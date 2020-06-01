package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.TIndexPicMapper;
import com.ruoyi.project.system.domain.TIndexPic;
import com.ruoyi.project.system.service.ITIndexPicService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-10
 */
@Service
public class TIndexPicServiceImpl implements ITIndexPicService 
{
    @Autowired
    private TIndexPicMapper tIndexPicMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public TIndexPic selectTIndexPicById(Long id)
    {
        return tIndexPicMapper.selectTIndexPicById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param tIndexPic 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<TIndexPic> selectTIndexPicList(TIndexPic tIndexPic)
    {
        return tIndexPicMapper.selectTIndexPicList(tIndexPic);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param tIndexPic 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertTIndexPic(TIndexPic tIndexPic)
    {
        tIndexPic.setCreateTime(DateUtils.getNowDate());
        return tIndexPicMapper.insertTIndexPic(tIndexPic);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param tIndexPic 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateTIndexPic(TIndexPic tIndexPic)
    {
        tIndexPic.setUpdateTime(DateUtils.getNowDate());
        return tIndexPicMapper.updateTIndexPic(tIndexPic);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteTIndexPicByIds(Long[] ids)
    {
        return tIndexPicMapper.deleteTIndexPicByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteTIndexPicById(Long id)
    {
        return tIndexPicMapper.deleteTIndexPicById(id);
    }
}
