package com.ruoyi.project.system.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.TUserInviteMapper;
import com.ruoyi.project.system.domain.TUserInvite;
import com.ruoyi.project.system.service.ITUserInviteService;

/**
 * 用户邀请商Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-10
 */
@Service
public class TUserInviteServiceImpl implements ITUserInviteService 
{
    @Autowired
    private TUserInviteMapper tUserInviteMapper;

    /**
     * 查询用户邀请商
     * 
     * @param userId 用户邀请商ID
     * @return 用户邀请商
     */
    @Override
    public TUserInvite selectTUserInviteById(Long userId)
    {
        return tUserInviteMapper.selectTUserInviteById(userId);
    }

    /**
     * 查询用户邀请商列表
     * 
     * @param tUserInvite 用户邀请商
     * @return 用户邀请商
     */
    @Override
    public List<TUserInvite> selectTUserInviteList(TUserInvite tUserInvite)
    {
        return tUserInviteMapper.selectTUserInviteList(tUserInvite);
    }

    /**
     * 新增用户邀请商
     * 
     * @param tUserInvite 用户邀请商
     * @return 结果
     */
    @Override
    public int insertTUserInvite(TUserInvite tUserInvite)
    {
        tUserInvite.setCreateTime(DateUtils.getNowDate());
        return tUserInviteMapper.insertTUserInvite(tUserInvite);
    }

    /**
     * 修改用户邀请商
     * 
     * @param tUserInvite 用户邀请商
     * @return 结果
     */
    @Override
    public int updateTUserInvite(TUserInvite tUserInvite)
    {
        tUserInvite.setUpdateTime(DateUtils.getNowDate());
        return tUserInviteMapper.updateTUserInvite(tUserInvite);
    }

    /**
     * 批量删除用户邀请商
     * 
     * @param userIds 需要删除的用户邀请商ID
     * @return 结果
     */
    @Override
    public int deleteTUserInviteByIds(Long[] userIds)
    {
        return tUserInviteMapper.deleteTUserInviteByIds(userIds);
    }

    /**
     * 删除用户邀请商信息
     * 
     * @param userId 用户邀请商ID
     * @return 结果
     */
    @Override
    public int deleteTUserInviteById(Long userId)
    {
        return tUserInviteMapper.deleteTUserInviteById(userId);
    }

    @Override
    public TUserInvite selectTUserInviteByInvoteCode(Map map) {
        return tUserInviteMapper.selectTUserInviteByInvoteCode(map);
    }
}
