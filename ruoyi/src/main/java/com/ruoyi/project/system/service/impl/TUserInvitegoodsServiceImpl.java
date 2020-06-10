package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.TUserInvitegoodsMapper;
import com.ruoyi.project.system.domain.TUserInvitegoods;
import com.ruoyi.project.system.service.ITUserInvitegoodsService;

/**
 * 邀请流水Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-10
 */
@Service
public class TUserInvitegoodsServiceImpl implements ITUserInvitegoodsService 
{
    @Autowired
    private TUserInvitegoodsMapper tUserInvitegoodsMapper;

    /**
     * 查询邀请流水
     * 
     * @param id 邀请流水ID
     * @return 邀请流水
     */
    @Override
    public TUserInvitegoods selectTUserInvitegoodsById(Long id)
    {
        return tUserInvitegoodsMapper.selectTUserInvitegoodsById(id);
    }

    /**
     * 查询邀请流水列表
     * 
     * @param tUserInvitegoods 邀请流水
     * @return 邀请流水
     */
    @Override
    public List<TUserInvitegoods> selectTUserInvitegoodsList(TUserInvitegoods tUserInvitegoods)
    {
        return tUserInvitegoodsMapper.selectTUserInvitegoodsList(tUserInvitegoods);
    }

    /**
     * 新增邀请流水
     * 
     * @param tUserInvitegoods 邀请流水
     * @return 结果
     */
    @Override
    public int insertTUserInvitegoods(TUserInvitegoods tUserInvitegoods)
    {
        tUserInvitegoods.setCreateTime(DateUtils.getNowDate());
        return tUserInvitegoodsMapper.insertTUserInvitegoods(tUserInvitegoods);
    }

    /**
     * 修改邀请流水
     * 
     * @param tUserInvitegoods 邀请流水
     * @return 结果
     */
    @Override
    public int updateTUserInvitegoods(TUserInvitegoods tUserInvitegoods)
    {
        tUserInvitegoods.setUpdateTime(DateUtils.getNowDate());
        return tUserInvitegoodsMapper.updateTUserInvitegoods(tUserInvitegoods);
    }

    /**
     * 批量删除邀请流水
     * 
     * @param ids 需要删除的邀请流水ID
     * @return 结果
     */
    @Override
    public int deleteTUserInvitegoodsByIds(Long[] ids)
    {
        return tUserInvitegoodsMapper.deleteTUserInvitegoodsByIds(ids);
    }

    /**
     * 删除邀请流水信息
     * 
     * @param id 邀请流水ID
     * @return 结果
     */
    @Override
    public int deleteTUserInvitegoodsById(Long id)
    {
        return tUserInvitegoodsMapper.deleteTUserInvitegoodsById(id);
    }
}
