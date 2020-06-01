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
import com.ruoyi.project.system.domain.TUserInvite;
import com.ruoyi.project.system.service.ITUserInviteService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 用户邀请商Controller
 * 
 * @author ruoyi
 * @date 2020-05-10
 */
@RestController
@RequestMapping("/system/invite")
public class TUserInviteController extends BaseController
{
    @Autowired
    private ITUserInviteService tUserInviteService;

    /**
     * 查询用户邀请商列表
     */
    @PreAuthorize("@ss.hasPermi('system:invite:list')")
    @GetMapping("/list")
    public TableDataInfo list(TUserInvite tUserInvite)
    {
        startPage();
        List<TUserInvite> list = tUserInviteService.selectTUserInviteList(tUserInvite);
        return getDataTable(list);
    }

    /**
     * 导出用户邀请商列表
     */
    @PreAuthorize("@ss.hasPermi('system:invite:export')")
    @Log(title = "用户邀请商", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TUserInvite tUserInvite)
    {
        List<TUserInvite> list = tUserInviteService.selectTUserInviteList(tUserInvite);
        ExcelUtil<TUserInvite> util = new ExcelUtil<TUserInvite>(TUserInvite.class);
        return util.exportExcel(list, "invite");
    }

    /**
     * 获取用户邀请商详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:invite:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return AjaxResult.success(tUserInviteService.selectTUserInviteById(userId));
    }

    /**
     * 新增用户邀请商
     */
    @PreAuthorize("@ss.hasPermi('system:invite:add')")
    @Log(title = "用户邀请商", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TUserInvite tUserInvite)
    {
        return toAjax(tUserInviteService.insertTUserInvite(tUserInvite));
    }

    /**
     * 修改用户邀请商
     */
    @PreAuthorize("@ss.hasPermi('system:invite:edit')")
    @Log(title = "用户邀请商", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TUserInvite tUserInvite)
    {
        return toAjax(tUserInviteService.updateTUserInvite(tUserInvite));
    }

    /**
     * 删除用户邀请商
     */
    @PreAuthorize("@ss.hasPermi('system:invite:remove')")
    @Log(title = "用户邀请商", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(tUserInviteService.deleteTUserInviteByIds(userIds));
    }
}
