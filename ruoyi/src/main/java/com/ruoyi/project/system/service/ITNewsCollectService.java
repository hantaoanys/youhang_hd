package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.TNewsCollect;

/**
 * 新闻评论Service接口
 * 
 * @author ruoyi
 * @date 2020-05-23
 */
public interface ITNewsCollectService 
{
    /**
     * 查询新闻评论
     * 
     * @param id 新闻评论ID
     * @return 新闻评论
     */
    public TNewsCollect selectTNewsCollectById(Long id);

    /**
     * 查询新闻评论列表
     * 
     * @param tNewsCollect 新闻评论
     * @return 新闻评论集合
     */
    public List<TNewsCollect> selectTNewsCollectList(TNewsCollect tNewsCollect);

    /**
     * 新增新闻评论
     * 
     * @param tNewsCollect 新闻评论
     * @return 结果
     */
    public int insertTNewsCollect(TNewsCollect tNewsCollect);

    /**
     * 修改新闻评论
     * 
     * @param tNewsCollect 新闻评论
     * @return 结果
     */
    public int updateTNewsCollect(TNewsCollect tNewsCollect);

    /**
     * 批量删除新闻评论
     * 
     * @param ids 需要删除的新闻评论ID
     * @return 结果
     */
    public int deleteTNewsCollectByIds(Long[] ids);

    /**
     * 删除新闻评论信息
     * 
     * @param id 新闻评论ID
     * @return 结果
     */
    public int deleteTNewsCollectById(Long id);
}
