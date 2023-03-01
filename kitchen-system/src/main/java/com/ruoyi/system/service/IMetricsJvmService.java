package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.MetricsJvm;

/**
 * 数据监控JVM参数Service接口
 * 
 * @author ruoyi
 * @date 2023-03-01
 */
public interface IMetricsJvmService 
{
    /**
     * 查询数据监控JVM参数
     * 
     * @param id 数据监控JVM参数主键
     * @return 数据监控JVM参数
     */
    public MetricsJvm selectMetricsJvmById(Long id);

    /**
     * 查询数据监控JVM参数列表
     * 
     * @param metricsJvm 数据监控JVM参数
     * @return 数据监控JVM参数集合
     */
    public List<MetricsJvm> selectMetricsJvmList(MetricsJvm metricsJvm);

    /**
     * 新增数据监控JVM参数
     * 
     * @param metricsJvm 数据监控JVM参数
     * @return 结果
     */
    public int insertMetricsJvm(MetricsJvm metricsJvm);

    /**
     * 修改数据监控JVM参数
     * 
     * @param metricsJvm 数据监控JVM参数
     * @return 结果
     */
    public int updateMetricsJvm(MetricsJvm metricsJvm);

    /**
     * 批量删除数据监控JVM参数
     * 
     * @param ids 需要删除的数据监控JVM参数主键集合
     * @return 结果
     */
    public int deleteMetricsJvmByIds(Long[] ids);

    /**
     * 删除数据监控JVM参数信息
     * 
     * @param id 数据监控JVM参数主键
     * @return 结果
     */
    public int deleteMetricsJvmById(Long id);
}
