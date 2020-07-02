package com.ruoyi.project.system.mapper;

import java.util.HashMap;
import java.util.List;
import com.ruoyi.project.system.domain.TUserInviteHistory;

/**
 * 提现流水Mapper接口
 * 
 * @author ruoyi
 * @date 2020-06-10
 */
public interface TUserInviteHistoryMapper 
{
    /**
     * 查询提现流水
     * 
     * @param userId 提现流水ID
     * @return 提现流水
     */
    public TUserInviteHistory selectTUserInviteHistoryById(Long userId);

    /**
     * 查询提现流水列表
     * 
     * @param tUserInviteHistory 提现流水
     * @return 提现流水集合
     */
    public List<TUserInviteHistory> selectTUserInviteHistoryList(TUserInviteHistory tUserInviteHistory);

    /**
     * 新增提现流水
     * 
     * @param tUserInviteHistory 提现流水
     * @return 结果
     */
    public int insertTUserInviteHistory(TUserInviteHistory tUserInviteHistory);

    /**
     * 修改提现流水
     * 
     * @param tUserInviteHistory 提现流水
     * @return 结果
     */
    public int updateTUserInviteHistory(TUserInviteHistory tUserInviteHistory);

    /**
     * 删除提现流水
     * 
     * @param userId 提现流水ID
     * @return 结果
     */
    public int deleteTUserInviteHistoryById(Long userId);

    /**
     * 批量删除提现流水
     * 
     * @param userIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTUserInviteHistoryByIds(Long[] userIds);

    void verify(HashMap map);
}
