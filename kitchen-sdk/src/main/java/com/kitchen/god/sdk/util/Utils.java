package com.kitchen.god.sdk.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.TimeZone;
import java.util.regex.Pattern;

public class Utils {
    private static final Pattern IP_PATTERN = Pattern.compile("\\d{1,3}(\\.\\d{1,3}){3,5}$");

    private static final String STR_HOST_ERROR_DETECTED = "** HOST ERROR DETECTED **";

    private static final String STR_IP_ERROR_DETECTED = "** IP ERROR DETECTED **";

    private static final String LOG_TIME_FORMAT = "yyyyMMddHHmmssSSS";

    public static final String HOST_NAME = getHostName();

    public static final String HOST_IP = getHostIP();

    protected static String getHostName() {
        String host = "** HOST ERROR DETECTED **";
        try {
            try {
                InetAddress localAddress = InetAddress.getLocalHost();
                host = localAddress.getHostName();
            } catch (Throwable e) {
                InetAddress localAddress = getLocalAddress();
                if (localAddress != null) {
                    host = localAddress.getHostName();
                } else {
                    host = "** HOST ERROR DETECTED **";
                }
            }
        } catch (Throwable ex) {
        }
        return host;
    }

    protected static String getHostIP() {
        String ip = "** IP ERROR DETECTED **";
        try {
            InetAddress inetAddress = getLocalAddress();
            if (inetAddress != null) {
                ip = inetAddress.getHostAddress();
            }
        } catch (Throwable ex) {
        }
        return ip;
    }

    public static String getNowTime() {
        String nowTime = null;
        try {
            Date rightNow = new Date();
            TimeZone localTimeZone = TimeZone.getTimeZone("GMT+8");
            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            df.setTimeZone(localTimeZone);
            nowTime = df.format(rightNow);
        } catch (Exception e) {
            nowTime = "";
        }
        return nowTime;
    }

    private static InetAddress getLocalAddress() {
        InetAddress localAddress = null;
        try {
            localAddress = InetAddress.getLocalHost();
            if (isValidAddress(localAddress))
                return localAddress;
        } catch (Throwable e) {
        }
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                try {
                    NetworkInterface network = interfaces.nextElement();
                    Enumeration<InetAddress> addresses = network.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        try {
                            InetAddress address = addresses.nextElement();
                            if (isValidAddress(address)) {
                                return address;
                            }
                        } catch (Throwable ignored) {
                        }
                    }
                } catch (Throwable ignored) {
                }
            }
        } catch (Throwable ignored) {
        }
        return localAddress;
    }

    private static boolean isValidAddress(InetAddress address) {
        if (address == null || address.isLoopbackAddress())
            return false;
        String ip = address.getHostAddress();
        return (ip != null && !"0.0.0.0".equals(ip) && !"127.0.0.1".equals(ip) && IP_PATTERN.matcher(ip).matches());
    }
}
