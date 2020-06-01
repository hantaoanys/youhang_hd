package com.ruoyi.project.system.service;

import java.util.List;
import java.util.Map;
import com.ruoyi.project.system.domain.TAppUser;

/**
 * 用户信息Service接口
 * 
 * @author ruoyi
 * @date 2020-05-10
 */
public interface ITAppUserService 
{
    /**
     * 查询用户信息
     * 
     * @param userId 用户信息ID
     * @return 用户信息
     */
    public TAppUser selectTAppUserById(Long userId);

    /**
     * 查询用户信息列表
     * 
     * @param tAppUser 用户信息
     * @return 用户信息集合
     */
    public List<TAppUser> selectTAppUserList(TAppUser tAppUser);

    /**
     * 新增用户信息
     * 
     * @param tAppUser 用户信息
     * @return 结果
     */
    public int insertTAppUser(TAppUser tAppUser);

    /**
     * 修改用户信息
     * 
     * @param tAppUser 用户信息
     * @return 结果
     */
    public int updateTAppUser(TAppUser tAppUser);

    /**
     * 批量删除用户信息
     * 
     * @param userIds 需要删除的用户信息ID
     * @return 结果
     */
    public int deleteTAppUserByIds(Long[] userIds);

    /**
     * 删除用户信息信息
     * 
     * @param userId 用户信息ID
     * @return 结果
     */
    public int deleteTAppUserById(Long userId);

    TAppUser selectTAppUserByPhone(Map uMap);

}
