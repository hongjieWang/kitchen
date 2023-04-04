package com.kitchen.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kitchen.system.domain.KeyConfig;
import com.kitchen.system.mapper.KeyConfigMapper;
import com.kitchen.system.service.IKeyConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * key翻译Service业务层处理
 *
 * @author wanghongjie
 * @date 2023-04-02
 */
@Service
public class KeyConfigServiceImpl extends ServiceImpl<KeyConfigMapper, KeyConfig> implements IKeyConfigService {
    @Resource
    private KeyConfigMapper keyConfigMapper;

    /**
     * 查询key翻译
     *
     * @param id key翻译主键
     * @return key翻译
     */
    @Override
    public KeyConfig selectKeyConfigById(Long id) {
        return keyConfigMapper.selectKeyConfigById(id);
    }

    /**
     * 查询key翻译列表
     *
     * @param keyConfig key翻译
     * @return key翻译
     */
    @Override
    public List<KeyConfig> selectKeyConfigList(KeyConfig keyConfig) {
        return keyConfigMapper.selectKeyConfigList(keyConfig);
    }

    /**
     * 新增key翻译
     *
     * @param keyConfig key翻译
     * @return 结果
     */
    @Override
    public int insertKeyConfig(KeyConfig keyConfig) {
        keyConfig.setCreateTime(new Date());
        String keyValues = keyConfig.getKeyValues();
        String[] split = keyValues.split("#");
        if (split.length == 3) {
            keyConfig.setKeyOne(split[0]);
            keyConfig.setKeyTwo(split[1]);
            keyConfig.setKeyThird(split[2]);
        } else if (split.length == 2) {
            keyConfig.setKeyOne(split[0]);
            keyConfig.setKeyTwo(split[1]);
            keyConfig.setKeyThird("-");
        } else if (split.length == 1) {
            keyConfig.setKeyOne(split[0]);
            keyConfig.setKeyTwo("-");
            keyConfig.setKeyThird("-");
        }
        return keyConfigMapper.insertKeyConfig(keyConfig);
    }

    /**
     * 修改key翻译
     *
     * @param keyConfig key翻译
     * @return 结果
     */
    @Override
    public int updateKeyConfig(KeyConfig keyConfig) {
        keyConfig.setUpdateTime(new Date());
        return keyConfigMapper.updateKeyConfig(keyConfig);
    }

    /**
     * 批量删除key翻译
     *
     * @param ids 需要删除的key翻译主键
     * @return 结果
     */
    @Override
    public int deleteKeyConfigByIds(Long[] ids) {
        return keyConfigMapper.deleteKeyConfigByIds(ids);
    }

    /**
     * 删除key翻译信息
     *
     * @param id key翻译主键
     * @return 结果
     */
    @Override
    public int deleteKeyConfigById(Long id) {
        return keyConfigMapper.deleteKeyConfigById(id);
    }
}
