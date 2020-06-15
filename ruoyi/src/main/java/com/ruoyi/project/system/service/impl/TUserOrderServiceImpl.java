package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.TUserOrderMapper;
import com.ruoyi.project.system.domain.TUserOrder;
import com.ruoyi.project.system.service.ITUserOrderService;

/**
 * 用户商品Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-01
 */
@Service
public class TUserOrderServiceImpl implements ITUserOrderService 
{
    @Autowired
    private TUserOrderMapper tUserOrderMapper;

    /**
     * 查询用户商品
     * 
     * @param id 用户商品ID
     * @return 用户商品
     */
    @Override
    public TUserOrder selectTUserOrderById(Long id)
    {
        return tUserOrderMapper.selectTUserOrderById(id);
    }

    /**
     * 查询用户商品列表
     * 
     * @param tUserOrder 用户商品
     * @return 用户商品
     */
    @Override
    public List<TUserOrder> selectTUserOrderList(TUserOrder tUserOrder)
    {
        return tUserOrderMapper.selectTUserOrderList(tUserOrder);
    }

    /**
     * 新增用户商品
     * 
     * @param tUserOrder 用户商品
     * @return 结果
     */
    @Override
    public int insertTUserOrder(TUserOrder tUserOrder)
    {
        tUserOrder.setCreateTime(DateUtils.getNowDate());
        return tUserOrderMapper.insertTUserOrder(tUserOrder);
    }

    /**
     * 修改用户商品
     * 
     * @param tUserOrder 用户商品
     * @return 结果
     */
    @Override
    public int updateTUserOrder(TUserOrder tUserOrder)
    {
        tUserOrder.setUpdateTime(DateUtils.getNowDate());
        return tUserOrderMapper.updateTUserOrder(tUserOrder);
    }

    /**
     * 批量删除用户商品
     * 
     * @param ids 需要删除的用户商品ID
     * @return 结果
     */
    @Override
    public int deleteTUserOrderByIds(Long[] ids)
    {
        return tUserOrderMapper.deleteTUserOrderByIds(ids);
    }

    /**
     * 删除用户商品信息
     * 
     * @param id 用户商品ID
     * @return 结果
     */
    @Override
    public int deleteTUserOrderById(Long id)
    {
        return tUserOrderMapper.deleteTUserOrderById(id);
    }

    @Override
    public void updateOrderStatus(TUserOrder tUserOrder) {
        tUserOrderMapper.updateOrderStatus(tUserOrder);
    }
}
