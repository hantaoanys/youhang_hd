package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.TUserInvitegoods;

/**
 * 邀请流水Mapper接口
 * 
 * @author ruoyi
 * @date 2020-06-10
 */
public interface TUserInvitegoodsMapper 
{
    /**
     * 查询邀请流水
     * 
     * @param id 邀请流水ID
     * @return 邀请流水
     */
    public TUserInvitegoods selectTUserInvitegoodsById(Long id);

    /**
     * 查询邀请流水列表
     * 
     * @param tUserInvitegoods 邀请流水
     * @return 邀请流水集合
     */
    public List<TUserInvitegoods> selectTUserInvitegoodsList(TUserInvitegoods tUserInvitegoods);

    /**
     * 新增邀请流水
     * 
     * @param tUserInvitegoods 邀请流水
     * @return 结果
     */
    public int insertTUserInvitegoods(TUserInvitegoods tUserInvitegoods);

    /**
     * 修改邀请流水
     * 
     * @param tUserInvitegoods 邀请流水
     * @return 结果
     */
    public int updateTUserInvitegoods(TUserInvitegoods tUserInvitegoods);

    /**
     * 删除邀请流水
     * 
     * @param id 邀请流水ID
     * @return 结果
     */
    public int deleteTUserInvitegoodsById(Long id);

    /**
     * 批量删除邀请流水
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTUserInvitegoodsByIds(Long[] ids);
}
