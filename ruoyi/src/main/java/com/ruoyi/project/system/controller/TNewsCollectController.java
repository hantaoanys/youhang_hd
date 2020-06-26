package com.ruoyi.project.system.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.framework.redis.RedisCache;
import com.ruoyi.project.system.domain.TAppUser;
import com.ruoyi.project.system.domain.TNews;
import com.ruoyi.project.system.service.ITAppUserService;
import com.ruoyi.project.system.service.ITNewsService;
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
import com.ruoyi.project.system.domain.TNewsCollect;
import com.ruoyi.project.system.service.ITNewsCollectService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 新闻收藏Controller
 * 
 * @author ruoyi
 * @date 2020-05-23
 */
@RestController
@RequestMapping("/system/collect")
public class TNewsCollectController extends BaseController
{
    @Autowired
    private ITNewsCollectService tNewsCollectService;
    @Autowired
    private ITAppUserService tAppUserService;

    @Autowired
    private RedisCache redisCache;
    @Autowired
    private ITNewsService tNewsService;
    /**
     * 查询新闻收藏列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TNewsCollect tNewsCollect)
    {
        startPage();
        List<TNewsCollect> list = tNewsCollectService.selectTNewsCollectList(tNewsCollect);
        return getDataTable(list);
    }

    /**
     * 查询新闻收藏列表APP
     */
    @GetMapping("/app/list")
    public Object appList(TNewsCollect tNewsCollect,HttpServletRequest request)
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
                tNewsCollect.setUserId(userId);
            }
        }
        startPage();
        List<TNews> tnewslist = new ArrayList();
        List<TNewsCollect> list = tNewsCollectService.selectTNewsCollectList(tNewsCollect);
        if (null != list ){
            for (TNewsCollect t :list){
                if (null!=t.getNewsId()){
                    TNews tNews = tNewsService.selectTNewsById(t.getNewsId());
                    tnewslist.add(tNews);
                }

            }
        }

        return getDataTable(tnewslist);
    }



    /**
     * 导出新闻收藏列表
     */
    @Log(title = "新闻收藏", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TNewsCollect tNewsCollect)
    {
        List<TNewsCollect> list = tNewsCollectService.selectTNewsCollectList(tNewsCollect);
        ExcelUtil<TNewsCollect> util = new ExcelUtil<TNewsCollect>(TNewsCollect.class);
        return util.exportExcel(list, "collect");
    }

    /**
     * 获取新闻收藏详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tNewsCollectService.selectTNewsCollectById(id));
    }

    /**
     * 新增新闻收藏
     */
    @Log(title = "新闻收藏", businessType = BusinessType.INSERT)
    @PostMapping("/app")
    public Object addApp(@RequestBody TNewsCollect tNewsCollect, HttpServletRequest request)
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
                TNewsCollect temp = new TNewsCollect();
                temp.setUserId(userId);
                temp.setNewsId(tNewsCollect.getNewsId());
                List<TNewsCollect> tNewsCollects = tNewsCollectService.selectTNewsCollectList(temp);
                if (null!=tNewsCollects && tNewsCollects.size()>0){
                    tNewsCollectService.deleteTNewsCollectById(tNewsCollects.get(0).getId());
                    ret.put("msg","取消收藏");
                    return ret;
                }
                tNewsCollect.setCreateUser(appUser.getNickName());
                tNewsCollect.setCreateTime(new Date());
                tNewsCollect.setUserId(userId);
                tNewsCollectService.insertTNewsCollect(tNewsCollect);
                ret.put("msg","收藏成功");
            }
        }
        return ret;
    }

    /**
     * 新增新闻收藏
     */
    @Log(title = "新闻收藏", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TNewsCollect tNewsCollect)
    {
        return toAjax(tNewsCollectService.insertTNewsCollect(tNewsCollect));
    }
    /**
     * 修改新闻收藏
     */
    @Log(title = "新闻收藏", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TNewsCollect tNewsCollect)
    {
        return toAjax(tNewsCollectService.updateTNewsCollect(tNewsCollect));
    }

    /**
     * 删除新闻收藏
     */
    @Log(title = "新闻收藏", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tNewsCollectService.deleteTNewsCollectByIds(ids));
    }
}
