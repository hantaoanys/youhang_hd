package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.system.domain.TGoodcollect;
import com.ruoyi.project.system.mapper.TGoodcollectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.TGoodsMapper;
import com.ruoyi.project.system.domain.TGoods;
import com.ruoyi.project.system.service.ITGoodsService;

/**
 * 商品Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-27
 */
@Service
public class TGoodsServiceImpl implements ITGoodsService 
{
    @Autowired
    private TGoodsMapper tGoodsMapper;

    @Autowired
    private TGoodcollectMapper tGoodcollectMapper;

    /**
     * 查询商品
     * 
     * @param id 商品ID
     * @return 商品
     */
    @Override
    public TGoods selectTGoodsById(Long id)
    {
        TGoods tgood = tGoodsMapper.selectTGoodsById(id);
        TGoodcollect tmep = new TGoodcollect();
        tmep.setGoodId(id.toString());
        List<TGoodcollect> tGoodcollects = tGoodcollectMapper.selectTGoodcollectList(tmep);
        if (null != tGoodcollects && tGoodcollects.size()>0){
            tgood.setCollectNumber(tGoodcollects.size());
        }else {
            tgood.setCollectNumber(0);
        }
         return tgood;
    }

    /**
     * 查询商品列表
     * 
     * @param tGoods 商品
     * @return 商品
     */
    @Override
    public List<TGoods> selectTGoodsList(TGoods tGoods)
    {
        return tGoodsMapper.selectTGoodsList(tGoods);
    }

    /**
     * 新增商品
     * 
     * @param tGoods 商品
     * @return 结果
     */
    @Override
    public int insertTGoods(TGoods tGoods)
    {
        tGoods.setCreateTime(DateUtils.getNowDate());
        return tGoodsMapper.insertTGoods(tGoods);
    }

    /**
     * 修改商品
     * 
     * @param tGoods 商品
     * @return 结果
     */
    @Override
    public int updateTGoods(TGoods tGoods)
    {
        tGoods.setUpdateTime(DateUtils.getNowDate());
        return tGoodsMapper.updateTGoods(tGoods);
    }

    /**
     * 批量删除商品
     * 
     * @param ids 需要删除的商品ID
     * @return 结果
     */
    @Override
    public int deleteTGoodsByIds(Long[] ids)
    {
        return tGoodsMapper.deleteTGoodsByIds(ids);
    }

    /**
     * 删除商品信息
     * 
     * @param id 商品ID
     * @return 结果
     */
    @Override
    public int deleteTGoodsById(Long id)
    {
        return tGoodsMapper.deleteTGoodsById(id);
    }
}
