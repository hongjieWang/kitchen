package com.kitchen.system.consumer;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.kitchen.common.utils.DateUtils;
import com.kitchen.common.utils.bean.BeanUtils;
import com.kitchen.system.domain.MetricsKv;
import com.kitchen.system.domain.MetricsQpsDay;
import com.kitchen.system.domain.vo.MetricsKvVo;
import com.kitchen.system.service.IMetricsKvService;
import com.kitchen.system.service.IMetricsQpsDayService;
import org.apache.pulsar.client.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 消费数据
 *
 * @author wanghongjie
 */
@Component
public class MetricsConsumer {

    Logger logger = LoggerFactory.getLogger(MetricsConsumer.class);
    private static final PulsarClient CLIENT = PulsarFactory.getPulsarClient();

    private static final String KEY_SPLIT = "#";

    private static final String DEFAULT_KEY = "-";
    @Resource
    private IMetricsKvService metricsKvService;

    @Resource
    private IMetricsQpsDayService metricsQpsDayService;

    @PostConstruct
    public void start() {
        logger.info("MetricsConsumer is start !");
        consumer();
    }

    private void consumer() {
        MessageListener<byte[]> myMessageListener = (consumer, msg) -> {
            try {
                String message = new String(msg.getData());
                String[] split = message.split("\n");
                Arrays.stream(split).forEach(str -> {
                    MetricsKvVo kv = JSON.parseObject(str, MetricsKvVo.class);
                    MetricsKv metricsKv = new MetricsKv();
                    BeanUtils.copyProperties(kv, metricsKv);
                    metricsKvService.insertMetricsKv(metricsKv);
                    addQps(metricsKv);
                    addRt(metricsKv);
                });
            } catch (Exception ignored) {
            } finally {
                try {
                    consumer.acknowledge(msg);
                } catch (PulsarClientException ignored) {
                }
            }
        };
        if (PulsarFactory.autoEnable() && Objects.nonNull(CLIENT)) {
            try {
                CLIENT.newConsumer().topic(PulsarFactory.buildTopicUrl()).subscriptionName(PulsarFactory.subscriptionName()).messageListener(myMessageListener).subscriptionInitialPosition(SubscriptionInitialPosition.Earliest).subscribe();
            } catch (PulsarClientException ignored) {
            }
        }
    }

    private void addQps(MetricsKv metricsKv) {
        if ("QPS".equals(metricsKv.getLogType())) {
            String keyValue = metricsKv.getKeyValue();
            String[] split = keyValue.split(KEY_SPLIT);
            if (split.length == 3) {
                String key1 = split[0];
                String key2 = split[1];
                String key3 = split[2];
                createAndUpdate(metricsKv, key1, key2, key3);
            } else if (split.length == 2) {
                String key1 = split[0];
                String key2 = split[1];
                createAndUpdate(metricsKv, key1, key2, DEFAULT_KEY);
            } else if (split.length == 1) {
                String key1 = split[0];
                createAndUpdate(metricsKv, key1, DEFAULT_KEY, DEFAULT_KEY);
            }
        }
    }

    private void addRt(MetricsKv metricsKv) {
        if ("RT".equals(metricsKv.getLogType())) {
            String keyValue = metricsKv.getKeyValue();
            String[] split = keyValue.split(KEY_SPLIT);
            if (split.length == 3) {
                String key1 = split[0];
                String key2 = split[1];
                String key3 = split[2];
                createAndUpdateRt(metricsKv, key1, key2, key3);
            } else if (split.length == 2) {
                String key1 = split[0];
                String key2 = split[1];
                createAndUpdateRt(metricsKv, key1, key2, DEFAULT_KEY);
            } else if (split.length == 1) {
                String key1 = split[0];
                createAndUpdateRt(metricsKv, key1, DEFAULT_KEY, DEFAULT_KEY);
            }
        }
    }

    /**
     * 创建and更新当日数据
     *
     * @param metricsKv 上报数据
     * @param key1      key1
     * @param key2      key2
     * @param key3      key3
     */
    private void createAndUpdate(MetricsKv metricsKv, String key1, String key2, String key3) {
        List<MetricsQpsDay> metricsQpsDays = metricsQpsDayService.selectByTimeAndKeys(beginTime(), endTime(), "QPS", key1, key2, key3);
        if (Objects.isNull(metricsQpsDays) || metricsQpsDays.isEmpty()) {
            createMetricsQpsDay(metricsKv, key1, key2, key3, "QPS", "QPS");
        } else {
            MetricsQpsDay metricsQpsDay = metricsQpsDays.get(0);
            metricsQpsDay.setV1(metricsQpsDay.getV1() + metricsKv.getV1());
            metricsQpsDay.setUpdateTime(new Date());
            metricsQpsDayService.updateMetricsQpsDay(metricsQpsDay);
        }
    }

    /**
     * 创建and更新当日数据
     *
     * @param metricsKv 上报数据
     * @param key1      key1
     * @param key2      key2
     * @param key3      key3
     */
    private void createAndUpdateRt(MetricsKv metricsKv, String key1, String key2, String key3) {
        List<MetricsQpsDay> metricsQpsDays = metricsQpsDayService.selectByTimeAndKeys(beginTime(), endTime(), "RT", key1, key2, key3);
        if (Objects.isNull(metricsQpsDays) || metricsQpsDays.isEmpty()) {
            Long v2 = metricsKv.getV2();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("num", v2);
            jsonObject.put("min", metricsKv.getMinValue());
            jsonObject.put("max", metricsKv.getMaxValue());
            createMetricsQpsDay(metricsKv, key1, key2, key3, "RT", jsonObject.toString());
        } else {
            MetricsQpsDay metricsQpsDay = metricsQpsDays.get(0);
            String remark = metricsQpsDay.getRemark();
            JSONObject jsonObject = JSON.parseObject(remark);
            Long num = jsonObject.getLong("num");
            Long curMin = jsonObject.getLong("min");
            Long curMax = jsonObject.getLong("max");
            long allTime = metricsQpsDay.getV1() * num;
            long l = allTime + metricsKv.getV1() * metricsKv.getV2();
            long average = l / (num + metricsKv.getV2());

            long min = (curMin < 0L || metricsKv.getMinValue() < curMin) ? metricsKv.getMinValue() : curMin;
            long max = (curMax < 0L || curMax < metricsKv.getMaxValue()) ? metricsKv.getMaxValue() : curMax;

            jsonObject.put("num", num + metricsKv.getV2());
            jsonObject.put("min", min);
            jsonObject.put("max", max);

            metricsQpsDay.setV1(average);
            metricsQpsDay.setUpdateTime(new Date());
            metricsQpsDay.setRemark(jsonObject.toString());
            metricsQpsDayService.updateMetricsQpsDay(metricsQpsDay);
        }
    }

    private void createMetricsQpsDay(MetricsKv metricsKv, String key1, String key2, String key3, String type, String remark) {
        MetricsQpsDay metricsQpsDay = new MetricsQpsDay();
        metricsQpsDay.setEnvironment(metricsKv.getEnvironment());
        metricsQpsDay.setAppName(metricsKv.getAppName());
        metricsQpsDay.setV1(metricsKv.getV1());
        metricsQpsDay.setKeyOne(key1);
        metricsQpsDay.setKeyTwo(key2);
        metricsQpsDay.setKeyThird(key3);
        metricsQpsDay.setKeyValue(metricsKv.getKeyValue());
        metricsQpsDay.setTime(metricsKv.getTime());
        metricsQpsDay.setCreateTime(new Date());
        metricsQpsDay.setUpdateTime(new Date());
        metricsQpsDay.setLogType(type);
        metricsQpsDay.setRemark(remark);
        metricsQpsDayService.insertMetricsQpsDay(metricsQpsDay);
    }


    private Date beginTime() {
        String date = DateUtils.getDate();
        String concat = date.concat(" 00:00:00");
        try {
            return DateUtils.parseDate(concat, DateUtils.YYYY_MM_DD_HH_MM_SS);
        } catch (ParseException ignored) {
        }
        return new Date();
    }

    private Date endTime() {
        String date = DateUtils.getDate();
        String concat = date.concat(" 23:59:59");
        SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtils.YYYY_MM_DD_HH_MM_SS);
        try {
            return dateFormat.parse(concat);
        } catch (ParseException ignored) {
        }
        return new Date();
    }

}
