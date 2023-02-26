package com.kitchen.god.sdk.task;

import com.kitchen.god.sdk.jvm.JvmInfoPicker;
import com.kitchen.god.sdk.jvm.JvmInfoPickerFactory;

import java.util.TimerTask;

public abstract class AbstractJvmInfo extends TimerTask {
    protected static JvmInfoPicker localJvm = JvmInfoPickerFactory.create("Local");

    protected static String instanceCode = localJvm.getJvmInstanceCode();

    protected static String logType = "JVM";
}
