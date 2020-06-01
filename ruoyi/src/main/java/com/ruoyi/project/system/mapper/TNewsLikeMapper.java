package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.TNewsLike;

/**
 * 新闻评论Mapper接口
 * 
 * @author ruoyi
 * @date 2020-05-23
 */
public interface TNewsLikeMapper 
{
    /**
     * 查询新闻评论
     * 
     * @param id 新闻评论ID
     * @return 新闻评论
     */
    public TNewsLike selectTNewsLikeById(Long id);

    /**
     * 查询新闻评论列表
     * 
     * @param tNewsLike 新闻评论
     * @return 新闻评论集合
     */
    public List<TNewsLike> selectTNewsLikeList(TNewsLike tNewsLike);

    /**
     * 新增新闻评论
     * 
     * @param tNewsLike 新闻评论
     * @return 结果
     */
    public int insertTNewsLike(TNewsLike tNewsLike);

    /**
     * 修改新闻评论
     * 
     * @param tNewsLike 新闻评论
     * @return 结果
     */
    public int updateTNewsLike(TNewsLike tNewsLike);

    /**
     * 删除新闻评论
     * 
     * @param id 新闻评论ID
     * @return 结果
     */
    public int deleteTNewsLikeById(Long id);

    /**
     * 批量删除新闻评论
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTNewsLikeByIds(Long[] ids);
}
