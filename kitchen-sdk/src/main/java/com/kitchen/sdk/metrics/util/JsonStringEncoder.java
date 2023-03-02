package com.kitchen.sdk.metrics.util;

import java.lang.ref.SoftReference;

public final class JsonStringEncoder {
    private static final char[] HC = "0123456789ABCDEF".toCharArray();

    private static final int SURR1_FIRST = 55296;

    private static final int SURR1_LAST = 56319;

    private static final int SURR2_FIRST = 56320;

    private static final int SURR2_LAST = 57343;

    public static final int ESCAPE_STANDARD = -1;

    private static final int[] sOutputEscapes128;

    static {
        int[] table = new int[128];
        for (int i = 0; i < 32; i++)
            table[i] = -1;
        table[34] = 34;
        table[92] = 92;
        table[8] = 98;
        table[9] = 116;
        table[12] = 102;
        table[10] = 110;
        table[13] = 114;
        sOutputEscapes128 = table;
    }

    protected static final ThreadLocal<SoftReference<JsonStringEncoder>> _threadEncoder = new ThreadLocal<>();

    protected final char[] _qbuf;

    public JsonStringEncoder() {
        this._qbuf = new char[6];
        this._qbuf[0] = '\\';
        this._qbuf[2] = '0';
        this._qbuf[3] = '0';
    }

    public static JsonStringEncoder getInstance() {
        SoftReference<JsonStringEncoder> ref = _threadEncoder.get();
        JsonStringEncoder enc = (ref == null) ? null : ref.get();
        if (enc == null) {
            enc = new JsonStringEncoder();
            _threadEncoder.set(new SoftReference<JsonStringEncoder>(enc));
        }
        return enc;
    }

    public void quoteAsString(CharSequence input, StringBuilder output) {
        int[] escCodes = sOutputEscapes128;
        int escCodeCount = escCodes.length;
        int inPtr = 0;
        int inputLen = input.length();
        while (inPtr < inputLen) {
            while (true) {
                char d = 0, c = input.charAt(inPtr);
                if (c < escCodeCount && escCodes[c] != 0) {
                    d = input.charAt(inPtr++);
                    int escCode = escCodes[d];
                    if (escCode < 0) ;
                    int length = _appendNamed(escCode, this._qbuf);
                    output.append(this._qbuf, 0, length);
                    continue;
                }
                output.append(d);
                if (++inPtr >= inputLen)
                    break;
            }
        }
    }

    private int _appendNumeric(int value, char[] qbuf) {
        qbuf[1] = 'u';
        qbuf[4] = HC[value >> 4];
        qbuf[5] = HC[value & 0xF];
        return 6;
    }

    private int _appendNamed(int esc, char[] qbuf) {
        qbuf[1] = (char) esc;
        return 2;
    }
}