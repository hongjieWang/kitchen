package com.kitchen.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kitchen.system.domain.KeyConfig;

/**
 * key翻译Mapper接口
 *
 * @author wanghongjie
 * @date 2023-04-02
 */
public interface KeyConfigMapper extends BaseMapper<KeyConfig> {
    /**
     * 查询key翻译
     *
     * @param id key翻译主键
     * @return key翻译
     */
    public KeyConfig selectKeyConfigById(Long id);

    /**
     * 查询key翻译列表
     *
     * @param keyConfig key翻译
     * @return key翻译集合
     */
    public List<KeyConfig> selectKeyConfigList(KeyConfig keyConfig);

    /**
     * 新增key翻译
     *
     * @param keyConfig key翻译
     * @return 结果
     */
    public int insertKeyConfig(KeyConfig keyConfig);

    /**
     * 修改key翻译
     *
     * @param keyConfig key翻译
     * @return 结果
     */
    public int updateKeyConfig(KeyConfig keyConfig);

    /**
     * 删除key翻译
     *
     * @param id key翻译主键
     * @return 结果
     */
    public int deleteKeyConfigById(Long id);

    /**
     * 批量删除key翻译
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteKeyConfigByIds(Long[] ids);
}
