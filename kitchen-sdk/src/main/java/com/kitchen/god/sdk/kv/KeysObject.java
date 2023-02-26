package com.kitchen.god.sdk.kv;

import com.kitchen.god.sdk.common.Type;
import com.kitchen.god.sdk.util.StringUtil;

import java.util.Arrays;
import java.util.List;

public class KeysObject {
    private final String key1;

    private final String key2;

    private final String key3;

    private final Type type;

    public KeysObject(String key1, String key2, String key3, Type type) {
        this.key1 = key1;
        this.key2 = key2;
        this.key3 = key3;
        this.type = type;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        return asList().equals(((KeysObject) o).asList());
    }

    public int hashCode() {
        return asList().hashCode();
    }

    private List<Object> asList() {
        return Arrays.asList(new Object[]{this.key1, this.key2, this.key3, this.type});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String key : Arrays.<String>asList(new String[]{this.key1, this.key2, this.key3})) {
            if (StringUtil.isNotBlank(key)) {
                if (sb.length() > 0)
                    sb.append("#");
                sb.append(key);
            }
        }
        return sb.toString();
    }

    public String getKey1() {
        return this.key1;
    }

    public String getKey2() {
        return this.key2;
    }

    public String getKey3() {
        return this.key3;
    }

    public Type getType() {
        return this.type;
    }
}