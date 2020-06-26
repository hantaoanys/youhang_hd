package com.ruoyi.project.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.TAppupdateMapper;
import com.ruoyi.project.system.domain.TAppupdate;
import com.ruoyi.project.system.service.ITAppupdateService;

/**
 * app升级Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-21
 */
@Service
public class TAppupdateServiceImpl implements ITAppupdateService 
{
    @Autowired
    private TAppupdateMapper tAppupdateMapper;

    /**
     * 查询app升级
     * 
     * @param id app升级ID
     * @return app升级
     */
    @Override
    public TAppupdate selectTAppupdateById(Long id)
    {
        return tAppupdateMapper.selectTAppupdateById(id);
    }

    /**
     * 查询app升级列表
     * 
     * @param tAppupdate app升级
     * @return app升级
     */
    @Override
    public List<TAppupdate> selectTAppupdateList(TAppupdate tAppupdate)
    {
        return tAppupdateMapper.selectTAppupdateList(tAppupdate);
    }

    /**
     * 新增app升级
     * 
     * @param tAppupdate app升级
     * @return 结果
     */
    @Override
    public int insertTAppupdate(TAppupdate tAppupdate)
    {
        return tAppupdateMapper.insertTAppupdate(tAppupdate);
    }

    /**
     * 修改app升级
     * 
     * @param tAppupdate app升级
     * @return 结果
     */
    @Override
    public int updateTAppupdate(TAppupdate tAppupdate)
    {
        return tAppupdateMapper.updateTAppupdate(tAppupdate);
    }

    /**
     * 批量删除app升级
     * 
     * @param ids 需要删除的app升级ID
     * @return 结果
     */
    @Override
    public int deleteTAppupdateByIds(Long[] ids)
    {
        return tAppupdateMapper.deleteTAppupdateByIds(ids);
    }

    /**
     * 删除app升级信息
     * 
     * @param id app升级ID
     * @return 结果
     */
    @Override
    public int deleteTAppupdateById(Long id)
    {
        return tAppupdateMapper.deleteTAppupdateById(id);
    }
}
