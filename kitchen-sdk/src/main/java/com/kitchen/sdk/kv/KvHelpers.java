package com.kitchen.sdk.kv;

import com.kitchen.sdk.loggers.MetricsLoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class KvHelpers {
  private static final Lock lock = new ReentrantLock();
  
  public static Map<KeysObject, ValuesObject> cached = new ConcurrentHashMap<KeysObject, ValuesObject>(1000);
  
  public static void addKeysValues(KeysObject keysObject, long v1, long v2) {
    if (MetricsLoggerFactory.ENABLE) {
      ValuesObject valuesObject = getValuesByKeys(keysObject);
      valuesObject.addCount(v1, v2);
    } 
  }
  
  public static void updateKeysValues(KeysObject keysObject, long v1, long v2) {
    if (MetricsLoggerFactory.ENABLE) {
      ValuesObject valuesObject = getValuesByKeys(keysObject);
      valuesObject.update(v1, v2);
    } 
  }
  
  private static ValuesObject getValuesByKeys(KeysObject keysObject) {
    ValuesObject valuesObject = cached.get(keysObject);
    if (valuesObject == null)
      try {
        lock.lock();
        valuesObject = cached.get(keysObject);
        if (valuesObject != null)
          return valuesObject; 
        valuesObject = new ValuesObject(keysObject.getType());
        cached.put(keysObject, valuesObject);
      } finally {
        lock.unlock();
      }  
    return valuesObject;
  }
}
