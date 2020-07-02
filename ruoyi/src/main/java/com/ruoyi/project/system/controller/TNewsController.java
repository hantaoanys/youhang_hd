package com.ruoyi.project.system.controller;

import java.util.List;

import com.ruoyi.common.utils.sign.Base64;
import com.ruoyi.framework.redis.RedisCache;
import com.ruoyi.project.system.domain.TAppUser;
import com.ruoyi.project.system.domain.TNewsCollect;
import com.ruoyi.project.system.domain.TNewsLike;
import com.ruoyi.project.system.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.project.system.domain.TNews;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/system/news")
public class TNewsController extends BaseController
{
    @Autowired
    private ITNewsService tNewsService;

    @Autowired
    private ITNewsCommentService tNewsCommentService;

    @Autowired
    private ITNewsLikeService tNewsLikeService;

    @Autowired
    private ITNewsCollectService tNewsCollectService;

    @Autowired
    private ITAppUserService tAppUserService;

    @Autowired
    private RedisCache redisCache;

    @GetMapping("/list")
    public TableDataInfo list(TNews tNews)
    {
        startPage();
        List<TNews> list = tNewsService.selectTNewsList(tNews);
        return getDataTable(list);
    }

    @GetMapping("/export")
    public AjaxResult export(TNews tNews)
    {
        List<TNews> list = tNewsService.selectTNewsList(tNews);
        ExcelUtil<TNews> util = new ExcelUtil<TNews>(TNews.class);
        return util.exportExcel(list, "news");
    }

    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id, HttpServletRequest request)
    {
        TNews tNews =  tNewsService.selectTNewsById(id);
        if (null != tNews.getNewsBody()){
            tNews.setNewsBody(new String(Base64.decode(tNews.getNewsBody())));
        }
        String token = request.getHeader("token");
        Boolean uflag = true;
        if(null == token){
            uflag =false;
            tNews.setCollect(false);
            tNews.setLike(false);
        }else {
            Long userId = redisCache.getCacheObject( request.getHeader("token"));
            //校验用户id是否存在
            TAppUser appUser = tAppUserService.selectTAppUserById(userId);
            if (null == appUser || null == appUser.getUserId()){
                uflag = false;
                tNews.setCollect(false);
                tNews.setLike(false);
            }
            if(uflag){
                // 带出用户是否收藏
                TNewsCollect tNewsCollect = new TNewsCollect();
                tNewsCollect.setNewsId(id);
                tNewsCollect.setUserId(userId);
                List<TNewsCollect> c = tNewsCollectService.selectTNewsCollectList(tNewsCollect);
                if (null !=c && c.size()>0){
                    tNews.setCollect(true);
                }else {
                    tNews.setCollect(false);
                }
                //带出用户是否点赞
                TNewsLike tNewsLike = new TNewsLike();
                tNewsLike.setNewsId(id);
                tNewsLike.setUserId(userId);
                List<TNewsLike> l = tNewsLikeService.selectTNewsLikeList(tNewsLike);
                if (null !=l && l.size()>0){
                    tNews.setLike(true);
                }else {
                    tNews.setLike(false);
                }
            }
        }
        TNewsLike lNum = new TNewsLike();
        List<TNewsLike> number2 = tNewsLikeService.selectTNewsLikeList(lNum);
        tNews.setLikeNumber(Long.valueOf(number2.size()));
        return AjaxResult.success(tNews);
    }

    @GetMapping(value = "/app/{id}")
    public AjaxResult getappInfo(@PathVariable("id") Long id, HttpServletRequest request)
    {
        TNews tNews =  tNewsService.selectTNewsRefreshById(id);
        String token = request.getHeader("token");
        Boolean uflag = true;
        if(null == token){
            uflag =false;
            tNews.setCollect(false);
            tNews.setLike(false);
        }else {
            Long userId = redisCache.getCacheObject( request.getHeader("token"));
            //校验用户id是否存在
            TAppUser appUser = tAppUserService.selectTAppUserById(userId);
            if (null == appUser || null == appUser.getUserId()){
                uflag = false;
                tNews.setCollect(false);
                tNews.setLike(false);
            }
            if(uflag){
                // 带出用户是否收藏
                TNewsCollect tNewsCollect = new TNewsCollect();
                tNewsCollect.setNewsId(id);
                tNewsCollect.setUserId(userId);
                List<TNewsCollect> c = tNewsCollectService.selectTNewsCollectList(tNewsCollect);
                if (null !=c && c.size()>0){
                    tNews.setCollect(true);
                }else {
                    tNews.setCollect(false);
                }
                //带出用户是否点赞
                TNewsLike tNewsLike = new TNewsLike();
                tNewsLike.setNewsId(id);
                tNewsLike.setUserId(userId);
                List<TNewsLike> l = tNewsLikeService.selectTNewsLikeList(tNewsLike);
                if (null !=l && l.size()>0){
                    tNews.setLike(true);
                }else {
                    tNews.setLike(false);
                }
            }
        }

        TNewsLike lNum = new TNewsLike();
        List<TNewsLike> number2 = tNewsLikeService.selectTNewsLikeList(lNum);
        tNews.setLikeNumber(Long.valueOf(number2.size()));
        return AjaxResult.success(tNews);
    }

    @PostMapping
    public AjaxResult add(@RequestBody TNews tNews)
    {
        return toAjax(tNewsService.insertTNews(tNews));
    }

    @PutMapping
    public AjaxResult edit(@RequestBody TNews tNews)
    {
        return toAjax(tNewsService.updateTNews(tNews));
    }

	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tNewsService.deleteTNewsByIds(ids));
    }
}
