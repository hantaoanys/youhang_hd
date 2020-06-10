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
import com.ruoyi.project.system.domain.TUserInviteHistory;
import com.ruoyi.project.system.service.ITUserInviteHistoryService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 提现流水Controller
 * 
 * @author ruoyi
 * @date 2020-06-10
 */
@RestController
@RequestMapping("/system/history")
public class TUserInviteHistoryController extends BaseController
{
    @Autowired
    private ITUserInviteHistoryService tUserInviteHistoryService;

    /**
     * 查询提现流水列表
     */
    @PreAuthorize("@ss.hasPermi('system:history:list')")
    @GetMapping("/list")
    public TableDataInfo list(TUserInviteHistory tUserInviteHistory)
    {
        startPage();
        List<TUserInviteHistory> list = tUserInviteHistoryService.selectTUserInviteHistoryList(tUserInviteHistory);
        return getDataTable(list);
    }

    /**
     * 导出提现流水列表
     */
    @PreAuthorize("@ss.hasPermi('system:history:export')")
    @Log(title = "提现流水", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TUserInviteHistory tUserInviteHistory)
    {
        List<TUserInviteHistory> list = tUserInviteHistoryService.selectTUserInviteHistoryList(tUserInviteHistory);
        ExcelUtil<TUserInviteHistory> util = new ExcelUtil<TUserInviteHistory>(TUserInviteHistory.class);
        return util.exportExcel(list, "history");
    }

    /**
     * 获取提现流水详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:history:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return AjaxResult.success(tUserInviteHistoryService.selectTUserInviteHistoryById(userId));
    }

    /**
     * 新增提现流水
     */
    @PreAuthorize("@ss.hasPermi('system:history:add')")
    @Log(title = "提现流水", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TUserInviteHistory tUserInviteHistory)
    {
        return toAjax(tUserInviteHistoryService.insertTUserInviteHistory(tUserInviteHistory));
    }

    /**
     * 修改提现流水
     */
    @PreAuthorize("@ss.hasPermi('system:history:edit')")
    @Log(title = "提现流水", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TUserInviteHistory tUserInviteHistory)
    {
        return toAjax(tUserInviteHistoryService.updateTUserInviteHistory(tUserInviteHistory));
    }

    /**
     * 删除提现流水
     */
    @PreAuthorize("@ss.hasPermi('system:history:remove')")
    @Log(title = "提现流水", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(tUserInviteHistoryService.deleteTUserInviteHistoryByIds(userIds));
    }
}
