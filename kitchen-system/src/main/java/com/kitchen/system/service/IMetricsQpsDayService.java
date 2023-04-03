package com.kitchen.system.service;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kitchen.system.domain.MetricsQpsDay;

/**
 * 应用埋点Service接口
 *
 * @author wanghongjie
 * @date 2023-04-03
 */
public interface IMetricsQpsDayService extends IService<MetricsQpsDay> {
    /**
     * 查询应用埋点
     *
     * @param id 应用埋点主键
     * @return 应用埋点
     */
    public MetricsQpsDay selectMetricsQpsDayById(Long id);


    /**
     * 查询监控数据
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param type      类型
     * @param key1      key1
     * @param key2      key2
     * @param key3      key3
     * @return
     */
    List<MetricsQpsDay> selectByTimeAndKeys(Date startTime, Date endTime, String type, String key1, String key2, String key3);

    /**
     * 查询应用埋点列表
     *
     * @param metricsQpsDay 应用埋点
     * @return 应用埋点集合
     */
    List<MetricsQpsDay> selectMetricsQpsDayList(MetricsQpsDay metricsQpsDay);

    /**
     * 新增应用埋点
     *
     * @param metricsQpsDay 应用埋点
     * @return 结果
     */
    int insertMetricsQpsDay(MetricsQpsDay metricsQpsDay);

    /**
     * 修改应用埋点
     *
     * @param metricsQpsDay 应用埋点
     * @return 结果
     */
    int updateMetricsQpsDay(MetricsQpsDay metricsQpsDay);

    /**
     * 批量删除应用埋点
     *
     * @param ids 需要删除的应用埋点主键集合
     * @return 结果
     */
    int deleteMetricsQpsDayByIds(Long[] ids);

    /**
     * 删除应用埋点信息
     *
     * @param id 应用埋点主键
     * @return 结果
     */
    int deleteMetricsQpsDayById(Long id);


}
