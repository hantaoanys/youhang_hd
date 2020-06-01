package com.ruoyi.project.system.controller;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.framework.redis.RedisCache;
import com.ruoyi.project.system.domain.TAppUser;
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
import com.ruoyi.project.system.domain.TNewsComment;
import com.ruoyi.project.system.service.ITNewsCommentService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 新闻评论Controller
 * 
 * @author ruoyi
 * @date 2020-05-13
 */
@RestController
@RequestMapping("/system/comment")
public class TNewsCommentController extends BaseController
{
    @Autowired
    private ITNewsCommentService tNewsCommentService;
    @Autowired
    private ITAppUserService tAppUserService;

    @Autowired
    private RedisCache redisCache;

    /**
     * 查询新闻评论列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TNewsComment tNewsComment)
    {
        startPage();
        List<TNewsComment> list = tNewsCommentService.selectTNewsCommentList(tNewsComment);
        return getDataTable(list);
    }

    /**
     * 导出新闻评论列表
     */
    @Log(title = "新闻评论", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TNewsComment tNewsComment)
    {
        List<TNewsComment> list = tNewsCommentService.selectTNewsCommentList(tNewsComment);
        ExcelUtil<TNewsComment> util = new ExcelUtil<TNewsComment>(TNewsComment.class);
        return util.exportExcel(list, "comment");
    }

    /**
     * 获取新闻评论详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tNewsCommentService.selectTNewsCommentById(id));
    }

    /**
     * 新增新闻评论
     */
    @Log(title = "新闻评论", businessType = BusinessType.INSERT)
    @PostMapping("/app")
    public Object addApp(@RequestBody TNewsComment tNewsComment, HttpServletRequest request) throws Exception {
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
                tNewsComment.setCreateUser(appUser.getNickName());
                tNewsComment.setCreateTime(new Date());
                tNewsComment.setUserId(userId);
//                tNewsComment.setParam2(appUser.getAvatar());
                int param1 = tNewsCommentService.selectMaxParam1();
                tNewsComment.setParam1(param1);
            }
        }
        tNewsCommentService.insertTNewsComment(tNewsComment);
        ret.put("msg","评论成功");
        return ret;
    }

    @Log(title = "新闻评论", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TNewsComment tNewsComment)  {
            return toAjax(tNewsCommentService.insertTNewsComment(tNewsComment));
        }

    /**
     * 修改新闻评论
     */
    @Log(title = "新闻评论", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TNewsComment tNewsComment)
    {
        return toAjax(tNewsCommentService.updateTNewsComment(tNewsComment));
    }

    /**
     * 删除新闻评论
     */
    @Log(title = "新闻评论", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tNewsCommentService.deleteTNewsCommentByIds(ids));
    }
}
