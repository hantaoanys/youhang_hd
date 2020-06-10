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
import com.ruoyi.project.system.domain.TUserInvitegoods;
import com.ruoyi.project.system.service.ITUserInvitegoodsService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 邀请流水Controller
 * 
 * @author ruoyi
 * @date 2020-06-10
 */
@RestController
@RequestMapping("/system/invitegoods")
public class TUserInvitegoodsController extends BaseController
{
    @Autowired
    private ITUserInvitegoodsService tUserInvitegoodsService;

    /**
     * 查询邀请流水列表
     */
    @PreAuthorize("@ss.hasPermi('system:invitegoods:list')")
    @GetMapping("/list")
    public TableDataInfo list(TUserInvitegoods tUserInvitegoods)
    {
        startPage();
        List<TUserInvitegoods> list = tUserInvitegoodsService.selectTUserInvitegoodsList(tUserInvitegoods);
        return getDataTable(list);
    }

    /**
     * 导出邀请流水列表
     */
    @PreAuthorize("@ss.hasPermi('system:invitegoods:export')")
    @Log(title = "邀请流水", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TUserInvitegoods tUserInvitegoods)
    {
        List<TUserInvitegoods> list = tUserInvitegoodsService.selectTUserInvitegoodsList(tUserInvitegoods);
        ExcelUtil<TUserInvitegoods> util = new ExcelUtil<TUserInvitegoods>(TUserInvitegoods.class);
        return util.exportExcel(list, "invitegoods");
    }

    /**
     * 获取邀请流水详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:invitegoods:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tUserInvitegoodsService.selectTUserInvitegoodsById(id));
    }

    /**
     * 新增邀请流水
     */
    @PreAuthorize("@ss.hasPermi('system:invitegoods:add')")
    @Log(title = "邀请流水", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TUserInvitegoods tUserInvitegoods)
    {
        return toAjax(tUserInvitegoodsService.insertTUserInvitegoods(tUserInvitegoods));
    }

    /**
     * 修改邀请流水
     */
    @PreAuthorize("@ss.hasPermi('system:invitegoods:edit')")
    @Log(title = "邀请流水", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TUserInvitegoods tUserInvitegoods)
    {
        return toAjax(tUserInvitegoodsService.updateTUserInvitegoods(tUserInvitegoods));
    }

    /**
     * 删除邀请流水
     */
    @PreAuthorize("@ss.hasPermi('system:invitegoods:remove')")
    @Log(title = "邀请流水", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tUserInvitegoodsService.deleteTUserInvitegoodsByIds(ids));
    }
}
