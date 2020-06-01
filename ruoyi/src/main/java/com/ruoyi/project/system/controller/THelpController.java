package com.ruoyi.project.system.controller;

import java.util.List;
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
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.system.domain.THelp;
import com.ruoyi.project.system.service.ITHelpService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 帮助中心Controller
 * 
 * @author ruoyi
 * @date 2020-05-27
 */
@RestController
@RequestMapping("/system/help")
public class THelpController extends BaseController
{
    @Autowired
    private ITHelpService tHelpService;

    /**
     * 查询帮助中心列表
     */
    @GetMapping("/list")
    public TableDataInfo list(THelp tHelp)
    {
        startPage();
        List<THelp> list = tHelpService.selectTHelpList(tHelp);
        return getDataTable(list);
    }

    /**
     * 导出帮助中心列表
     */
    @Log(title = "帮助中心", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(THelp tHelp)
    {
        List<THelp> list = tHelpService.selectTHelpList(tHelp);
        ExcelUtil<THelp> util = new ExcelUtil<THelp>(THelp.class);
        return util.exportExcel(list, "help");
    }

    /**
     * 获取帮助中心详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tHelpService.selectTHelpById(id));
    }

    /**
     * 新增帮助中心
     */
    @Log(title = "帮助中心", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody THelp tHelp)
    {
        return toAjax(tHelpService.insertTHelp(tHelp));
    }

    /**
     * 修改帮助中心
     */
    @Log(title = "帮助中心", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody THelp tHelp)
    {
        return toAjax(tHelpService.updateTHelp(tHelp));
    }

    /**
     * 删除帮助中心
     */
    @Log(title = "帮助中心", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tHelpService.deleteTHelpByIds(ids));
    }
}
