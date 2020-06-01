package com.ruoyi.project.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.THelpMapper;
import com.ruoyi.project.system.domain.THelp;
import com.ruoyi.project.system.service.ITHelpService;

/**
 * 帮助中心Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-27
 */
@Service
public class THelpServiceImpl implements ITHelpService 
{
    @Autowired
    private THelpMapper tHelpMapper;

    /**
     * 查询帮助中心
     * 
     * @param id 帮助中心ID
     * @return 帮助中心
     */
    @Override
    public THelp selectTHelpById(Long id)
    {
        return tHelpMapper.selectTHelpById(id);
    }

    /**
     * 查询帮助中心列表
     * 
     * @param tHelp 帮助中心
     * @return 帮助中心
     */
    @Override
    public List<THelp> selectTHelpList(THelp tHelp)
    {
        return tHelpMapper.selectTHelpList(tHelp);
    }

    /**
     * 新增帮助中心
     * 
     * @param tHelp 帮助中心
     * @return 结果
     */
    @Override
    public int insertTHelp(THelp tHelp)
    {
        return tHelpMapper.insertTHelp(tHelp);
    }

    /**
     * 修改帮助中心
     * 
     * @param tHelp 帮助中心
     * @return 结果
     */
    @Override
    public int updateTHelp(THelp tHelp)
    {
        return tHelpMapper.updateTHelp(tHelp);
    }

    /**
     * 批量删除帮助中心
     * 
     * @param ids 需要删除的帮助中心ID
     * @return 结果
     */
    @Override
    public int deleteTHelpByIds(Long[] ids)
    {
        return tHelpMapper.deleteTHelpByIds(ids);
    }

    /**
     * 删除帮助中心信息
     * 
     * @param id 帮助中心ID
     * @return 结果
     */
    @Override
    public int deleteTHelpById(Long id)
    {
        return tHelpMapper.deleteTHelpById(id);
    }
}
