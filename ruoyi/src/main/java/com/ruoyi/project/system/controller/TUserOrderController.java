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
import com.ruoyi.project.system.domain.TUserOrder;
import com.ruoyi.project.system.service.ITUserOrderService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 用户商品Controller
 * 
 * @author ruoyi
 * @date 2020-06-01
 */
@RestController
@RequestMapping("/system/order")
public class TUserOrderController extends BaseController
{
    @Autowired
    private ITUserOrderService tUserOrderService;

    /**
     * 查询用户商品列表
     */
    @PreAuthorize("@ss.hasPermi('system:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(TUserOrder tUserOrder)
    {
        startPage();
        List<TUserOrder> list = tUserOrderService.selectTUserOrderList(tUserOrder);
        return getDataTable(list);
    }

    /**
     * 导出用户商品列表
     */
    @PreAuthorize("@ss.hasPermi('system:order:export')")
    @Log(title = "用户商品", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TUserOrder tUserOrder)
    {
        List<TUserOrder> list = tUserOrderService.selectTUserOrderList(tUserOrder);
        ExcelUtil<TUserOrder> util = new ExcelUtil<TUserOrder>(TUserOrder.class);
        return util.exportExcel(list, "order");
    }

    /**
     * 获取用户商品详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:order:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tUserOrderService.selectTUserOrderById(id));
    }

    /**
     * 新增用户商品
     */
    @PreAuthorize("@ss.hasPermi('system:order:add')")
    @Log(title = "用户商品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TUserOrder tUserOrder)
    {
        return toAjax(tUserOrderService.insertTUserOrder(tUserOrder));
    }

    /**
     * 修改用户商品
     */
    @PreAuthorize("@ss.hasPermi('system:order:edit')")
    @Log(title = "用户商品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TUserOrder tUserOrder)
    {
        return toAjax(tUserOrderService.updateTUserOrder(tUserOrder));
    }

    /**
     * 删除用户商品
     */
    @PreAuthorize("@ss.hasPermi('system:order:remove')")
    @Log(title = "用户商品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tUserOrderService.deleteTUserOrderByIds(ids));
    }
}
