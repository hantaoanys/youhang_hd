package com.ruoyi.project.system.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.TAppUserMapper;
import com.ruoyi.project.system.domain.TAppUser;
import com.ruoyi.project.system.service.ITAppUserService;

/**
 * 用户信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-10
 */
@Service
public class TAppUserServiceImpl implements ITAppUserService 
{
    @Autowired
    private TAppUserMapper tAppUserMapper;

    /**
     * 查询用户信息
     * 
     * @param userId 用户信息ID
     * @return 用户信息
     */
    @Override
    public TAppUser selectTAppUserById(Long userId)
    {
        return tAppUserMapper.selectTAppUserById(userId);
    }

    /**
     * 查询用户信息列表
     * 
     * @param tAppUser 用户信息
     * @return 用户信息
     */
    @Override
    public List<TAppUser> selectTAppUserList(TAppUser tAppUser)
    {
        return tAppUserMapper.selectTAppUserList(tAppUser);
    }

    /**
     * 新增用户信息
     * 
     * @param tAppUser 用户信息
     * @return 结果
     */
    @Override
    public int insertTAppUser(TAppUser tAppUser)
    {
        tAppUser.setCreateTime(DateUtils.getNowDate());
        return tAppUserMapper.insertTAppUser(tAppUser);
    }

    /**
     * 修改用户信息
     * 
     * @param tAppUser 用户信息
     * @return 结果
     */
    @Override
    public int updateTAppUser(TAppUser tAppUser)
    {
        tAppUser.setUpdateTime(DateUtils.getNowDate());
        return tAppUserMapper.updateTAppUser(tAppUser);
    }

    /**
     * 批量删除用户信息
     * 
     * @param userIds 需要删除的用户信息ID
     * @return 结果
     */
    @Override
    public int deleteTAppUserByIds(Long[] userIds)
    {
        return tAppUserMapper.deleteTAppUserByIds(userIds);
    }

    /**
     * 删除用户信息信息
     * 
     * @param userId 用户信息ID
     * @return 结果
     */
    @Override
    public int deleteTAppUserById(Long userId)
    {
        return tAppUserMapper.deleteTAppUserById(userId);
    }

    @Override
    public TAppUser selectTAppUserByPhone(Map uMap) {
        return tAppUserMapper.selectTAppUserByPhone(uMap);
    }

}
