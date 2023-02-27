
package com.kitchen.sdk.jvm;

public interface JvmInfoPicker {
  String pickJvmEnvironmentInfo();
  
  String pickJvmRumtimeInfo();
  
  String getJvmInstanceCode();
}