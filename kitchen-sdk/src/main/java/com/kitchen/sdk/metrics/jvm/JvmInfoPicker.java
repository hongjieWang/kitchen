
package com.kitchen.sdk.metrics.jvm;

public interface JvmInfoPicker {
  String pickJvmEnvironmentInfo();
  
  String pickJvmRumtimeInfo();
  
  String getJvmInstanceCode();
}