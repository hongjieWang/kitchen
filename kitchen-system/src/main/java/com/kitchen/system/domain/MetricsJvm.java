package com.kitchen.system.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.kitchen.common.annotation.Excel;
import com.kitchen.common.core.domain.BaseEntity;

/**
 * 数据监控JVM参数对象 metrics_jvm
 *
 * @author ruoyi
 * @date 2023-03-01
 */
public class MetricsJvm extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 数据类型
     */
    @Excel(name = "数据类型")
    private String logType;

    /**
     * 应用名称
     */
    @Excel(name = "应用名称")
    private String appName;
    /**
     * 采集环境
     */
    private String environment;
    /**
     * 主机名称
     */
    @Excel(name = "主机名称")
    private String hostName;

    /**
     * 时间戳
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "时间戳", width = 30, dateFormat = "yyyy-MM-dd")
    private Date time;

    /**
     * 数据类型
     */
    @Excel(name = "数据类型")
    private Long dataType;

    /**
     * 峰值活动线程计数
     */
    @Excel(name = "峰值活动线程计数")
    private Long ptc;

    /**
     * JVM 线程数
     */
    @Excel(name = "JVM 线程数")
    private Long tc;

    /**
     * 当前活动后台进程线程数
     */
    @Excel(name = "当前活动后台进程线程数")
    private Long dtc;

    /**
     * 当前加载到 Java 虚拟机中的类的数量
     */
    @Excel(name = "当前加载到 Java 虚拟机中的类的数量")
    private Long lcc;

    /**
     * Java 虚拟机开始执行到目前已经卸载的类的总数
     */
    @Excel(name = "Java 虚拟机开始执行到目前已经卸载的类的总数")
    private Long tlcc;

    /**
     * Java 虚拟机开始执行到目前已经卸载的类的总数
     */
    @Excel(name = "Java 虚拟机开始执行到目前已经卸载的类的总数")
    private Long ucc;

    /**
     * java 虚拟机使用的非堆内存的当前内存使用量
     */
    @Excel(name = "java 虚拟机使用的非堆内存的当前内存使用量")
    private Long nhmu;

    /**
     * java 虚拟机用于对象分配的堆的当前内存使用量
     */
    @Excel(name = "java 虚拟机用于对象分配的堆的当前内存使用量")
    private Long hmu;

    /**
     * java虚拟机非堆初始化大小
     */
    @Excel(name = "java虚拟机非堆初始化大小")
    private Long inhmu;

    /**
     * Java虚拟机堆内存初始化大小
     */
    @Excel(name = "Java虚拟机堆内存初始化大小")
    private Long ihmu;

    /**
     * java 虚拟机可使用非堆内存
     */
    @Excel(name = "java 虚拟机可使用非堆内存")
    private Long cnhmu;

    /**
     * java 虚拟机可使用堆内存
     */
    @Excel(name = "java 虚拟机可使用堆内存")
    private Long chmu;

    /**
     * Java 虚拟机 最大非堆内存
     */
    @Excel(name = "Java 虚拟机 最大非堆内存")
    private Long mnhmu;

    /**
     * java 虚拟机最大堆内存
     */
    @Excel(name = "java 虚拟机最大堆内存")
    private Long mhmu;

    /**
     * fgc执行次数
     */
    @Excel(name = "fgc执行次数")
    private Long fgcc;

    /**
     * ygc执行次数
     */
    @Excel(name = "ygc执行次数")
    private Long ygcc;

    /**
     * fgc持续时间
     */
    @Excel(name = "fgc持续时间")
    private Long fgcd;

    /**
     * ygc持续时间
     */
    @Excel(name = "ygc持续时间")
    private Long ygcd;

    /**
     * fgc累积回收时间
     */
    @Excel(name = "fgc累积回收时间")
    private Long fgct;

    /**
     * Ygc累积回收时间
     */
    @Excel(name = "Ygc累积回收时间")
    private Long ygct;

    /**
     * fgc开始时间
     */
    @Excel(name = "fgc开始时间")
    private Long fgcs;

    /**
     * ygc开始时间
     */
    @Excel(name = "ygc开始时间")
    private Long ygcs;

    /**
     * fgc结束时间
     */
    @Excel(name = "fgc结束时间")
    private Long fgce;

    /**
     * ygc结束时间
     */
    @Excel(name = "ygc结束时间")
    private Long ygce;

    /**
     * cpu执行时间
     */
    @Excel(name = "cpu执行时间")
    private Long cpu;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getLogType() {
        return logType;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppName() {
        return appName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostName() {
        return hostName;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getTime() {
        return time;
    }

    public void setDataType(Long dataType) {
        this.dataType = dataType;
    }

    public Long getDataType() {
        return dataType;
    }

    public void setPtc(Long ptc) {
        this.ptc = ptc;
    }

    public Long getPtc() {
        return ptc;
    }

    public void setTc(Long tc) {
        this.tc = tc;
    }

    public Long getTc() {
        return tc;
    }

    public void setDtc(Long dtc) {
        this.dtc = dtc;
    }

    public Long getDtc() {
        return dtc;
    }

    public void setLcc(Long lcc) {
        this.lcc = lcc;
    }

    public Long getLcc() {
        return lcc;
    }

    public void setTlcc(Long tlcc) {
        this.tlcc = tlcc;
    }

    public Long getTlcc() {
        return tlcc;
    }

    public void setUcc(Long ucc) {
        this.ucc = ucc;
    }

    public Long getUcc() {
        return ucc;
    }

    public void setNhmu(Long nhmu) {
        this.nhmu = nhmu;
    }

    public Long getNhmu() {
        return nhmu;
    }

    public void setHmu(Long hmu) {
        this.hmu = hmu;
    }

    public Long getHmu() {
        return hmu;
    }

    public void setInhmu(Long inhmu) {
        this.inhmu = inhmu;
    }

    public Long getInhmu() {
        return inhmu;
    }

    public void setIhmu(Long ihmu) {
        this.ihmu = ihmu;
    }

    public Long getIhmu() {
        return ihmu;
    }

    public void setCnhmu(Long cnhmu) {
        this.cnhmu = cnhmu;
    }

    public Long getCnhmu() {
        return cnhmu;
    }

    public void setChmu(Long chmu) {
        this.chmu = chmu;
    }

    public Long getChmu() {
        return chmu;
    }

    public void setMnhmu(Long mnhmu) {
        this.mnhmu = mnhmu;
    }

    public Long getMnhmu() {
        return mnhmu;
    }

    public void setMhmu(Long mhmu) {
        this.mhmu = mhmu;
    }

    public Long getMhmu() {
        return mhmu;
    }

    public void setFgcc(Long fgcc) {
        this.fgcc = fgcc;
    }

    public Long getFgcc() {
        return fgcc;
    }

    public void setYgcc(Long ygcc) {
        this.ygcc = ygcc;
    }

    public Long getYgcc() {
        return ygcc;
    }

    public void setFgcd(Long fgcd) {
        this.fgcd = fgcd;
    }

    public Long getFgcd() {
        return fgcd;
    }

    public void setYgcd(Long ygcd) {
        this.ygcd = ygcd;
    }

    public Long getYgcd() {
        return ygcd;
    }

    public void setFgct(Long fgct) {
        this.fgct = fgct;
    }

    public Long getFgct() {
        return fgct;
    }

    public void setYgct(Long ygct) {
        this.ygct = ygct;
    }

    public Long getYgct() {
        return ygct;
    }

    public void setFgcs(Long fgcs) {
        this.fgcs = fgcs;
    }

    public Long getFgcs() {
        return fgcs;
    }

    public void setYgcs(Long ygcs) {
        this.ygcs = ygcs;
    }

    public Long getYgcs() {
        return ygcs;
    }

    public void setFgce(Long fgce) {
        this.fgce = fgce;
    }

    public Long getFgce() {
        return fgce;
    }

    public void setYgce(Long ygce) {
        this.ygce = ygce;
    }

    public Long getYgce() {
        return ygce;
    }

    public void setCpu(Long cpu) {
        this.cpu = cpu;
    }

    public Long getCpu() {
        return cpu;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("logType", getLogType())
                .append("appName", getAppName())
                .append("hostName", getHostName())
                .append("time", getTime())
                .append("dataType", getDataType())
                .append("ptc", getPtc())
                .append("tc", getTc())
                .append("dtc", getDtc())
                .append("lcc", getLcc())
                .append("tlcc", getTlcc())
                .append("ucc", getUcc())
                .append("nhmu", getNhmu())
                .append("hmu", getHmu())
                .append("inhmu", getInhmu())
                .append("ihmu", getIhmu())
                .append("cnhmu", getCnhmu())
                .append("chmu", getChmu())
                .append("mnhmu", getMnhmu())
                .append("mhmu", getMhmu())
                .append("fgcc", getFgcc())
                .append("ygcc", getYgcc())
                .append("fgcd", getFgcd())
                .append("ygcd", getYgcd())
                .append("fgct", getFgct())
                .append("ygct", getYgct())
                .append("fgcs", getFgcs())
                .append("ygcs", getYgcs())
                .append("fgce", getFgce())
                .append("ygce", getYgce())
                .append("cpu", getCpu())
                .toString();
    }
}
