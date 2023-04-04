package com.kitchen.system.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kitchen.common.utils.DateUtils;
import com.kitchen.common.utils.StringUtils;
import com.kitchen.system.domain.KeyConfig;
import com.kitchen.system.domain.MetricsQpsDay;
import com.kitchen.system.mapper.KeyConfigMapper;
import com.kitchen.system.mapper.MetricsQpsDayMapper;
import com.kitchen.system.service.IMetricsQpsDayService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.*;

/**
 * 应用埋点Service业务层处理
 *
 * @author wanghongjie
 * @date 2023-04-03
 */
@Service
public class MetricsQpsDayServiceImpl extends ServiceImpl<MetricsQpsDayMapper, MetricsQpsDay> implements IMetricsQpsDayService {
    @Resource
    private MetricsQpsDayMapper metricsQpsDayMapper;

    @Resource
    private KeyConfigMapper keyConfigMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 查询应用埋点
     *
     * @param id 应用埋点主键
     * @return 应用埋点
     */
    @Override
    public MetricsQpsDay selectMetricsQpsDayById(Long id) {
        return metricsQpsDayMapper.selectMetricsQpsDayById(id);
    }

    @Override
    public List<MetricsQpsDay> selectByTimeAndKeys(Date startTime, Date endTime, String type, String key1, String key2, String key3) {
        LambdaQueryWrapper<MetricsQpsDay> wrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(startTime) && Objects.nonNull(endTime)) {
            wrapper.gt(MetricsQpsDay::getTime, startTime).lt(MetricsQpsDay::getTime, endTime);
        }
        if (StringUtils.isNotEmpty(type)) {
            wrapper.eq(MetricsQpsDay::getLogType, type);
        }
        if (StringUtils.isNotEmpty(key1)) {
            wrapper.eq(MetricsQpsDay::getKeyOne, key1);
        }
        if (StringUtils.isNotEmpty(key2)) {
            wrapper.eq(MetricsQpsDay::getKeyTwo, key2);
        }
        if (StringUtils.isNotEmpty(key3)) {
            wrapper.eq(MetricsQpsDay::getKeyThird, key3);
        }
        return metricsQpsDayMapper.selectList(wrapper);
    }

    /**
     * 查询应用埋点列表
     *
     * @param metricsQpsDay 应用埋点
     * @return 应用埋点
     */
    @Override
    public List<MetricsQpsDay> selectMetricsQpsDayList(MetricsQpsDay metricsQpsDay) {
        List<KeyConfig> keyConfigs = keyConfigMapper.selectKeyConfigList(new KeyConfig());
        List<MetricsQpsDay> metricsQpsDays = new ArrayList<>(keyConfigs.size());
        Map<String, Object> params = metricsQpsDay.getParams();
        if (params.size() == 0) {
            params = new HashMap<>(8);
            params.put("beginTime", DateUtils.getDate());
            params.put("endTime", DateUtils.getDate());
            metricsQpsDay.setParams(params);
        }
        keyConfigs.forEach(keyConfig -> {
            metricsQpsDay.setKeyValue(keyConfig.getKeyValues());
            List<MetricsQpsDay> dbMetricsQpsDays = metricsQpsDayMapper.selectMetricsQpsDayList(metricsQpsDay);
            if (dbMetricsQpsDays.isEmpty()) {
                MetricsQpsDay newMetricsQpsDay = new MetricsQpsDay();
                newMetricsQpsDay.setKeyOne(keyConfig.getKeyOne());
                newMetricsQpsDay.setKeyTwo(keyConfig.getKeyTwo());
                newMetricsQpsDay.setKeyThird(keyConfig.getKeyThird());
                newMetricsQpsDay.setV1(0L);
                newMetricsQpsDay.setEnvironment("-");
                metricsQpsDays.add(newMetricsQpsDay);
            } else {
                MetricsQpsDay metricsQps = dbMetricsQpsDays.get(0);
                if (StringUtils.isNotEmpty(metricsQps.getRemark())) {
                    JSONObject jsonObject = JSON.parseObject(metricsQps.getRemark());
                    metricsQps.setMin(jsonObject.getLong("min"));
                    metricsQps.setMax(jsonObject.getLong("max"));
                }
                metricsQpsDays.add(metricsQps);
            }
        });
        getLastDay(metricsQpsDays);
        return metricsQpsDays;
    }

    /**
     * 计算昨日数据
     *
     * @param metricsQpsDays 请求数据
     */
    private void getLastDay(List<MetricsQpsDay> metricsQpsDays) {
        metricsQpsDays.forEach(metricsQps -> {
            List<MetricsQpsDay> metricsQpsLastDays;
            String metricsQpsLastDaysStr = stringRedisTemplate.opsForValue().get(buildKey(beginTime(), endTime(), metricsQps.getLogType(), metricsQps.getKeyOne(), metricsQps.getKeyTwo(), metricsQps.getKeyThird()));
            if (StringUtils.isNotEmpty(metricsQpsLastDaysStr)) {
                metricsQpsLastDays = JSON.parseArray(metricsQpsLastDaysStr, MetricsQpsDay.class);
            } else {
                metricsQpsLastDays = selectByTimeAndKeys(beginTime(), endTime(), metricsQps.getLogType(), metricsQps.getKeyOne(), metricsQps.getKeyTwo(), metricsQps.getKeyThird());
                stringRedisTemplate.opsForValue().set(buildKey(beginTime(), endTime(), metricsQps.getLogType(), metricsQps.getKeyOne(), metricsQps.getKeyTwo(), metricsQps.getKeyThird()), JSON.toJSONString(metricsQpsLastDays));
            }
            if (Objects.isNull(metricsQpsLastDays) || metricsQpsLastDays.isEmpty()) {
                metricsQps.setV2(0L);
            } else {
                metricsQps.setV2(metricsQpsLastDays.get(0).getV1());
            }
        });
    }

    /**
     * 新增应用埋点
     *
     * @param metricsQpsDay 应用埋点
     * @return 结果
     */
    @Override
    public int insertMetricsQpsDay(MetricsQpsDay metricsQpsDay) {
        return metricsQpsDayMapper.insertMetricsQpsDay(metricsQpsDay);
    }

    /**
     * 修改应用埋点
     *
     * @param metricsQpsDay 应用埋点
     * @return 结果
     */
    @Override
    public int updateMetricsQpsDay(MetricsQpsDay metricsQpsDay) {
        return metricsQpsDayMapper.updateMetricsQpsDay(metricsQpsDay);
    }

    /**
     * 批量删除应用埋点
     *
     * @param ids 需要删除的应用埋点主键
     * @return 结果
     */
    @Override
    public int deleteMetricsQpsDayByIds(Long[] ids) {
        return metricsQpsDayMapper.deleteMetricsQpsDayByIds(ids);
    }

    /**
     * 删除应用埋点信息
     *
     * @param id 应用埋点主键
     * @return 结果
     */
    @Override
    public int deleteMetricsQpsDayById(Long id) {
        return metricsQpsDayMapper.deleteMetricsQpsDayById(id);
    }

    public String buildKey(Date startTime, Date endTime, String type, String key1, String key2, String key3) {
        return String.format("%s%s%s%s%s%s%s", "kitchen:metrics:qps:day:", type, key1, key2, key3, startTime.getTime(), endTime.getTime());
    }

    private Date beginTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        Date time = calendar.getTime();
        String date = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, time);
        String concat = date.concat(" 00:00:00");
        try {
            return DateUtils.parseDate(concat, DateUtils.YYYY_MM_DD_HH_MM_SS);
        } catch (ParseException ignored) {
        }
        return new Date();
    }

    private Date endTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        Date time = calendar.getTime();
        String date = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, time);
        String concat = date.concat(" 23:59:59");
        try {
            return DateUtils.parseDate(concat, DateUtils.YYYY_MM_DD_HH_MM_SS);
        } catch (ParseException ignored) {
        }
        return new Date();
    }
}
