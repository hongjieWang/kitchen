package com.kitchen.system.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kitchen.system.domain.KeyConfig;

/**
 * key翻译Service接口
 *
 * @author wanghongjie
 * @date 2023-04-02
 */
public interface IKeyConfigService extends IService<KeyConfig> {
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
    List<KeyConfig> selectKeyConfigList(KeyConfig keyConfig);

    /**
     * 新增key翻译
     *
     * @param keyConfig key翻译
     * @return 结果
     */
    int insertKeyConfig(KeyConfig keyConfig);

    /**
     * 修改key翻译
     *
     * @param keyConfig key翻译
     * @return 结果
     */
    int updateKeyConfig(KeyConfig keyConfig);

    /**
     * 批量删除key翻译
     *
     * @param ids 需要删除的key翻译主键集合
     * @return 结果
     */
    int deleteKeyConfigByIds(Long[] ids);

    /**
     * 删除key翻译信息
     *
     * @param id key翻译主键
     * @return 结果
     */
    int deleteKeyConfigById(Long id);
}
