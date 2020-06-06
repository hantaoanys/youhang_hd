package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.TGoodcollect;

/**
 * 商品收藏Mapper接口
 * 
 * @author ruoyi
 * @date 2020-06-06
 */
public interface TGoodcollectMapper 
{
    /**
     * 查询商品收藏
     * 
     * @param id 商品收藏ID
     * @return 商品收藏
     */
    public TGoodcollect selectTGoodcollectById(Long id);

    /**
     * 查询商品收藏列表
     * 
     * @param tGoodcollect 商品收藏
     * @return 商品收藏集合
     */
    public List<TGoodcollect> selectTGoodcollectList(TGoodcollect tGoodcollect);

    /**
     * 新增商品收藏
     * 
     * @param tGoodcollect 商品收藏
     * @return 结果
     */
    public int insertTGoodcollect(TGoodcollect tGoodcollect);

    /**
     * 修改商品收藏
     * 
     * @param tGoodcollect 商品收藏
     * @return 结果
     */
    public int updateTGoodcollect(TGoodcollect tGoodcollect);

    /**
     * 删除商品收藏
     * 
     * @param id 商品收藏ID
     * @return 结果
     */
    public int deleteTGoodcollectById(Long id);

    /**
     * 批量删除商品收藏
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTGoodcollectByIds(Long[] ids);
}
