package com.kitchen.sdk.metrics.task;

import com.kitchen.sdk.metrics.jvm.JvmInfoPicker;
import com.kitchen.sdk.metrics.jvm.JvmInfoPickerFactory;

import java.util.TimerTask;

public abstract class AbstractJvmInfo extends TimerTask {
    protected static JvmInfoPicker localJvm = JvmInfoPickerFactory.create("Local");

    protected static String instanceCode = localJvm.getJvmInstanceCode();

    protected static String logType = "JVM";
}
