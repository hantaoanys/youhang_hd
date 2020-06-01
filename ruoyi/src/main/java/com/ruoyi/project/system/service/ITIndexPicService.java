package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.TIndexPic;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2020-05-10
 */
public interface ITIndexPicService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public TIndexPic selectTIndexPicById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param tIndexPic 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TIndexPic> selectTIndexPicList(TIndexPic tIndexPic);

    /**
     * 新增【请填写功能名称】
     * 
     * @param tIndexPic 【请填写功能名称】
     * @return 结果
     */
    public int insertTIndexPic(TIndexPic tIndexPic);

    /**
     * 修改【请填写功能名称】
     * 
     * @param tIndexPic 【请填写功能名称】
     * @return 结果
     */
    public int updateTIndexPic(TIndexPic tIndexPic);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteTIndexPicByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteTIndexPicById(Long id);
}
