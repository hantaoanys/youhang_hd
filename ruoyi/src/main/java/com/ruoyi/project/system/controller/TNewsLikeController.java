package com.ruoyi.project.system.controller;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.framework.redis.RedisCache;
import com.ruoyi.project.system.domain.TAppUser;
import com.ruoyi.project.system.domain.TNewsCollect;
import com.ruoyi.project.system.service.ITAppUserService;
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
import com.ruoyi.project.system.domain.TNewsLike;
import com.ruoyi.project.system.service.ITNewsLikeService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 新闻点赞Controller
 * 
 * @author ruoyi
 * @date 2020-05-23
 */
@RestController
@RequestMapping("/system/like")
public class TNewsLikeController extends BaseController
{
    @Autowired
    private ITNewsLikeService tNewsLikeService;

    @Autowired
    private ITAppUserService tAppUserService;

    @Autowired
    private RedisCache redisCache;

    /**
     * 查询新闻点赞列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TNewsLike tNewsLike)
    {
        startPage();
        List<TNewsLike> list = tNewsLikeService.selectTNewsLikeList(tNewsLike);
        return getDataTable(list);
    }

    /**
     * 导出新闻点赞列表
     */
    @Log(title = "新闻点赞", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TNewsLike tNewsLike)
    {
        List<TNewsLike> list = tNewsLikeService.selectTNewsLikeList(tNewsLike);
        ExcelUtil<TNewsLike> util = new ExcelUtil<TNewsLike>(TNewsLike.class);
        return util.exportExcel(list, "like");
    }

    /**
     * 获取新闻点赞详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tNewsLikeService.selectTNewsLikeById(id));
    }

    /**
     * 新增新闻点赞
     */
    @Log(title = "新闻点赞", businessType = BusinessType.INSERT)
    @PostMapping("/app")
    public Object addApp(@RequestBody TNewsLike tNewsLike, HttpServletRequest request)
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
                TNewsLike temp = new TNewsLike();
                temp.setUserId(userId);
                temp.setNewsId(tNewsLike.getNewsId());
                List<TNewsLike> tNewsLikes = tNewsLikeService.selectTNewsLikeList(temp);
                if (null!=tNewsLikes && tNewsLikes.size()>0){
                    tNewsLikeService.deleteTNewsLikeById(tNewsLikes.get(0).getId());
                    ret.put("msg","取消点赞");
                    return ret;
                }
                tNewsLike.setCreateUser(appUser.getNickName());
                tNewsLike.setCreateTime(new Date());
                tNewsLike.setUserId(userId);
                tNewsLikeService.insertTNewsLike(tNewsLike);
                ret.put("msg","点赞成功");
            }
        }
        return ret;
    }
    /**
     * 新增新闻点赞
     */
    @Log(title = "新闻点赞", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TNewsLike tNewsLike)
    {
        return toAjax(tNewsLikeService.insertTNewsLike(tNewsLike));
    }

    /**
     * 修改新闻点赞
     */
    @Log(title = "新闻点赞", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TNewsLike tNewsLike)
    {
        return toAjax(tNewsLikeService.updateTNewsLike(tNewsLike));
    }

    /**
     * 删除新闻点赞
     */
    @Log(title = "新闻点赞", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tNewsLikeService.deleteTNewsLikeByIds(ids));
    }
}
