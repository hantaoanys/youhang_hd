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
import com.ruoyi.project.system.domain.TNotice;
import com.ruoyi.project.system.service.ITNoticeService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 公告Controller
 * 
 * @author ruoyi
 * @date 2020-05-13
 */
@RestController
@RequestMapping("/app/notice")
public class TNoticeController extends BaseController
{
    @Autowired
    private ITNoticeService tNoticeService;


    @GetMapping("/list")
    public TableDataInfo list(TNotice tNotice)
    {
        startPage();
        List<TNotice> list = tNoticeService.selectTNoticeList(tNotice);
        return getDataTable(list);
    }

    @GetMapping("/export")
    public AjaxResult export(TNotice tNotice)
    {
        List<TNotice> list = tNoticeService.selectTNoticeList(tNotice);
        ExcelUtil<TNotice> util = new ExcelUtil<TNotice>(TNotice.class);
        return util.exportExcel(list, "notice");
    }

    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tNoticeService.selectTNoticeById(id));
    }

    @PostMapping
    public AjaxResult add(@RequestBody TNotice tNotice)
    {
        return toAjax(tNoticeService.insertTNotice(tNotice));
    }

    @PutMapping
    public AjaxResult edit(@RequestBody TNotice tNotice)
    {
        return toAjax(tNoticeService.updateTNotice(tNotice));
    }

	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tNoticeService.deleteTNoticeByIds(ids));
    }
}
