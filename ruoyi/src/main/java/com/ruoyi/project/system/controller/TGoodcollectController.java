package com.ruoyi.project.system.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.framework.redis.RedisCache;
import com.ruoyi.project.system.domain.TAppUser;
import com.ruoyi.project.system.domain.TGoods;
import com.ruoyi.project.system.service.ITAppUserService;
import com.ruoyi.project.system.service.ITGoodsService;
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
import com.ruoyi.project.system.domain.TGoodcollect;
import com.ruoyi.project.system.service.ITGoodcollectService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 商品收藏Controller
 * 
 * @author ruoyi
 * @date 2020-06-06
 */
@RestController
@RequestMapping("/system/goodcollect")
public class TGoodcollectController extends BaseController
{
    @Autowired
    private ITGoodcollectService tGoodcollectService;
    @Autowired
    private ITAppUserService tAppUserService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ITGoodsService tGoodsService;

    /**
     * 查询商品收藏列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TGoodcollect tGoodcollect)
    {
        startPage();
        List<TGoodcollect> list = tGoodcollectService.selectTGoodcollectList(tGoodcollect);
        return getDataTable(list);
    }

    /**
     * APP查询商品收藏列表
     */
    @GetMapping("/app/list")
    public Object appList(TGoodcollect tGoodcollect, HttpServletRequest request)
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
                tGoodcollect.setUserId(userId.toString());
            }
        }
        startPage();
        List<TGoods> goodList = new ArrayList<>();
        List<TGoodcollect> list = tGoodcollectService.selectTGoodcollectList(tGoodcollect);
        if (null !=list){
            for (TGoodcollect t:list){
                if (null != t.getGoodId()){
                    TGoods tGoods=  tGoodsService.selectTGoodsById(Long.valueOf(t.getGoodId()));
                    goodList.add(tGoods);
                }

            }
        }

        return getDataTable(goodList);
    }

    /**
     * 导出商品收藏列表
     */
    @Log(title = "商品收藏", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TGoodcollect tGoodcollect)
    {
        List<TGoodcollect> list = tGoodcollectService.selectTGoodcollectList(tGoodcollect);
        ExcelUtil<TGoodcollect> util = new ExcelUtil<TGoodcollect>(TGoodcollect.class);
        return util.exportExcel(list, "goodcollect");
    }

    /**
     * 获取商品收藏详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tGoodcollectService.selectTGoodcollectById(id));
    }

    /**
     * 新增APP收藏
     */
    @PostMapping("/app")
    public Object addApp(@RequestBody TGoodcollect tGoodcollect, HttpServletRequest request)
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
                TGoodcollect temp = new TGoodcollect();
                temp.setUserId(userId.toString());
                temp.setGoodId(tGoodcollect.getGoodId());
                List<TGoodcollect> tGoodcollects = tGoodcollectService.selectTGoodcollectList(temp);
                if (null!=tGoodcollects && tGoodcollects.size()>0){
                    tGoodcollectService.deleteTGoodcollectById(tGoodcollects.get(0).getId());
                    ret.put("msg","取消收藏");
                    return ret;
                }
                tGoodcollect.setPhone(appUser.getPhonenumber());
                tGoodcollect.setCreateUser(appUser.getNickName());
                tGoodcollect.setCreateTime(new Date());
                tGoodcollect.setUserId(userId.toString());
                tGoodcollectService.insertTGoodcollect(tGoodcollect);
                ret.put("msg","收藏成功");
            }
        }
        return ret;
    }



    /**
     * 新增商品收藏
     */
    @Log(title = "商品收藏", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TGoodcollect tGoodcollect)
    {
        return toAjax(tGoodcollectService.insertTGoodcollect(tGoodcollect));
    }

    /**
     * 修改商品收藏
     */
    @Log(title = "商品收藏", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TGoodcollect tGoodcollect)
    {
        return toAjax(tGoodcollectService.updateTGoodcollect(tGoodcollect));
    }

    /**
     * 删除商品收藏
     */
    @Log(title = "商品收藏", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tGoodcollectService.deleteTGoodcollectByIds(ids));
    }
}
