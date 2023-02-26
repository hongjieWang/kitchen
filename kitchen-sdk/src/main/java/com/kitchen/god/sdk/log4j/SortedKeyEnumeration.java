package com.kitchen.god.sdk.log4j;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

class SortedKeyEnumeration implements Enumeration {
    private Enumeration e;

    public SortedKeyEnumeration(Hashtable ht) {
        Enumeration<String> f = ht.keys();
        Vector<String> keys = new Vector(ht.size());
        for (int last = 0; f.hasMoreElements(); last++) {
            String key = f.nextElement();
            int i;
            for (i = 0; i < last; i++) {
                String s = keys.get(i);
                if (key.compareTo(s) <= 0)
                    break;
            }
            keys.add(i, key);
        }
        this.e = keys.elements();
    }

    public boolean hasMoreElements() {
        return this.e.hasMoreElements();
    }

    public Object nextElement() {
        return this.e.nextElement();
    }
}
