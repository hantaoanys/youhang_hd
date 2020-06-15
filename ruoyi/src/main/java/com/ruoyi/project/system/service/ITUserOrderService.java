package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.TUserOrder;

/**
 * 用户商品Service接口
 * 
 * @author ruoyi
 * @date 2020-06-01
 */
public interface ITUserOrderService 
{
    /**
     * 查询用户商品
     * 
     * @param id 用户商品ID
     * @return 用户商品
     */
    public TUserOrder selectTUserOrderById(Long id);

    /**
     * 查询用户商品列表
     * 
     * @param tUserOrder 用户商品
     * @return 用户商品集合
     */
    public List<TUserOrder> selectTUserOrderList(TUserOrder tUserOrder);

    /**
     * 新增用户商品
     * 
     * @param tUserOrder 用户商品
     * @return 结果
     */
    public int insertTUserOrder(TUserOrder tUserOrder);

    /**
     * 修改用户商品
     * 
     * @param tUserOrder 用户商品
     * @return 结果
     */
    public int updateTUserOrder(TUserOrder tUserOrder);

    /**
     * 批量删除用户商品
     * 
     * @param ids 需要删除的用户商品ID
     * @return 结果
     */
    public int deleteTUserOrderByIds(Long[] ids);

    /**
     * 删除用户商品信息
     * 
     * @param id 用户商品ID
     * @return 结果
     */
    public int deleteTUserOrderById(Long id);

    void updateOrderStatus(TUserOrder tUserOrder);
}
