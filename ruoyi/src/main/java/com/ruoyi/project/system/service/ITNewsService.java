package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.TNews;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2020-05-10
 */
public interface ITNewsService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public TNews selectTNewsById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param tNews 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TNews> selectTNewsList(TNews tNews);

    /**
     * 新增【请填写功能名称】
     * 
     * @param tNews 【请填写功能名称】
     * @return 结果
     */
    public int insertTNews(TNews tNews);

    /**
     * 修改【请填写功能名称】
     * 
     * @param tNews 【请填写功能名称】
     * @return 结果
     */
    public int updateTNews(TNews tNews);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteTNewsByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteTNewsById(Long id);

    TNews selectTNewsRefreshById(Long id);
}
