package com.kitchen.sdk.metrics.jvm;

public class JvmInfoPickerFactory {
  public static final String PICKER_TYPE = "Local";
  
  public static JvmInfoPicker create(String type) {
    return LocalJvmInfoPicker.getInstance();
  }
}