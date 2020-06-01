package com.ruoyi.project.system.service;

import java.util.List;
import java.util.Map;
import com.ruoyi.project.system.domain.TUserInvite;

/**
 * 用户邀请商Service接口
 * 
 * @author ruoyi
 * @date 2020-05-10
 */
public interface ITUserInviteService 
{
    /**
     * 查询用户邀请商
     * 
     * @param userId 用户邀请商ID
     * @return 用户邀请商
     */
    public TUserInvite selectTUserInviteById(Long userId);

    /**
     * 查询用户邀请商列表
     * 
     * @param tUserInvite 用户邀请商
     * @return 用户邀请商集合
     */
    public List<TUserInvite> selectTUserInviteList(TUserInvite tUserInvite);

    /**
     * 新增用户邀请商
     * 
     * @param tUserInvite 用户邀请商
     * @return 结果
     */
    public int insertTUserInvite(TUserInvite tUserInvite);

    /**
     * 修改用户邀请商
     * 
     * @param tUserInvite 用户邀请商
     * @return 结果
     */
    public int updateTUserInvite(TUserInvite tUserInvite);

    /**
     * 批量删除用户邀请商
     * 
     * @param userIds 需要删除的用户邀请商ID
     * @return 结果
     */
    public int deleteTUserInviteByIds(Long[] userIds);

    /**
     * 删除用户邀请商信息
     * 
     * @param userId 用户邀请商ID
     * @return 结果
     */
    public int deleteTUserInviteById(Long userId);

    TUserInvite selectTUserInviteByInvoteCode(Map map);
}
