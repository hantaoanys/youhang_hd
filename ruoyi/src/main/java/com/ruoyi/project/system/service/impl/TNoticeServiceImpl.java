package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.TNoticeMapper;
import com.ruoyi.project.system.domain.TNotice;
import com.ruoyi.project.system.service.ITNoticeService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-10
 */
@Service
public class TNoticeServiceImpl implements ITNoticeService 
{
    @Autowired
    private TNoticeMapper tNoticeMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public TNotice selectTNoticeById(Long id)
    {
        return tNoticeMapper.selectTNoticeById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param tNotice 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<TNotice> selectTNoticeList(TNotice tNotice)
    {
        return tNoticeMapper.selectTNoticeList(tNotice);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param tNotice 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertTNotice(TNotice tNotice)
    {
        tNotice.setCreateTime(DateUtils.getNowDate());
        return tNoticeMapper.insertTNotice(tNotice);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param tNotice 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateTNotice(TNotice tNotice)
    {
        tNotice.setUpdateTime(DateUtils.getNowDate());
        return tNoticeMapper.updateTNotice(tNotice);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteTNoticeByIds(Long[] ids)
    {
        return tNoticeMapper.deleteTNoticeByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteTNoticeById(Long id)
    {
        return tNoticeMapper.deleteTNoticeById(id);
    }
}
