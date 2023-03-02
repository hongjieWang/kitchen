package com.kitchen.system.service;

import java.util.List;

import com.kitchen.system.domain.MetricsKv;

/**
 * 数据监控键值Service接口
 *
 * @author ruoyi
 * @date 2023-03-01
 */
public interface IMetricsKvService {
    /**
     * 查询数据监控键值
     *
     * @param id 数据监控键值主键
     * @return 数据监控键值
     */
    public MetricsKv selectMetricsKvById(Long id);

    /**
     * 查询数据监控键值列表
     *
     * @param metricsKv 数据监控键值
     * @return 数据监控键值集合
     */
    public List<MetricsKv> selectMetricsKvList(MetricsKv metricsKv);

    /**
     * 新增数据监控键值
     *
     * @param metricsKv 数据监控键值
     * @return 结果
     */
    public int insertMetricsKv(MetricsKv metricsKv);

    /**
     * 修改数据监控键值
     *
     * @param metricsKv 数据监控键值
     * @return 结果
     */
    public int updateMetricsKv(MetricsKv metricsKv);

    /**
     * 批量删除数据监控键值
     *
     * @param ids 需要删除的数据监控键值主键集合
     * @return 结果
     */
    public int deleteMetricsKvByIds(Long[] ids);

    /**
     * 删除数据监控键值信息
     *
     * @param id 数据监控键值主键
     * @return 结果
     */
    public int deleteMetricsKvById(Long id);
}
