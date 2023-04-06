package com.kitchen.system.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kitchen.common.utils.DateUtils;
import com.kitchen.system.domain.Namespace;
import com.kitchen.system.mapper.NamespaceMapper;
import com.kitchen.system.service.INamespaceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 命名空间Service业务层处理
 *
 * @author wanghongjie
 * @date 2023-04-02
 */
@Service
public class NamespaceServiceImpl extends ServiceImpl<NamespaceMapper, Namespace> implements INamespaceService {
    @Resource
    private NamespaceMapper namespaceMapper;

    /**
     * 查询命名空间
     *
     * @param id 命名空间主键
     * @return 命名空间
     */
    @Override
    public Namespace selectNamespaceById(Long id) {
        return namespaceMapper.selectNamespaceById(id);
    }

    /**
     * 查询命名空间列表
     *
     * @param namespace 命名空间
     * @return 命名空间
     */
    @Override
    public List<Namespace> selectNamespaceList(Namespace namespace) {
        return namespaceMapper.selectNamespaceList(namespace);
    }

    /**
     * 新增命名空间
     *
     * @param namespace 命名空间
     * @return 结果
     */
    @Override
    public int insertNamespace(Namespace namespace) {
        namespace.setCreateTime(DateUtils.getNowDate());
        return namespaceMapper.insertNamespace(namespace);
    }

    /**
     * 修改命名空间
     *
     * @param namespace 命名空间
     * @return 结果
     */
    @Override
    public int updateNamespace(Namespace namespace) {
        namespace.setUpdateTime(DateUtils.getNowDate());
        return namespaceMapper.updateNamespace(namespace);
    }

    /**
     * 批量删除命名空间
     *
     * @param ids 需要删除的命名空间主键
     * @return 结果
     */
    @Override
    public int deleteNamespaceByIds(Long[] ids) {
        return namespaceMapper.deleteNamespaceByIds(ids);
    }

    /**
     * 删除命名空间信息
     *
     * @param id 命名空间主键
     * @return 结果
     */
    @Override
    public int deleteNamespaceById(Long id) {
        return namespaceMapper.deleteNamespaceById(id);
    }
}
