package com.kitchen.web.controller.system;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kitchen.common.annotation.Log;
import com.kitchen.common.core.controller.BaseController;
import com.kitchen.common.core.domain.AjaxResult;
import com.kitchen.common.enums.BusinessType;
import com.kitchen.system.domain.MetricsKv;
import com.kitchen.system.service.IMetricsKvService;
import com.kitchen.common.utils.poi.ExcelUtil;
import com.kitchen.common.core.page.TableDataInfo;

/**
 * 数据监控键值Controller
 *
 * @author wanghongjie
 * @date 2023-03-01
 */
@RestController
@RequestMapping("/system/kv")
public class MetricsKvController extends BaseController {
    @Resource
    private IMetricsKvService metricsKvService;

    /**
     * 查询数据监控键值列表
     */
    @PreAuthorize("@ss.hasPermi('system:kv:list')")
    @GetMapping("/list")
    public TableDataInfo list(MetricsKv metricsKv) {
        startPage();
        List<MetricsKv> list = metricsKvService.selectMetricsKvList(metricsKv);
        return getDataTable(list);
    }

    /**
     * 导出数据监控键值列表
     */
    @PreAuthorize("@ss.hasPermi('system:kv:export')")
    @Log(title = "数据监控键值", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MetricsKv metricsKv) {
        List<MetricsKv> list = metricsKvService.selectMetricsKvList(metricsKv);
        ExcelUtil<MetricsKv> util = new ExcelUtil<MetricsKv>(MetricsKv.class);
        util.exportExcel(response, list, "数据监控键值数据");
    }

    /**
     * 获取数据监控键值详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:kv:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(metricsKvService.selectMetricsKvById(id));
    }

    /**
     * 新增数据监控键值
     */
    @PreAuthorize("@ss.hasPermi('system:kv:add')")
    @Log(title = "数据监控键值", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MetricsKv metricsKv) {
        return toAjax(metricsKvService.insertMetricsKv(metricsKv));
    }

    /**
     * 修改数据监控键值
     */
    @PreAuthorize("@ss.hasPermi('system:kv:edit')")
    @Log(title = "数据监控键值", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MetricsKv metricsKv) {
        return toAjax(metricsKvService.updateMetricsKv(metricsKv));
    }

    /**
     * 删除数据监控键值
     */
    @PreAuthorize("@ss.hasPermi('system:kv:remove')")
    @Log(title = "数据监控键值", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(metricsKvService.deleteMetricsKvByIds(ids));
    }
}
