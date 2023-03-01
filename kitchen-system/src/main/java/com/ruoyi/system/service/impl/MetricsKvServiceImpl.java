package com.ruoyi.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.MetricsKvMapper;
import com.ruoyi.system.domain.MetricsKv;
import com.ruoyi.system.service.IMetricsKvService;

/**
 * 数据监控键值Service业务层处理
 *
 * @author ruoyi
 * @date 2023-03-01
 */
@Service
public class MetricsKvServiceImpl implements IMetricsKvService {
    @Autowired
    private MetricsKvMapper metricsKvMapper;

    /**
     * 查询数据监控键值
     *
     * @param id 数据监控键值主键
     * @return 数据监控键值
     */
    @Override
    public MetricsKv selectMetricsKvById(Long id) {
        return metricsKvMapper.selectMetricsKvById(id);
    }

    /**
     * 查询数据监控键值列表
     *
     * @param metricsKv 数据监控键值
     * @return 数据监控键值
     */
    @Override
    public List<MetricsKv> selectMetricsKvList(MetricsKv metricsKv) {
        return metricsKvMapper.selectMetricsKvList(metricsKv);
    }

    /**
     * 新增数据监控键值
     *
     * @param metricsKv 数据监控键值
     * @return 结果
     */
    @Override
    public int insertMetricsKv(MetricsKv metricsKv) {
        return metricsKvMapper.insertMetricsKv(metricsKv);
    }

    /**
     * 修改数据监控键值
     *
     * @param metricsKv 数据监控键值
     * @return 结果
     */
    @Override
    public int updateMetricsKv(MetricsKv metricsKv) {
        return metricsKvMapper.updateMetricsKv(metricsKv);
    }

    /**
     * 批量删除数据监控键值
     *
     * @param ids 需要删除的数据监控键值主键
     * @return 结果
     */
    @Override
    public int deleteMetricsKvByIds(Long[] ids) {
        return metricsKvMapper.deleteMetricsKvByIds(ids);
    }

    /**
     * 删除数据监控键值信息
     *
     * @param id 数据监控键值主键
     * @return 结果
     */
    @Override
    public int deleteMetricsKvById(Long id) {
        return metricsKvMapper.deleteMetricsKvById(id);
    }
}
