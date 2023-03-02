package com.kitchen.system.mapper;

import java.util.List;

import com.kitchen.system.domain.MetricsKv;

/**
 * 数据监控键值Mapper接口
 *
 * @author ruoyi
 * @date 2023-03-01
 */
public interface MetricsKvMapper {
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
     * 删除数据监控键值
     *
     * @param id 数据监控键值主键
     * @return 结果
     */
    public int deleteMetricsKvById(Long id);

    /**
     * 批量删除数据监控键值
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMetricsKvByIds(Long[] ids);
}
