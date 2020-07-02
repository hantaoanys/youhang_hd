package com.ruoyi.project.system.controller;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.framework.redis.RedisCache;
import com.ruoyi.project.system.domain.TAppUser;
import com.ruoyi.project.system.domain.TUserInviteHistory;
import com.ruoyi.project.system.service.ITAppUserService;
import com.ruoyi.project.system.service.ITUserInviteHistoryService;
import com.ruoyi.project.tool.gen.util.InviteCodeUtils;
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

import javax.servlet.http.HttpServletRequest;

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

    @Autowired
    private ITAppUserService tAppUserService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ITUserInviteHistoryService tUserInviteHistoryService;


    /**
     * 查询用户邀请商列表
     */
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
    @Log(title = "用户邀请商", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TUserInvite tUserInvite)
    {
        List<TUserInvite> list = tUserInviteService.selectTUserInviteList(tUserInvite);
        ExcelUtil<TUserInvite> util = new ExcelUtil<TUserInvite>(TUserInvite.class);
        return util.exportExcel(list, "invite");
    }

    /**
     * 获取用户邀请码，金额, 最近一次提现等
     */
    @GetMapping(value = "/app")
    public Object getAppInfo( HttpServletRequest request)
    {
        JSONObject ret = new JSONObject();
        String token = request.getHeader("token");
        Boolean uflag = true;
        if(null == token || "".equals(token)) {
            uflag = false;
            ret.put("msg","请先登录");
            return ret;
        }else {
            Long userId = redisCache.getCacheObject( request.getHeader("token"));
            //校验用户id是否存在
            TAppUser appUser = tAppUserService.selectTAppUserById(userId);
            if (null == appUser || null == appUser.getUserId()){
                ret.put("msg","请先登录");
                return ret;
            }else {
                TUserInvite tUserInvite = tUserInviteService.selectTUserInviteById(userId);
                if (null == tUserInvite || null == tUserInvite.getUserId()){
                    TUserInvite newInvite = new TUserInvite();
                    newInvite.setUserId(userId);
                    newInvite.setInviteNumberTotal(0L);
                    newInvite.setInviteMoneyTotal(0L);
                    newInvite.setInviteMoneyNot(0L);
                    newInvite.setInviteMoneyAlready(0L);
                    newInvite.setInviteCode(InviteCodeUtils.toSerialCode(userId));
                    newInvite.setCreateTime(new Date());
                    newInvite.setCanInvite(false);
                    tUserInviteService.insertTUserInvite(newInvite);
                    return  newInvite;
                }else {
                    /** TODO 判断是否有提现中 或失败状态的 不能继续提现*/

                    TUserInviteHistory history = new TUserInviteHistory();
                    history.setUserId(userId);
                    history.setStatus("1"); //审核状态 4.提现成功 3.审核失败 2:审核通过 1:提交申请
                    List<TUserInviteHistory> list = tUserInviteHistoryService.selectTUserInviteHistoryList(history);
                    if (null !=list && list.size()>0 ){
                        tUserInvite.setCanInvite(false);
                    }else {
                        tUserInvite.setCanInvite(true);
                    }

                    TUserInviteHistory lastStatus = new TUserInviteHistory();
                    lastStatus.setUserId(userId);
                    List<TUserInviteHistory> last = tUserInviteHistoryService.selectTUserInviteHistoryList(lastStatus);
                    if (last!=null && last.size()>0){
                        tUserInvite.settUserInviteHistory(last.get(0));
                    }
                    return tUserInvite;
                }
            }
        }
//        return AjaxResult.success(tUserInviteService.selectTUserInviteById(userId));
    }

    /**
     * 获取用户邀请
     */
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return AjaxResult.success(tUserInviteService.selectTUserInviteById(userId));
    }

    /**
     * 新增用户邀请商
     */
    @Log(title = "用户邀请商", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TUserInvite tUserInvite)
    {
        return toAjax(tUserInviteService.insertTUserInvite(tUserInvite));
    }


    /**
     * APP 修改用户邀请商
     */
    @Log(title = "用户邀请商", businessType = BusinessType.UPDATE)
    @PostMapping("/app/update")
    public Object editApp(@RequestBody TUserInvite tUserInvite,HttpServletRequest request)
    {
        JSONObject ret = new JSONObject();
        String token = request.getHeader("token");
        Boolean uflag = true;
        if(null == token || "".equals(token)) {
            uflag = false;
            ret.put("msg","请先登录");
            return ret;
        }else {
            Long userId = redisCache.getCacheObject(request.getHeader("token"));
            //校验用户id是否存在
            TAppUser appUser = tAppUserService.selectTAppUserById(userId);
            if (null == appUser || null == appUser.getUserId()) {
                ret.put("msg", "请先登录");
                return ret;
            } else {
                tUserInvite.setUserId(userId);
            }
        }
        return toAjax(tUserInviteService.updateTUserInvite(tUserInvite));
    }


    /**
     * 修改用户邀请商
     */
    @Log(title = "用户邀请商", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TUserInvite tUserInvite)
    {
        return toAjax(tUserInviteService.updateTUserInvite(tUserInvite));
    }

    /**
     * 删除用户邀请商
     */
    @Log(title = "用户邀请商", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(tUserInviteService.deleteTUserInviteByIds(userIds));
    }
}
