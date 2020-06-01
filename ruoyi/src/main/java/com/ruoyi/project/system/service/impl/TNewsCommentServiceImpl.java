package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.TNewsCommentMapper;
import com.ruoyi.project.system.domain.TNewsComment;
import com.ruoyi.project.system.service.ITNewsCommentService;

/**
 * 新闻评论Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-13
 */
@Service
public class TNewsCommentServiceImpl implements ITNewsCommentService 
{
    @Autowired
    private TNewsCommentMapper tNewsCommentMapper;

    /**
     * 查询新闻评论
     * 
     * @param id 新闻评论ID
     * @return 新闻评论
     */
    @Override
    public TNewsComment selectTNewsCommentById(Long id)
    {
        return tNewsCommentMapper.selectTNewsCommentById(id);
    }

    /**
     * 查询新闻评论列表
     * 
     * @param tNewsComment 新闻评论
     * @return 新闻评论
     */
    @Override
    public List<TNewsComment> selectTNewsCommentList(TNewsComment tNewsComment)
    {
        return tNewsCommentMapper.selectTNewsCommentList(tNewsComment);
    }

    /**
     * 新增新闻评论
     * 
     * @param tNewsComment 新闻评论
     * @return 结果
     */
    @Override
    public int insertTNewsComment(TNewsComment tNewsComment)
    {
        tNewsComment.setCreateTime(DateUtils.getNowDate());
        return tNewsCommentMapper.insertTNewsComment(tNewsComment);
    }

    /**
     * 修改新闻评论
     * 
     * @param tNewsComment 新闻评论
     * @return 结果
     */
    @Override
    public int updateTNewsComment(TNewsComment tNewsComment)
    {
        tNewsComment.setUpdateTime(DateUtils.getNowDate());
        return tNewsCommentMapper.updateTNewsComment(tNewsComment);
    }

    /**
     * 批量删除新闻评论
     * 
     * @param ids 需要删除的新闻评论ID
     * @return 结果
     */
    @Override
    public int deleteTNewsCommentByIds(Long[] ids)
    {
        return tNewsCommentMapper.deleteTNewsCommentByIds(ids);
    }

    /**
     * 删除新闻评论信息
     * 
     * @param id 新闻评论ID
     * @return 结果
     */
    @Override
    public int deleteTNewsCommentById(Long id)
    {
        return tNewsCommentMapper.deleteTNewsCommentById(id);
    }

    @Override
    public int selectMaxParam1() {
        return tNewsCommentMapper.selectMaxParam1();
    }
}
