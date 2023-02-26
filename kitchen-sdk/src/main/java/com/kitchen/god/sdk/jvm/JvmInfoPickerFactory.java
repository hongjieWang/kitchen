package com.kitchen.god.sdk.jvm;

public class JvmInfoPickerFactory {
  public static final String PICKER_TYPE = "Local";
  
  public static JvmInfoPicker create(String type) {
    return LocalJvmInfoPicker.getInstance();
  }
}