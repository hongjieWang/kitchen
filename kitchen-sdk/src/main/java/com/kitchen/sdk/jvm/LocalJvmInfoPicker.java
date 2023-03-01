package com.kitchen.sdk.jvm;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.List;


public class LocalJvmInfoPicker implements JvmInfoPicker {
    private long uptime;

    private long processCpuTime;

    private GarbageCollectorMXBean youngGC;

    private GarbageCollectorMXBean fullGC;

    private static LocalJvmInfoPicker instance = new LocalJvmInfoPicker();

    private LocalJvmInfoPicker() {
        List<GarbageCollectorMXBean> gcList = ManagementFactory.getGarbageCollectorMXBeans();
        if (gcList == null || gcList.isEmpty()) return;
        if (gcList.size() == 1) {
            this.youngGC = gcList.get(0);
        } else {
            this.youngGC = gcList.get(0);
            this.fullGC = gcList.get(1);
        }
    }

    public static LocalJvmInfoPicker getInstance() {
        return instance;
    }

    public static String getYoungGCName() {
        return (instance.youngGC != null) ? instance.youngGC.getName() : "NULL";
    }

    public static String getFullGCName() {
        return (instance.fullGC != null) ? instance.fullGC.getName() : "NULL";
    }

    public static long getYoungGCTime() {
        if (instance.youngGC != null) return instance.youngGC.getCollectionTime();
        return 0L;
    }

    public static long getYoungGCCount() {
        if (instance.youngGC != null) return instance.youngGC.getCollectionCount();
        return 0L;
    }

    public static long getFullGCTime() {
        if (instance.fullGC != null) return instance.fullGC.getCollectionTime();
        return 0L;
    }

    public static long getFullGCCount() {
        if (instance.fullGC != null) return instance.fullGC.getCollectionCount();
        return 0L;
    }

    public static long getFullGCStartTime() {
        return 0L;
    }

    public static long getFullGCEndTime() {
        return 0L;
    }

    public static long getYoungGCStartTime() {
        return 0L;
    }

    public static long getYoungGCEndTime() {
        return 0L;
    }

    public static long getYoungGCDuration() {
        return 0L;
    }

    public static long getFullGCDuration() {
        return 0L;
    }

    public String getOSArch() {
        return System.getProperties().getProperty("os.arch");
    }

    public String getOSName() {
        return System.getProperties().getProperty("os.name");
    }

    public String getSystemModel() {
        return System.getProperties().getProperty("sun.arch.data.model");
    }

    public String getLibPath() {
        return System.getProperties().getProperty("java.library.path");
    }

    public String getJREVersion() {
        return System.getProperties().getProperty("java.version");
    }

    public String getStartTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return sdf.format(ManagementFactory.getRuntimeMXBean().getStartTime());
    }

    public String getClassPath() {
        return ManagementFactory.getRuntimeMXBean().getClassPath();
    }

    public String getBootClassPath() {
        return ManagementFactory.getRuntimeMXBean().getBootClassPath();
    }

    public long getPeakThreadCount() {
        return ManagementFactory.getThreadMXBean().getPeakThreadCount();
    }

    public long getThreadCount() {
        return ManagementFactory.getThreadMXBean().getThreadCount();
    }

    public long getDaemonThreadCount() {
        return ManagementFactory.getThreadMXBean().getDaemonThreadCount();
    }

    public int getPid() {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        try {
            return Integer.parseInt(name.substring(0, name.indexOf('@')));
        } catch (Exception e) {
            return -1;
        }
    }

    public int getAvailableProcessors() {
        return ManagementFactory.getOperatingSystemMXBean().getAvailableProcessors();
    }

    public String getJREVendor() {
        return System.getProperties().getProperty("java.vm.vendor");
    }

    public String getInputArguments() {
        List<String> argList = ManagementFactory.getRuntimeMXBean().getInputArguments();
        StringBuilder sb = new StringBuilder();
        if (argList != null && !argList.isEmpty()) for (String arg : argList) {
            if (arg == null || arg.trim().length() == 0) continue;
            if (sb.length() > 0) sb.append(" ");
            arg = arg.replaceAll("\\\\", "/");
            sb.append(arg);
        }
        return sb.toString();
    }

    public long getTotalPhysicalMemorySize() {
        OperatingSystemMXBean osbean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        return osbean.getTotalPhysicalMemorySize();
//        return 0L;
    }

    public long getCommittedVirtualMemorySize() {
        OperatingSystemMXBean osbean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        return osbean.getCommittedVirtualMemorySize();
//        return 0L;
    }

    public long getTotalSwapSpaceSize() {
        OperatingSystemMXBean osbean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        return osbean.getTotalSwapSpaceSize();
//        return 0L;
    }

    public long getLoadedClassCount() {
        return ManagementFactory.getClassLoadingMXBean().getLoadedClassCount();
    }

    public long getTotalLoadedClassCount() {
        return ManagementFactory.getClassLoadingMXBean().getTotalLoadedClassCount();
    }

    public long getUnloadedClassCount() {
        return ManagementFactory.getClassLoadingMXBean().getUnloadedClassCount();
    }

    public long getHeapMemoryUsage() {
        try {
            return ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed();
        } catch (Throwable e) {
            return 0L;
        }
    }

    public long getNonHeapMemoryUsage() {
        try {
            return ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getUsed();
        } catch (Throwable e) {
            return 0L;
        }
    }

    public long getMaxHeapMemoryUsage() {
        try {
            return ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getMax();
        } catch (Throwable e) {
            return 0L;
        }
    }

    public long getMaxNonHeapMemoryUsage() {
        try {
            return ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getMax();
        } catch (Throwable e) {
            return 0L;
        }
    }

    public long getInitHeapMemoryUsage() {
        try {
            return ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getInit();
        } catch (Throwable e) {
            return 0L;
        }
    }

    public long getInitNonHeapMemoryUsage() {
        try {
            return ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getInit();
        } catch (Throwable e) {
            return 0L;
        }
    }

    public long getCommittedHeapMemoryUsage() {
        try {
            return ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getCommitted();
        } catch (Throwable e) {
            return 0L;
        }
    }

    public long getCommittedNonHeapMemoryUsage() {
        try {
            return ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getCommitted();
        } catch (Throwable e) {
            return 0L;
        }
    }

    public String getApplicationPath() {
        String classPath = null;
        URL classPathURL = null;
        try {
            classPathURL = getClass().getClassLoader().getResource("jvm.local");
            classPath = URLDecoder.decode(classPathURL.getPath(), System.getProperty("file.encoding"));
            classPath = classPath.replaceAll("\\\\", "/");
        } catch (Exception e) {
            classPath = getClass().getClassLoader().getResource("jvm.local").getPath();
        }
        classPath = classPath.replaceAll("^(file:)", "");
        if (classPath.matches(".*([/\\\\])WEB-INF([/\\\\])lib([/\\\\])(.*)$"))
            return classPath.replaceAll("([/\\\\])WEB-INF([/\\\\])lib([/\\\\])(.*)$", "");
        if (classPath.matches(".*([/\\\\])(([^/\\\\]+)\\.jar\\!([/\\\\])jvm\\.local)$"))
            return classPath.replaceAll("(([^/\\\\]+)\\.jar\\!([/\\\\])jvm\\.local)$", "");
        return classPath;
    }

    public String getHostName() {
        if (System.getenv("COMPUTERNAME") != null) return System.getenv("COMPUTERNAME");
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException uhe) {
            String host = uhe.getMessage();
            if (host != null) {
                int colon = host.indexOf(':');
                if (colon > 0) return host.substring(0, colon);
            }
            return "UnknownHost";
        }
    }

    public String getStartPath() {
        String startPath = System.getProperties().get("user.dir").toString();
        startPath = startPath.replaceAll("\\\\", "/");
        return startPath;
    }

    public float getCpu() {
        OperatingSystemMXBean osbean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        long uptimeNow = ManagementFactory.getRuntimeMXBean().getUptime();
        long processCpuTimeNow = osbean.getProcessCpuTime();
        float cpu = 0.0F;
        if (this.uptime > 0L && this.processCpuTime > 0L) {
            long l2 = uptimeNow - this.uptime;
            long l1 = processCpuTimeNow - this.processCpuTime;
            if (l2 > 0L) cpu = Math.min(99.0F, (float) l1 / (float) l2 * 10000.0F * osbean.getAvailableProcessors());
        }
        this.uptime = uptimeNow;
        this.processCpuTime = processCpuTimeNow;
        return cpu;
    }

    @Override
    public String pickJvmEnvironmentInfo() {
        StringBuilder sb = new StringBuilder("{");
        sb.append("\"").append("PId").append("\"").append(":").append("\"").append(getPid()).append("\"").append(",");
        sb.append("\"").append("JREV").append("\"").append(":").append("\"").append(getJREVersion()).append("\"").append(",");
        sb.append("\"").append("OSN").append("\"").append(":").append("\"").append(getOSName()).append("\"").append(",");
        sb.append("\"").append("OSA").append("\"").append(":").append("\"").append(getOSArch()).append("\"").append(",");
        sb.append("\"").append("OSAP").append("\"").append(":").append("\"").append(getAvailableProcessors()).append("\"").append(",");
        sb.append("\"").append("ARGS").append("\"").append(":").append("\"").append(getInputArguments()).append("\"").append(",");
        sb.append("\"").append("SP").append("\"").append(":").append("\"").append(getStartPath()).append("\"").append(",");
        sb.append("\"").append("AP").append("\"").append(":").append("\"").append(getApplicationPath()).append("\"").append(",");
        sb.append("\"").append("ST").append("\"").append(":").append("\"").append(getStartTime()).append("\"").append(",");
        sb.append("\"").append("TPMS").append("\"").append(":").append("\"").append(getTotalPhysicalMemorySize()).append("\"").append(",");
        sb.append("\"").append("TSSS").append("\"").append(":").append("\"").append(getTotalSwapSpaceSize()).append("\"").append(",");
        sb.append("\"").append("CVMS").append("\"").append(":").append("\"").append(getCommittedVirtualMemorySize()).append("\"").append(",");
        sb.append("\"").append("YGCN").append("\"").append(":").append("\"").append(getYoungGCName()).append("\"").append(",");
        sb.append("\"").append("FGCN").append("\"").append(":").append("\"").append(getFullGCName()).append("\"");
        sb.append("}");
        return sb.toString();
    }

    @Override
    public String pickJvmRumtimeInfo() {
        StringBuilder sb = new StringBuilder("{");
        sb.append("\"").append("PTC").append("\"").append(":").append(getPeakThreadCount()).append(",");
        sb.append("\"").append("TC").append("\"").append(":").append(getThreadCount()).append(",");
        sb.append("\"").append("DTC").append("\"").append(":").append(getDaemonThreadCount()).append(",");
        sb.append("\"").append("LCC").append("\"").append(":").append(getLoadedClassCount()).append(",");
        sb.append("\"").append("TLCC").append("\"").append(":").append(getTotalLoadedClassCount()).append(",");
        sb.append("\"").append("UCC").append("\"").append(":").append(getUnloadedClassCount()).append(",");
        sb.append("\"").append("NHMU").append("\"").append(":").append(getNonHeapMemoryUsage()).append(",");
        sb.append("\"").append("HMU").append("\"").append(":").append(getHeapMemoryUsage()).append(",");
        sb.append("\"").append("INHMU").append("\"").append(":").append(getInitNonHeapMemoryUsage()).append(",");
        sb.append("\"").append("IHMU").append("\"").append(":").append(getInitHeapMemoryUsage()).append(",");
        sb.append("\"").append("CNHMU").append("\"").append(":").append(getCommittedNonHeapMemoryUsage()).append(",");
        sb.append("\"").append("CHMU").append("\"").append(":").append(getCommittedHeapMemoryUsage()).append(",");
        sb.append("\"").append("MNHMU").append("\"").append(":").append(getMaxNonHeapMemoryUsage()).append(",");
        sb.append("\"").append("MHMU").append("\"").append(":").append(getMaxHeapMemoryUsage()).append(",");
        sb.append("\"").append("FGCC").append("\"").append(":").append(getFullGCCount()).append(",");
        sb.append("\"").append("YGCC").append("\"").append(":").append(getYoungGCCount()).append(",");
        sb.append("\"").append("FGCD").append("\"").append(":").append(getFullGCDuration()).append(",");
        sb.append("\"").append("YGCD").append("\"").append(":").append(getYoungGCDuration()).append(",");
        sb.append("\"").append("FGCT").append("\"").append(":").append(getFullGCTime()).append(",");
        sb.append("\"").append("YGCT").append("\"").append(":").append(getYoungGCTime()).append(",");
        sb.append("\"").append("FGCS").append("\"").append(":").append(getFullGCStartTime()).append(",");
        sb.append("\"").append("YGCS").append("\"").append(":").append(getYoungGCStartTime()).append(",");
        sb.append("\"").append("FGCE").append("\"").append(":").append(getFullGCEndTime()).append(",");
        sb.append("\"").append("YGCE").append("\"").append(":").append(getYoungGCEndTime()).append(",");
        sb.append("\"").append("CPU").append("\"").append(":").append(getCpu());
        return sb.append("}").toString();
    }

    @Override
    public String getJvmInstanceCode() {
        int instanceValue = 0;
        String instanceCode = "0";
        try {
            instanceValue = (getHostName() + getStartPath() + getApplicationPath() + System.currentTimeMillis()).hashCode();
            if (instanceValue < 0) {
                instanceValue = Math.abs(instanceValue);
                instanceCode = String.valueOf(instanceValue);
                instanceCode = getStrWith(instanceCode, String.valueOf(-2147483648).length());
            } else {
                instanceCode = String.valueOf(instanceValue);
            }
        } catch (Exception e) {
        }
        return instanceCode;
    }

    private String getStrWith(String str, int len) {
        StringBuilder sb = new StringBuilder(str);
        while (sb.length() < len) {
            if (sb.length() + 1 == len) {
                sb.insert(0, 1);
                continue;
            }
            sb.insert(0, 0);
        }
        return sb.toString();
    }
}