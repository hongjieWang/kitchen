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

import com.kitchen.system.domain.KeyConfig;
import com.kitchen.system.service.IKeyConfigService;

/**
 * key翻译Controller
 *
 * @author wanghongjie
 * @date 2023-04-02
 */
@RestController
@RequestMapping("/system/config" )
public class KeyConfigController extends BaseController {
    @Resource
    private IKeyConfigService keyConfigService;

    /**
     * 查询key翻译列表
     */
    @PreAuthorize("@ss.hasPermi('system:config:list')" )
    @Metrics(value = {"XX服务", "key翻译", "列表查询"})
    @GetMapping("/list" )
    public TableDataInfo list(KeyConfig keyConfig) {
        startPage();
        List<KeyConfig> list = keyConfigService.selectKeyConfigList(keyConfig);
        return getDataTable(list);
    }

    /**
     * 导出key翻译列表
     */
    @PreAuthorize("@ss.hasPermi('system:config:export')" )
    @Log(title = "key翻译", businessType = BusinessType.EXPORT)
    @Metrics(value = {"XX服务", "key翻译", "Excel导出"})
    @PostMapping("/export" )
    public void export(HttpServletResponse response, KeyConfig keyConfig) {
        List<KeyConfig> list = keyConfigService.selectKeyConfigList(keyConfig);
        ExcelUtil<KeyConfig> util = new ExcelUtil<KeyConfig>(KeyConfig.class);
        util.exportExcel(response, list, "key翻译数据" );
    }

    /**
     * 获取key翻译详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:config:query')" )
    @Metrics(value = {"XX服务", "key翻译", "查询详情"})
    @GetMapping(value = "/{id}" )
    public AjaxResult getInfo(@PathVariable("id" ) Long id) {
        return AjaxResult.success(keyConfigService.selectKeyConfigById(id));
    }

    /**
     * 新增key翻译
     */
    @PreAuthorize("@ss.hasPermi('system:config:add')" )
    @Log(title = "key翻译", businessType = BusinessType.INSERT)
    @Metrics(value = {"XX服务", "key翻译", "创建数据"})
    @PostMapping
    public AjaxResult add(@RequestBody KeyConfig keyConfig) {
        return toAjax(keyConfigService.insertKeyConfig(keyConfig));
    }

    /**
     * 修改key翻译
     */
    @PreAuthorize("@ss.hasPermi('system:config:edit')" )
    @Log(title = "key翻译", businessType = BusinessType.UPDATE)
    @Metrics(value = {"XX服务", "key翻译", "更新数据"})
    @PutMapping
    public AjaxResult edit(@RequestBody KeyConfig keyConfig) {
        return toAjax(keyConfigService.updateKeyConfig(keyConfig));
    }

    /**
     * 删除key翻译
     */
    @PreAuthorize("@ss.hasPermi('system:config:remove')" )
    @Log(title = "key翻译", businessType = BusinessType.DELETE)
    @Metrics(value = {"XX服务", "key翻译", "删除数据"})
    @DeleteMapping("/{ids}" )
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(keyConfigService.deleteKeyConfigByIds(ids));
    }
}
