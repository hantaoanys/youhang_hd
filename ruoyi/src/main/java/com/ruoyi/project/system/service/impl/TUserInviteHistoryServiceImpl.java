package com.ruoyi.project.system.service.impl;

import java.util.HashMap;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.TUserInviteHistoryMapper;
import com.ruoyi.project.system.domain.TUserInviteHistory;
import com.ruoyi.project.system.service.ITUserInviteHistoryService;

/**
 * 提现流水Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-10
 */
@Service
public class TUserInviteHistoryServiceImpl implements ITUserInviteHistoryService 
{
    @Autowired
    private TUserInviteHistoryMapper tUserInviteHistoryMapper;

    /**
     * 查询提现流水
     * 
     * @param userId 提现流水ID
     * @return 提现流水
     */
    @Override
    public TUserInviteHistory selectTUserInviteHistoryById(Long userId)
    {
        return tUserInviteHistoryMapper.selectTUserInviteHistoryById(userId);
    }

    /**
     * 查询提现流水列表
     * 
     * @param tUserInviteHistory 提现流水
     * @return 提现流水
     */
    @Override
    public List<TUserInviteHistory> selectTUserInviteHistoryList(TUserInviteHistory tUserInviteHistory)
    {
        return tUserInviteHistoryMapper.selectTUserInviteHistoryList(tUserInviteHistory);
    }

    /**
     * 新增提现流水
     * 
     * @param tUserInviteHistory 提现流水
     * @return 结果
     */
    @Override
    public int insertTUserInviteHistory(TUserInviteHistory tUserInviteHistory)
    {
        tUserInviteHistory.setCreateTime(DateUtils.getNowDate());
        return tUserInviteHistoryMapper.insertTUserInviteHistory(tUserInviteHistory);
    }

    /**
     * 修改提现流水
     * 
     * @param tUserInviteHistory 提现流水
     * @return 结果
     */
    @Override
    public int updateTUserInviteHistory(TUserInviteHistory tUserInviteHistory)
    {
        tUserInviteHistory.setUpdateTime(DateUtils.getNowDate());
        return tUserInviteHistoryMapper.updateTUserInviteHistory(tUserInviteHistory);
    }

    /**
     * 批量删除提现流水
     * 
     * @param userIds 需要删除的提现流水ID
     * @return 结果
     */
    @Override
    public int deleteTUserInviteHistoryByIds(Long[] userIds)
    {
        return tUserInviteHistoryMapper.deleteTUserInviteHistoryByIds(userIds);
    }

    /**
     * 删除提现流水信息
     * 
     * @param userId 提现流水ID
     * @return 结果
     */
    @Override
    public int deleteTUserInviteHistoryById(Long userId)
    {
        return tUserInviteHistoryMapper.deleteTUserInviteHistoryById(userId);
    }

    @Override
    public void verify(HashMap map) {
        tUserInviteHistoryMapper.verify(map);
    }
}
