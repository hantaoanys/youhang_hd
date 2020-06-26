package com.ruoyi.project.system.controller;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.framework.redis.RedisCache;
import com.ruoyi.project.system.domain.TAppUser;
import com.ruoyi.project.system.domain.TUserInvite;
import com.ruoyi.project.system.service.ITAppUserService;
import com.ruoyi.project.system.service.ITUserInviteService;
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

import javax.servlet.http.HttpServletRequest;

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

    @Autowired
    private ITUserInviteService tUserInviteService;

    @Autowired
    private ITAppUserService tAppUserService;

    @Autowired
    private RedisCache redisCache;



    /**
     * WEB 查询提现流水列表
     */
    @GetMapping("/list/web")
    public Object webList(TUserInviteHistory tUserInviteHistory) throws Exception {

        startPage();
        List<TUserInviteHistory> list = tUserInviteHistoryService.selectTUserInviteHistoryList(tUserInviteHistory);
        if (null != list && list.size()>0){
            for (TUserInviteHistory t : list){
                t.setPhone(t.getPhone().substring(0, 3) + "****" + t.getPhone().substring(7, t.getPhone().length()));
            }
        }

        return getDataTable(list);
    }



    /**
     * APP 查询提现流水列表
     */
    @GetMapping("/list/app")
    public Object appList(TUserInviteHistory tUserInviteHistory, HttpServletRequest request) throws Exception {
        JSONObject ret = new JSONObject();
        String token = request.getHeader("token");
        Boolean uflag = true;
        if(null == token || "".equals(token)) {
            uflag = false;
            ret.put("msg","请先登录");
//            throw new Exception ("请先登录");
            return ret;
        }else {
            Long userId = redisCache.getCacheObject( request.getHeader("token"));
            //校验用户id是否存在
            TAppUser appUser = tAppUserService.selectTAppUserById(userId);
            if (null == appUser || null == appUser.getUserId()){
                ret.put("msg","请先登录");
//                throw new Exception ("请先登录");
                return ret;
            }else {
                tUserInviteHistory.setUserId(userId);
            }
        }
        startPage();
        List<TUserInviteHistory> list = tUserInviteHistoryService.selectTUserInviteHistoryList(tUserInviteHistory);
        if (null != list && list.size()>0){
            for (TUserInviteHistory t : list){
                t.setPhone(t.getPhone().substring(0, 3) + "****" + t.getPhone().substring(7, t.getPhone().length()));
            }
        }

        return getDataTable(list);
    }



    /**
     * 查询提现流水列表
     */
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
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return AjaxResult.success(tUserInviteHistoryService.selectTUserInviteHistoryById(userId));
    }



    /**
     * APP申请提现
     */
    @Log(title = "提现流水", businessType = BusinessType.INSERT)
    @PostMapping("/app")
    public Object appAdd(@RequestBody TUserInviteHistory tUserInviteHistory,HttpServletRequest request) throws Exception {
        JSONObject ret = new JSONObject();
        String token = request.getHeader("token");
        Boolean uflag = true;
        if(null == token || "".equals(token)) {
            uflag = false;
            ret.put("msg","请先登录");
//            throw new Exception ("请先登录");
            return ret;
        }else {
            Long userId = redisCache.getCacheObject(request.getHeader("token"));
            //校验用户id是否存在
            TAppUser appUser = tAppUserService.selectTAppUserById(userId);
            if (null == appUser || null == appUser.getUserId()) {
                ret.put("msg", "请先登录");
//                throw new Exception("请先登录");
                return ret;
            } else {
                tUserInviteHistory.setUserId(userId);
                TUserInviteHistory history = new TUserInviteHistory();
                history.setUserId(userId);
                history.setStatus(1L); //审核状态 4.提现成功 3.审核失败 2:审核通过 1:提交申请
                List<TUserInviteHistory> list = tUserInviteHistoryService.selectTUserInviteHistoryList(history);
                if (null !=list && list.size()>0 ){
                    ret.put("msg", "存在暂未审核的提现，无法申请提现，请等待审核");
//                    throw new Exception("存在暂未审核的提现，无法申请提现，请等待审核");
                    return ret;
                }
                TUserInvite tUserInvite = tUserInviteService.selectTUserInviteById(userId);
                if (null == tUserInvite || null ==tUserInvite.getUserId()){
                    ret.put("msg", "系统错误，请稍后再试");
//                    throw new Exception("系统错误，请稍后再试");
                    return ret;
                }

                tUserInviteHistory.setStatus(1L);
                tUserInviteHistory.setCreateTime(new Date());
                tUserInviteHistory.setMoney(tUserInvite.getInviteMoneyNot());
                TAppUser user =  tAppUserService.selectTAppUserById(userId);
                tUserInviteHistory.setPhone(user.getPhonenumber());

                //更新主表的 未体现金额，已提现金额。
                tUserInvite.setInviteMoneyNot(0L);
                tUserInvite.setInviteMoneyAlready(tUserInvite.getInviteMoneyAlready() +tUserInvite.getInviteMoneyNot());
                tUserInviteService.updateTUserInvite(tUserInvite);
               }
        }

        return toAjax(tUserInviteHistoryService.insertTUserInviteHistory(tUserInviteHistory));
    }



    /**
     * 新增提现流水
     */
    @Log(title = "提现流水", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TUserInviteHistory tUserInviteHistory)
    {
        return toAjax(tUserInviteHistoryService.insertTUserInviteHistory(tUserInviteHistory));
    }

    /**
     * 修改提现流水
     */
    @Log(title = "提现流水", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TUserInviteHistory tUserInviteHistory)
    {
        return toAjax(tUserInviteHistoryService.updateTUserInviteHistory(tUserInviteHistory));
    }

    /**
     * 删除提现流水
     */
    @Log(title = "提现流水", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(tUserInviteHistoryService.deleteTUserInviteHistoryByIds(userIds));
    }
}
