package com.kitchen.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.kitchen.common.annotation.Log;
import com.kitchen.common.core.controller.BaseController;
import com.kitchen.common.core.domain.AjaxResult;
import com.kitchen.common.core.page.TableDataInfo;
import com.kitchen.common.enums.BusinessType;
import com.kitchen.common.utils.poi.ExcelUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import com.kitchen.sdk.metrics.annotation.Metrics;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kitchen.system.domain.MetricsQpsDay;
import com.kitchen.system.service.IMetricsQpsDayService;


/**
 * 应用埋点Controller
 *
 * @author wanghongjie
 * @date 2023-04-03
 */
@RestController
@RequestMapping("/system/metricsQpsDay")
public class MetricsQpsDayController extends BaseController {
    @Resource
    private IMetricsQpsDayService metricsQpsDayService;

    /**
     * 查询应用埋点列表system::list
     */
    @PreAuthorize("@ss.hasPermi('system:metricsQpsDay:list')")
    @Metrics(value = {"XX服务", "应用埋点", "列表查询"})
    @GetMapping("/list")
    public TableDataInfo list(MetricsQpsDay metricsQpsDay) {
        startPage();
        List<MetricsQpsDay> list = metricsQpsDayService.selectMetricsQpsDayList(metricsQpsDay);
        return getDataTable(list);
    }

    /**
     * 导出应用埋点列表
     */
    @PreAuthorize("@ss.hasPermi('system:metricsQpsDay:export')")
    @Log(title = "应用埋点", businessType = BusinessType.EXPORT)
    @Metrics(value = {"XX服务", "应用埋点", "Excel导出"})
    @PostMapping("/export")
    public void export(HttpServletResponse response, MetricsQpsDay metricsQpsDay) {
        List<MetricsQpsDay> list = metricsQpsDayService.selectMetricsQpsDayList(metricsQpsDay);
        ExcelUtil<MetricsQpsDay> util = new ExcelUtil<MetricsQpsDay>(MetricsQpsDay.class);
        util.exportExcel(response, list, "应用埋点数据");
    }

    /**
     * 获取应用埋点详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:metricsQpsDay:query')")
    @Metrics(value = {"XX服务", "应用埋点", "查询详情"})
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(metricsQpsDayService.selectMetricsQpsDayById(id));
    }

    /**
     * 新增应用埋点
     */
    @PreAuthorize("@ss.hasPermi('system:metricsQpsDay:add')")
    @Log(title = "应用埋点", businessType = BusinessType.INSERT)
    @Metrics(value = {"XX服务", "应用埋点", "创建数据"})
    @PostMapping
    public AjaxResult add(@RequestBody MetricsQpsDay metricsQpsDay) {
        return toAjax(metricsQpsDayService.insertMetricsQpsDay(metricsQpsDay));
    }

    /**
     * 修改应用埋点
     */
    @PreAuthorize("@ss.hasPermi('system:metricsQpsDay:edit')")
    @Log(title = "应用埋点", businessType = BusinessType.UPDATE)
    @Metrics(value = {"XX服务", "应用埋点", "更新数据"})
    @PutMapping
    public AjaxResult edit(@RequestBody MetricsQpsDay metricsQpsDay) {
        return toAjax(metricsQpsDayService.updateMetricsQpsDay(metricsQpsDay));
    }

    /**
     * 删除应用埋点
     */
    @PreAuthorize("@ss.hasPermi('system:metricsQpsDay:remove')")
    @Log(title = "应用埋点", businessType = BusinessType.DELETE)
    @Metrics(value = {"XX服务", "应用埋点", "删除数据"})
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(metricsQpsDayService.deleteMetricsQpsDayByIds(ids));
    }
}
