package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.system.domain.TNewsCollect;
import com.ruoyi.project.system.domain.TNewsComment;
import com.ruoyi.project.system.mapper.TNewsCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.TNewsMapper;
import com.ruoyi.project.system.domain.TNews;
import com.ruoyi.project.system.service.ITNewsService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-10
 */
@Service
public class TNewsServiceImpl implements ITNewsService 
{
    @Autowired
    private TNewsMapper tNewsMapper;

    @Autowired
    private TNewsCommentMapper tNewsCommentMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public TNews selectTNewsById(Long id)
    {
        //阅读数加1
        TNews tNews =  tNewsMapper.selectTNewsById(id);
        if (null != tNews) {
            tNews.setViewNumber(tNews.getViewNumber()+1);
            tNewsMapper.updateTNews(tNews);
        }
        // 带出评论列表
        TNewsComment tNewsComment = new TNewsComment();
        tNewsComment.setNewsId(id);
        List<TNewsComment> comments = tNewsCommentMapper.selectLists(tNewsComment);
        tNews.setNewComment(comments);
        return tNews;
    }


    @Override
    public TNews selectTNewsRefreshById(Long id)
    {
        //阅读数加1
        TNews tNews =  tNewsMapper.selectTNewsById(id);
//        if (null != tNews) {
//            tNews.setViewNumber(tNews.getViewNumber()+1);
//            tNewsMapper.updateTNews(tNews);
//        }
        // 带出评论列表
        TNewsComment tNewsComment = new TNewsComment();
        tNewsComment.setNewsId(id);
        List<TNewsComment> comments = tNewsCommentMapper.selectLists(tNewsComment);
        tNews.setNewComment(comments);
        tNews.setCommentNumber(Long.valueOf(comments.size()));
        return tNews;
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param tNews 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<TNews> selectTNewsList(TNews tNews)
    {
        return tNewsMapper.selectTNewsList(tNews);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param tNews 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertTNews(TNews tNews)
    {
        tNews.setCreateTime(DateUtils.getNowDate());
        return tNewsMapper.insertTNews(tNews);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param tNews 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateTNews(TNews tNews)
    {
        tNews.setUpdateTime(DateUtils.getNowDate());
        return tNewsMapper.updateTNews(tNews);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteTNewsByIds(Long[] ids)
    {
        return tNewsMapper.deleteTNewsByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteTNewsById(Long id)
    {
        return tNewsMapper.deleteTNewsById(id);
    }
}
