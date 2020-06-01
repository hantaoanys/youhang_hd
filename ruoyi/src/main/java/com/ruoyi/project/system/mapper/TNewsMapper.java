package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.TNews;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2020-05-10
 */
public interface TNewsMapper 
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
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteTNewsById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTNewsByIds(Long[] ids);
}
