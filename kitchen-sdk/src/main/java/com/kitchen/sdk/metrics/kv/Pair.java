
package com.kitchen.sdk.metrics.kv;

public class Pair<T, S> {
  private T first;
  
  private S second;
  
  public Pair(T t, S s) {
    this.first = t;
    this.second = s;
  }
  
  public T getFirst() {
    return this.first;
  }
  
  public void setFirst(T first) {
    this.first = first;
  }
  
  public S getSecond() {
    return this.second;
  }
  
  public void setSecond(S second) {
    this.second = second;
  }
}
