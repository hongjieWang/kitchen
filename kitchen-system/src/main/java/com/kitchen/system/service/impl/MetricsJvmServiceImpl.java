package com.kitchen.system.service.impl;

import java.util.List;

import com.kitchen.system.domain.MetricsJvm;
import com.kitchen.system.mapper.MetricsJvmMapper;
import com.kitchen.system.service.IMetricsJvmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 数据监控JVM参数Service业务层处理
 *
 * @author ruoyi
 * @date 2023-03-01
 */
@Service
public class MetricsJvmServiceImpl implements IMetricsJvmService {
    @Autowired
    private MetricsJvmMapper metricsJvmMapper;

    /**
     * 查询数据监控JVM参数
     *
     * @param id 数据监控JVM参数主键
     * @return 数据监控JVM参数
     */
    @Override
    public MetricsJvm selectMetricsJvmById(Long id) {
        return metricsJvmMapper.selectMetricsJvmById(id);
    }

    /**
     * 查询数据监控JVM参数列表
     *
     * @param metricsJvm 数据监控JVM参数
     * @return 数据监控JVM参数
     */
    @Override
    public List<MetricsJvm> selectMetricsJvmList(MetricsJvm metricsJvm) {
        return metricsJvmMapper.selectMetricsJvmList(metricsJvm);
    }

    /**
     * 新增数据监控JVM参数
     *
     * @param metricsJvm 数据监控JVM参数
     * @return 结果
     */
    @Override
    public int insertMetricsJvm(MetricsJvm metricsJvm) {
        return metricsJvmMapper.insertMetricsJvm(metricsJvm);
    }

    /**
     * 修改数据监控JVM参数
     *
     * @param metricsJvm 数据监控JVM参数
     * @return 结果
     */
    @Override
    public int updateMetricsJvm(MetricsJvm metricsJvm) {
        return metricsJvmMapper.updateMetricsJvm(metricsJvm);
    }

    /**
     * 批量删除数据监控JVM参数
     *
     * @param ids 需要删除的数据监控JVM参数主键
     * @return 结果
     */
    @Override
    public int deleteMetricsJvmByIds(Long[] ids) {
        return metricsJvmMapper.deleteMetricsJvmByIds(ids);
    }

    /**
     * 删除数据监控JVM参数信息
     *
     * @param id 数据监控JVM参数主键
     * @return 结果
     */
    @Override
    public int deleteMetricsJvmById(Long id) {
        return metricsJvmMapper.deleteMetricsJvmById(id);
    }
}
