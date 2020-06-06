package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.TGoodcollectMapper;
import com.ruoyi.project.system.domain.TGoodcollect;
import com.ruoyi.project.system.service.ITGoodcollectService;

/**
 * 商品收藏Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-06
 */
@Service
public class TGoodcollectServiceImpl implements ITGoodcollectService 
{
    @Autowired
    private TGoodcollectMapper tGoodcollectMapper;

    /**
     * 查询商品收藏
     * 
     * @param id 商品收藏ID
     * @return 商品收藏
     */
    @Override
    public TGoodcollect selectTGoodcollectById(Long id)
    {
        return tGoodcollectMapper.selectTGoodcollectById(id);
    }

    /**
     * 查询商品收藏列表
     * 
     * @param tGoodcollect 商品收藏
     * @return 商品收藏
     */
    @Override
    public List<TGoodcollect> selectTGoodcollectList(TGoodcollect tGoodcollect)
    {
        return tGoodcollectMapper.selectTGoodcollectList(tGoodcollect);
    }

    /**
     * 新增商品收藏
     * 
     * @param tGoodcollect 商品收藏
     * @return 结果
     */
    @Override
    public int insertTGoodcollect(TGoodcollect tGoodcollect)
    {
        tGoodcollect.setCreateTime(DateUtils.getNowDate());
        return tGoodcollectMapper.insertTGoodcollect(tGoodcollect);
    }

    /**
     * 修改商品收藏
     * 
     * @param tGoodcollect 商品收藏
     * @return 结果
     */
    @Override
    public int updateTGoodcollect(TGoodcollect tGoodcollect)
    {
        tGoodcollect.setUpdateTime(DateUtils.getNowDate());
        return tGoodcollectMapper.updateTGoodcollect(tGoodcollect);
    }

    /**
     * 批量删除商品收藏
     * 
     * @param ids 需要删除的商品收藏ID
     * @return 结果
     */
    @Override
    public int deleteTGoodcollectByIds(Long[] ids)
    {
        return tGoodcollectMapper.deleteTGoodcollectByIds(ids);
    }

    /**
     * 删除商品收藏信息
     * 
     * @param id 商品收藏ID
     * @return 结果
     */
    @Override
    public int deleteTGoodcollectById(Long id)
    {
        return tGoodcollectMapper.deleteTGoodcollectById(id);
    }
}
