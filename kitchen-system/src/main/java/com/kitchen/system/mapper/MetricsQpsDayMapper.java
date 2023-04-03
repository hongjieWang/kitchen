package com.kitchen.system.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kitchen.system.domain.MetricsQpsDay;

/**
 * 应用埋点Mapper接口
 *
 * @author wanghongjie
 * @date 2023-04-03
 */
public interface MetricsQpsDayMapper extends BaseMapper<MetricsQpsDay> {
    /**
     * 查询应用埋点
     *
     * @param id 应用埋点主键
     * @return 应用埋点
     */
    public MetricsQpsDay selectMetricsQpsDayById(Long id);

    /**
     * 查询应用埋点列表
     *
     * @param metricsQpsDay 应用埋点
     * @return 应用埋点集合
     */
    public List<MetricsQpsDay> selectMetricsQpsDayList(MetricsQpsDay metricsQpsDay);

    /**
     * 新增应用埋点
     *
     * @param metricsQpsDay 应用埋点
     * @return 结果
     */
    public int insertMetricsQpsDay(MetricsQpsDay metricsQpsDay);

    /**
     * 修改应用埋点
     *
     * @param metricsQpsDay 应用埋点
     * @return 结果
     */
    public int updateMetricsQpsDay(MetricsQpsDay metricsQpsDay);

    /**
     * 删除应用埋点
     *
     * @param id 应用埋点主键
     * @return 结果
     */
    public int deleteMetricsQpsDayById(Long id);

    /**
     * 批量删除应用埋点
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMetricsQpsDayByIds(Long[] ids);
}
