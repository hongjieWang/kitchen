package com.kitchen.sdk.log4j.helpers;

import com.kitchen.sdk.log4j.Appender;
import com.kitchen.sdk.log4j.spi.AppenderAttachable;
import com.kitchen.sdk.log4j.spi.LoggingEvent;

import java.util.Enumeration;
import java.util.Vector;

public class AppenderAttachableImpl implements AppenderAttachable {
    protected Vector appenderList;

    @Override
    public void addAppender(Appender newAppender) {
        if (newAppender == null)
            return;
        if (this.appenderList == null)
            this.appenderList = new Vector(1);
        if (!this.appenderList.contains(newAppender))
            this.appenderList.addElement(newAppender);
    }

    public int appendLoopOnAppenders(LoggingEvent event) {
        int size = 0;
        if (this.appenderList != null) {
            size = this.appenderList.size();
            for (int i = 0; i < size; i++) {
                Appender appender = (Appender) this.appenderList.elementAt(i);
                appender.doAppend(event);
            }
        }
        return size;
    }

    @Override
    public Enumeration getAllAppenders() {
        if (this.appenderList == null)
            return null;
        return this.appenderList.elements();
    }

    @Override
    public Appender getAppender(String name) {
        if (this.appenderList == null || name == null)
            return null;
        int size = this.appenderList.size();
        for (int i = 0; i < size; i++) {
            Appender appender = (Appender) this.appenderList.elementAt(i);
            if (name.equals(appender.getName()))
                return appender;
        }
        return null;
    }

    @Override
    public boolean isAttached(Appender appender) {
        if (this.appenderList == null || appender == null)
            return false;
        int size = this.appenderList.size();
        for (int i = 0; i < size; i++) {
            Appender a = (Appender) this.appenderList.elementAt(i);
            if (a == appender)
                return true;
        }
        return false;
    }

    @Override
    public void removeAllAppenders() {
        if (this.appenderList != null) {
            int len = this.appenderList.size();
            for (int i = 0; i < len; i++) {
                Appender a = (Appender) this.appenderList.elementAt(i);
                a.close();
            }
            this.appenderList.removeAllElements();
            this.appenderList = null;
        }
    }

    @Override
    public void removeAppender(Appender appender) {
        if (appender == null || this.appenderList == null)
            return;
        this.appenderList.removeElement(appender);
    }

    @Override
    public void removeAppender(String name) {
        if (name == null || this.appenderList == null)
            return;
        int size = this.appenderList.size();
        for (int i = 0; i < size; i++) {
            if (name.equals(((Appender) this.appenderList.elementAt(i)).getName())) {
                this.appenderList.removeElementAt(i);
                break;
            }
        }
    }
}