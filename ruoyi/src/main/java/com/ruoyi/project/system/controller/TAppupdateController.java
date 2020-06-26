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
import com.ruoyi.project.system.domain.TAppupdate;
import com.ruoyi.project.system.service.ITAppupdateService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * app升级Controller
 * 
 * @author ruoyi
 * @date 2020-06-21
 */
@RestController
@RequestMapping("/system/appupdate")
public class TAppupdateController extends BaseController
{
    @Autowired
    private ITAppupdateService tAppupdateService;

    /**
     * 查询app升级列表
     */
    @GetMapping("/list")
    public Object list(TAppupdate tAppupdate)
    {
        startPage();
        List<TAppupdate> list = tAppupdateService.selectTAppupdateList(tAppupdate);
        if (null != list && list.size()>0){
            return list.get(0);
        }
        return getDataTable(list);
    }

    /**
     * 导出app升级列表
     */
    @Log(title = "app升级", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TAppupdate tAppupdate)
    {
        List<TAppupdate> list = tAppupdateService.selectTAppupdateList(tAppupdate);
        ExcelUtil<TAppupdate> util = new ExcelUtil<TAppupdate>(TAppupdate.class);
        return util.exportExcel(list, "appupdate");
    }

    /**
     * 获取app升级详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tAppupdateService.selectTAppupdateById(id));
    }

    /**
     * 新增app升级
     */
    @Log(title = "app升级", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TAppupdate tAppupdate)
    {
        return toAjax(tAppupdateService.insertTAppupdate(tAppupdate));
    }

    /**
     * 修改app升级
     */
    @Log(title = "app升级", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TAppupdate tAppupdate)
    {
        return toAjax(tAppupdateService.updateTAppupdate(tAppupdate));
    }

    /**
     * 删除app升级
     */
    @Log(title = "app升级", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tAppupdateService.deleteTAppupdateByIds(ids));
    }
}
