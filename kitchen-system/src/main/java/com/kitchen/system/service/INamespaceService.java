package com.kitchen.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kitchen.system.domain.Namespace;

import java.util.List;


/**
 * 命名空间Service接口
 *
 * @author ruoyi
 * @date 2023-04-02
 */
public interface INamespaceService extends IService<Namespace> {
    /**
     * 查询命名空间
     *
     * @param id 命名空间主键
     * @return 命名空间
     */
    public Namespace selectNamespaceById(Long id);

    /**
     * 查询命名空间列表
     *
     * @param namespace 命名空间
     * @return 命名空间集合
     */
    List<Namespace> selectNamespaceList(Namespace namespace);

    /**
     * 新增命名空间
     *
     * @param namespace 命名空间
     * @return 结果
     */
    int insertNamespace(Namespace namespace);

    /**
     * 修改命名空间
     *
     * @param namespace 命名空间
     * @return 结果
     */
    int updateNamespace(Namespace namespace);

    /**
     * 批量删除命名空间
     *
     * @param ids 需要删除的命名空间主键集合
     * @return 结果
     */
    int deleteNamespaceByIds(Long[] ids);

    /**
     * 删除命名空间信息
     *
     * @param id 命名空间主键
     * @return 结果
     */
    int deleteNamespaceById(Long id);
}
