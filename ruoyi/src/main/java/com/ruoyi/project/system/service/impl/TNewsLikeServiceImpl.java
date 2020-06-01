package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.TNewsLikeMapper;
import com.ruoyi.project.system.domain.TNewsLike;
import com.ruoyi.project.system.service.ITNewsLikeService;

/**
 * 新闻评论Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-23
 */
@Service
public class TNewsLikeServiceImpl implements ITNewsLikeService 
{
    @Autowired
    private TNewsLikeMapper tNewsLikeMapper;

    /**
     * 查询新闻评论
     * 
     * @param id 新闻评论ID
     * @return 新闻评论
     */
    @Override
    public TNewsLike selectTNewsLikeById(Long id)
    {
        return tNewsLikeMapper.selectTNewsLikeById(id);
    }

    /**
     * 查询新闻评论列表
     * 
     * @param tNewsLike 新闻评论
     * @return 新闻评论
     */
    @Override
    public List<TNewsLike> selectTNewsLikeList(TNewsLike tNewsLike)
    {
        return tNewsLikeMapper.selectTNewsLikeList(tNewsLike);
    }

    /**
     * 新增新闻评论
     * 
     * @param tNewsLike 新闻评论
     * @return 结果
     */
    @Override
    public int insertTNewsLike(TNewsLike tNewsLike)
    {
        tNewsLike.setCreateTime(DateUtils.getNowDate());
        return tNewsLikeMapper.insertTNewsLike(tNewsLike);
    }

    /**
     * 修改新闻评论
     * 
     * @param tNewsLike 新闻评论
     * @return 结果
     */
    @Override
    public int updateTNewsLike(TNewsLike tNewsLike)
    {
        tNewsLike.setUpdateTime(DateUtils.getNowDate());
        return tNewsLikeMapper.updateTNewsLike(tNewsLike);
    }

    /**
     * 批量删除新闻评论
     * 
     * @param ids 需要删除的新闻评论ID
     * @return 结果
     */
    @Override
    public int deleteTNewsLikeByIds(Long[] ids)
    {
        return tNewsLikeMapper.deleteTNewsLikeByIds(ids);
    }

    /**
     * 删除新闻评论信息
     * 
     * @param id 新闻评论ID
     * @return 结果
     */
    @Override
    public int deleteTNewsLikeById(Long id)
    {
        return tNewsLikeMapper.deleteTNewsLikeById(id);
    }
}
