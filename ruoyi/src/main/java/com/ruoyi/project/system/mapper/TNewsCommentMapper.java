package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.TNewsComment;

/**
 * 新闻评论Mapper接口
 * 
 * @author ruoyi
 * @date 2020-05-13
 */
public interface TNewsCommentMapper 
{
    /**
     * 查询新闻评论
     * 
     * @param id 新闻评论ID
     * @return 新闻评论
     */
    public TNewsComment selectTNewsCommentById(Long id);

    /**
     * 查询新闻评论列表
     * 
     * @param tNewsComment 新闻评论
     * @return 新闻评论集合
     */
    public List<TNewsComment> selectTNewsCommentList(TNewsComment tNewsComment);

    /**
     * 新增新闻评论
     * 
     * @param tNewsComment 新闻评论
     * @return 结果
     */
    public int insertTNewsComment(TNewsComment tNewsComment);

    /**
     * 修改新闻评论
     * 
     * @param tNewsComment 新闻评论
     * @return 结果
     */
    public int updateTNewsComment(TNewsComment tNewsComment);

    /**
     * 删除新闻评论
     * 
     * @param id 新闻评论ID
     * @return 结果
     */
    public int deleteTNewsCommentById(Long id);

    /**
     * 批量删除新闻评论
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTNewsCommentByIds(Long[] ids);

    int selectMaxParam1();

    List<TNewsComment> selectLists(TNewsComment tNewsComment);
}
