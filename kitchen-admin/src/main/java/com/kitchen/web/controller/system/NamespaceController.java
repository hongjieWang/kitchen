package com.kitchen.web.controller.system;

import com.kitchen.common.annotation.Log;
import com.kitchen.common.core.controller.BaseController;
import com.kitchen.common.core.domain.AjaxResult;
import com.kitchen.common.core.page.TableDataInfo;
import com.kitchen.common.enums.BusinessType;
import com.kitchen.common.utils.poi.ExcelUtil;
import com.kitchen.sdk.metrics.annotation.Metrics;
import com.kitchen.system.domain.Namespace;
import com.kitchen.system.service.INamespaceService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 命名空间Controller
 *
 * @author ruoyi
 * @date 2023-04-02
 */
@RestController
@RequestMapping("/system/namespace" )
public class NamespaceController extends BaseController {
    @Resource
    private INamespaceService namespaceService;

    /**
     * 查询命名空间列表
     */
    @PreAuthorize("@ss.hasPermi('system:namespace:list')" )
    @Metrics(value = {"XX服务", "命名空间", "列表查询"})
    @GetMapping("/list" )
    public TableDataInfo list(Namespace namespace) {
        startPage();
        List<Namespace> list = namespaceService.selectNamespaceList(namespace);
        return getDataTable(list);
    }

    /**
     * 导出命名空间列表
     */
    @PreAuthorize("@ss.hasPermi('system:namespace:export')" )
    @Log(title = "命名空间", businessType = BusinessType.EXPORT)
    @Metrics(value = {"XX服务", "命名空间", "Excel导出"})
    @PostMapping("/export" )
    public void export(HttpServletResponse response, Namespace namespace) {
        List<Namespace> list = namespaceService.selectNamespaceList(namespace);
        ExcelUtil<Namespace> util = new ExcelUtil<Namespace>(Namespace.class);
        util.exportExcel(response, list, "命名空间数据" );
    }

    /**
     * 获取命名空间详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:namespace:query')" )
    @Metrics(value = {"XX服务", "命名空间", "查询详情"})
    @GetMapping(value = "/{id}" )
    public AjaxResult getInfo(@PathVariable("id" ) Long id) {
        return AjaxResult.success(namespaceService.selectNamespaceById(id));
    }

    /**
     * 新增命名空间
     */
    @PreAuthorize("@ss.hasPermi('system:namespace:add')" )
    @Log(title = "命名空间", businessType = BusinessType.INSERT)
    @Metrics(value = {"XX服务", "命名空间", "创建数据"})
    @PostMapping
    public AjaxResult add(@RequestBody Namespace namespace) {
        return toAjax(namespaceService.insertNamespace(namespace));
    }

    /**
     * 修改命名空间
     */
    @PreAuthorize("@ss.hasPermi('system:namespace:edit')" )
    @Log(title = "命名空间", businessType = BusinessType.UPDATE)
    @Metrics(value = {"XX服务", "命名空间", "更新数据"})
    @PutMapping
    public AjaxResult edit(@RequestBody Namespace namespace) {
        return toAjax(namespaceService.updateNamespace(namespace));
    }

    /**
     * 删除命名空间
     */
    @PreAuthorize("@ss.hasPermi('system:namespace:remove')" )
    @Log(title = "命名空间", businessType = BusinessType.DELETE)
    @Metrics(value = {"XX服务", "命名空间", "删除数据"})
    @DeleteMapping("/{ids}" )
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(namespaceService.deleteNamespaceByIds(ids));
    }
}
