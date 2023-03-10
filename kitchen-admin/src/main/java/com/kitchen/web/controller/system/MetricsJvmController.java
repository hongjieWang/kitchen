package com.kitchen.web.controller.system;

import java.util.List;
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
import com.kitchen.system.domain.MetricsJvm;
import com.kitchen.system.service.IMetricsJvmService;
import com.kitchen.common.utils.poi.ExcelUtil;
import com.kitchen.common.core.page.TableDataInfo;

/**
 * 数据监控JVM参数Controller
 *
 * @author ruoyi
 * @date 2023-03-01
 */
@RestController
@RequestMapping("/system/jvm")
public class MetricsJvmController extends BaseController {
    @Autowired
    private IMetricsJvmService metricsJvmService;

    /**
     * 查询数据监控JVM参数列表
     */
    @PreAuthorize("@ss.hasPermi('system:jvm:list')")
    @GetMapping("/list")
    public TableDataInfo list(MetricsJvm metricsJvm) {
        startPage();
        List<MetricsJvm> list = metricsJvmService.selectMetricsJvmList(metricsJvm);
        return getDataTable(list);
    }

    /**
     * 导出数据监控JVM参数列表
     */
    @PreAuthorize("@ss.hasPermi('system:jvm:export')")
    @Log(title = "数据监控JVM参数", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MetricsJvm metricsJvm) {
        List<MetricsJvm> list = metricsJvmService.selectMetricsJvmList(metricsJvm);
        ExcelUtil<MetricsJvm> util = new ExcelUtil<MetricsJvm>(MetricsJvm.class);
        util.exportExcel(response, list, "数据监控JVM参数数据");
    }

    /**
     * 获取数据监控JVM参数详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:jvm:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(metricsJvmService.selectMetricsJvmById(id));
    }

    /**
     * 新增数据监控JVM参数
     */
    @PreAuthorize("@ss.hasPermi('system:jvm:add')")
    @Log(title = "数据监控JVM参数", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MetricsJvm metricsJvm) {
        return toAjax(metricsJvmService.insertMetricsJvm(metricsJvm));
    }

    /**
     * 修改数据监控JVM参数
     */
    @PreAuthorize("@ss.hasPermi('system:jvm:edit')")
    @Log(title = "数据监控JVM参数", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MetricsJvm metricsJvm) {
        return toAjax(metricsJvmService.updateMetricsJvm(metricsJvm));
    }

    /**
     * 删除数据监控JVM参数
     */
    @PreAuthorize("@ss.hasPermi('system:jvm:remove')")
    @Log(title = "数据监控JVM参数", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(metricsJvmService.deleteMetricsJvmByIds(ids));
    }
}
