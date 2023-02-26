package com.kitchen.god.sdk.log4j.pattern;

import com.kitchen.god.sdk.log4j.helpers.Loader;
import com.kitchen.god.sdk.log4j.helpers.LogLog;

import java.lang.reflect.Method;
import java.util.*;

public final class PatternParser {
    private static final char ESCAPE_CHAR = '%';

    private static final int LITERAL_STATE = 0;

    private static final int CONVERTER_STATE = 1;

    private static final int DOT_STATE = 3;

    private static final int MIN_STATE = 4;

    private static final int MAX_STATE = 5;

    private static final Map PATTERN_LAYOUT_RULES;

    private static final Map FILENAME_PATTERN_RULES;

    static {
        Map<Object, Object> rules = new HashMap<Object, Object>(17);
        rules.put("m", MessagePatternConverter.class);
        rules.put("message", MessagePatternConverter.class);
        rules.put("n", LineSeparatorPatternConverter.class);
        PATTERN_LAYOUT_RULES = new ReadOnlyMap(rules);
        Map<Object, Object> fnameRules = new HashMap<Object, Object>(4);
        fnameRules.put("d", FileDatePatternConverter.class);
        fnameRules.put("date", FileDatePatternConverter.class);
        fnameRules.put("i", IntegerPatternConverter.class);
        fnameRules.put("index", IntegerPatternConverter.class);
        FILENAME_PATTERN_RULES = new ReadOnlyMap(fnameRules);
    }

    public static Map getPatternLayoutRules() {
        return PATTERN_LAYOUT_RULES;
    }

    public static Map getFileNamePatternRules() {
        return FILENAME_PATTERN_RULES;
    }

    private static int extractConverter(char lastChar, String pattern, int i, StringBuffer convBuf, StringBuffer currentLiteral) {
        convBuf.setLength(0);
        if (!Character.isUnicodeIdentifierStart(lastChar)) {
            return i;
        }
        convBuf.append(lastChar);
        while (i < pattern.length() && Character.isUnicodeIdentifierPart(pattern.charAt(i))) {
            convBuf.append(pattern.charAt(i));
            currentLiteral.append(pattern.charAt(i));
            i++;
        }
        return i;
    }

    private static int extractOptions(String pattern, int i, List<String> options) {
        while (i < pattern.length() && pattern.charAt(i) == '{') {
            int end = pattern.indexOf('}', i);
            if (end == -1) {
                break;
            }
            String r = pattern.substring(i + 1, end);
            options.add(r);
            i = end + 1;
        }
        return i;
    }

    public static void parse(String pattern, List<Object> patternConverters, List<FormattingInfo> formattingInfos, Map converterRegistry, Map rules) {
        if (pattern == null)
            throw new NullPointerException("pattern");
        StringBuffer currentLiteral = new StringBuffer(32);
        int patternLength = pattern.length();
        int state = 0;
        int i = 0;
        FormattingInfo formattingInfo = FormattingInfo.getDefault();
        while (i < patternLength) {
            char c = pattern.charAt(i++);
            switch (state) {
                case 0:
                    if (i == patternLength) {
                        currentLiteral.append(c);
                        continue;
                    }
                    if (c == '%') {
                        switch (pattern.charAt(i)) {
                            case '%':
                                currentLiteral.append(c);
                                i++;
                                continue;
                        }
                        if (currentLiteral.length() != 0) {
                            patternConverters.add(new LiteralPatternConverter(currentLiteral.toString()));
                            formattingInfos.add(FormattingInfo.getDefault());
                        }
                        currentLiteral.setLength(0);
                        currentLiteral.append(c);
                        state = 1;
                        formattingInfo = FormattingInfo.getDefault();
                        continue;
                    }
                    currentLiteral.append(c);
                case 1:
                    currentLiteral.append(c);
                    switch (c) {
                        case '-':
                            formattingInfo = new FormattingInfo(true, formattingInfo.getMinLength(), formattingInfo.getMaxLength());
                            continue;
                        case '.':
                            state = 3;
                            continue;
                    }
                    if (c >= '0' && c <= '9') {
                        formattingInfo = new FormattingInfo(formattingInfo.isLeftAligned(), c - 48, formattingInfo.getMaxLength());
                        state = 4;
                        continue;
                    }
                    i = finalizeConverter(c, pattern, i, currentLiteral, formattingInfo, converterRegistry, rules, patternConverters, formattingInfos);
                    state = 0;
                    formattingInfo = FormattingInfo.getDefault();
                    currentLiteral.setLength(0);
                case 4:
                    currentLiteral.append(c);
                    if (c >= '0' && c <= '9') {
                        formattingInfo = new FormattingInfo(formattingInfo.isLeftAligned(), formattingInfo.getMinLength() * 10 + c - 48, formattingInfo.getMaxLength());
                        continue;
                    }
                    if (c == '.') {
                        state = 3;
                        continue;
                    }
                    i = finalizeConverter(c, pattern, i, currentLiteral, formattingInfo, converterRegistry, rules, patternConverters, formattingInfos);
                    state = 0;
                    formattingInfo = FormattingInfo.getDefault();
                    currentLiteral.setLength(0);
                case 3:
                    currentLiteral.append(c);
                    if (c >= '0' && c <= '9') {
                        formattingInfo = new FormattingInfo(formattingInfo.isLeftAligned(), formattingInfo.getMinLength(), c - 48);
                        state = 5;
                        continue;
                    }
                    LogLog.error("Error occured in position " + i + ".\n Was expecting digit, instead got char \"" + c + "\".");
                    state = 0;
                case 5:
                    currentLiteral.append(c);
                    if (c >= '0' && c <= '9') {
                        formattingInfo = new FormattingInfo(formattingInfo.isLeftAligned(), formattingInfo.getMinLength(), formattingInfo.getMaxLength() * 10 + c - 48);
                        continue;
                    }
                    i = finalizeConverter(c, pattern, i, currentLiteral, formattingInfo, converterRegistry, rules, patternConverters, formattingInfos);
                    state = 0;
                    formattingInfo = FormattingInfo.getDefault();
                    currentLiteral.setLength(0);
            }
        }
        if (currentLiteral.length() != 0) {
            patternConverters.add(new LiteralPatternConverter(currentLiteral.toString()));
            formattingInfos.add(FormattingInfo.getDefault());
        }
    }

    private static Object createConverter(String converterId, StringBuffer currentLiteral, Map converterRegistry, Map rules, List options) {
        String converterName = converterId;
        Object converterObj = null;
        for (int i = converterId.length(); i > 0 && converterObj == null;
             i--) {
            converterName = converterName.substring(0, i);
            if (converterRegistry != null) {
                converterObj = converterRegistry.get(converterName);
            }
            if (converterObj == null && rules != null) {
                converterObj = rules.get(converterName);
            }
        }
        if (converterObj == null) {
            LogLog.error("Unrecognized format specifier [" + converterId + "]");
            return null;
        }
        Class converterClass = null;
        if (converterObj instanceof Class) {
            converterClass = (Class) converterObj;
        } else if (converterObj instanceof String) {
            try {
                converterClass = Loader.loadClass((String) converterObj);
            } catch (ClassNotFoundException ex) {
                LogLog.warn("Class for conversion pattern %" + converterName + " not found", ex);
                return null;
            }
        } else {
            LogLog.warn("Bad map entry for conversion pattern %" + converterName + ".");
            return null;
        }
        try {
            Method factory = converterClass.getMethod("newInstance", new Class[]{Class.forName("[Ljava.lang.String;")});
            String[] optionsArray = new String[options.size()];
            optionsArray = (String[]) options.toArray((Object[]) optionsArray);
            Object newObj = factory.invoke(null, new Object[]{optionsArray});
            if (newObj instanceof PatternConverter) {
                currentLiteral.delete(0, currentLiteral.length() - converterId.length() - converterName.length());
                return (PatternConverter) newObj;
            }
            LogLog.warn("Class " + converterClass.getName() + " does not extend PatternConverter.");
        } catch (Exception ex) {
            LogLog.error("Error creating converter for " + converterId, ex);
            try {
                Object pc = converterClass.newInstance();
                currentLiteral.delete(0, currentLiteral.length() - converterId.length() - converterName.length());
                return pc;
            } catch (Exception ex2) {
                LogLog.error("Error creating converter for " + converterId, ex2);
            }
        }
        return null;
    }

    private static int finalizeConverter(char c, String pattern, int i, StringBuffer currentLiteral, FormattingInfo formattingInfo, Map converterRegistry, Map rules, List<Object> patternConverters, List<FormattingInfo> formattingInfos) {
        StringBuffer convBuf = new StringBuffer();
        i = extractConverter(c, pattern, i, convBuf, currentLiteral);
        String converterId = convBuf.toString();
        List options = new ArrayList();
        i = extractOptions(pattern, i, options);
        Object pc = createConverter(converterId, currentLiteral, converterRegistry, rules, options);
        if (pc == null) {
            StringBuffer msg;
            if (converterId == null || converterId.length() == 0) {
                msg = new StringBuffer("Empty conversion specifier starting at position ");
            } else {
                msg = new StringBuffer("Unrecognized conversion specifier [");
                msg.append(converterId);
                msg.append("] starting at position ");
            }
            msg.append(Integer.toString(i));
            msg.append(" in conversion pattern.");
            LogLog.error(msg.toString());
            patternConverters.add(new LiteralPatternConverter(currentLiteral.toString()));
            formattingInfos.add(FormattingInfo.getDefault());
        } else {
            patternConverters.add(pc);
            formattingInfos.add(formattingInfo);
            if (currentLiteral.length() > 0) {
                patternConverters.add(new LiteralPatternConverter(currentLiteral.toString()));
                formattingInfos.add(FormattingInfo.getDefault());
            }
        }
        currentLiteral.setLength(0);
        return i;
    }

    private static class ReadOnlyMap implements Map {
        private final Map map;

        public ReadOnlyMap(Map src) {
            this.map = src;
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean containsKey(Object key) {
            return this.map.containsKey(key);
        }

        @Override
        public boolean containsValue(Object value) {
            return this.map.containsValue(value);
        }

        @Override
        public Set entrySet() {
            return this.map.entrySet();
        }

        @Override
        public Object get(Object key) {
            return this.map.get(key);
        }

        @Override
        public boolean isEmpty() {
            return this.map.isEmpty();
        }

        @Override
        public Set keySet() {
            return this.map.keySet();
        }

        @Override
        public Object put(Object key, Object value) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void putAll(Map t) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Object remove(Object key) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int size() {
            return this.map.size();
        }

        @Override
        public Collection values() {
            return this.map.values();
        }
    }
}