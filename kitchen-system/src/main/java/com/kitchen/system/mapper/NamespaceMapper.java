package com.kitchen.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kitchen.system.domain.Namespace;

import java.util.List;


/**
 * 命名空间Mapper接口
 *
 * @author wanghongjie
 * @date 2023-04-02
 */
public interface NamespaceMapper extends BaseMapper<Namespace> {
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
    public List<Namespace> selectNamespaceList(Namespace namespace);

    /**
     * 新增命名空间
     *
     * @param namespace 命名空间
     * @return 结果
     */
    public int insertNamespace(Namespace namespace);

    /**
     * 修改命名空间
     *
     * @param namespace 命名空间
     * @return 结果
     */
    public int updateNamespace(Namespace namespace);

    /**
     * 删除命名空间
     *
     * @param id 命名空间主键
     * @return 结果
     */
    public int deleteNamespaceById(Long id);

    /**
     * 批量删除命名空间
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNamespaceByIds(Long[] ids);
}
