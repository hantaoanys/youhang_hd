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
import com.ruoyi.project.system.domain.TPrivacy;
import com.ruoyi.project.system.service.ITPrivacyService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 隐私政策Controller
 * 
 * @author ruoyi
 * @date 2020-05-27
 */
@RestController
@RequestMapping("/system/privacy")
public class TPrivacyController extends BaseController
{
    @Autowired
    private ITPrivacyService tPrivacyService;

    /**
     * 查询隐私政策列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TPrivacy tPrivacy)
    {
        startPage();
        List<TPrivacy> list = tPrivacyService.selectTPrivacyList(tPrivacy);
        return getDataTable(list);
    }

    /**
     * 导出隐私政策列表
     */
    @Log(title = "隐私政策", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TPrivacy tPrivacy)
    {
        List<TPrivacy> list = tPrivacyService.selectTPrivacyList(tPrivacy);
        ExcelUtil<TPrivacy> util = new ExcelUtil<TPrivacy>(TPrivacy.class);
        return util.exportExcel(list, "privacy");
    }

    /**
     * 获取隐私政策详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tPrivacyService.selectTPrivacyById(id));
    }

    /**
     * 新增隐私政策
     */
    @Log(title = "隐私政策", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TPrivacy tPrivacy)
    {
        return toAjax(tPrivacyService.insertTPrivacy(tPrivacy));
    }

    /**
     * 修改隐私政策
     */
    @Log(title = "隐私政策", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TPrivacy tPrivacy)
    {
        return toAjax(tPrivacyService.updateTPrivacy(tPrivacy));
    }

    /**
     * 删除隐私政策
     */
    @Log(title = "隐私政策", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tPrivacyService.deleteTPrivacyByIds(ids));
    }
}
