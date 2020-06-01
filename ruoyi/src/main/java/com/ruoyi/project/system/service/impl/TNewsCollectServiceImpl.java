package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.TNewsCollectMapper;
import com.ruoyi.project.system.domain.TNewsCollect;
import com.ruoyi.project.system.service.ITNewsCollectService;

/**
 * 新闻评论Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-23
 */
@Service
public class TNewsCollectServiceImpl implements ITNewsCollectService 
{
    @Autowired
    private TNewsCollectMapper tNewsCollectMapper;

    /**
     * 查询新闻评论
     * 
     * @param id 新闻评论ID
     * @return 新闻评论
     */
    @Override
    public TNewsCollect selectTNewsCollectById(Long id)
    {
        return tNewsCollectMapper.selectTNewsCollectById(id);
    }

    /**
     * 查询新闻评论列表
     * 
     * @param tNewsCollect 新闻评论
     * @return 新闻评论
     */
    @Override
    public List<TNewsCollect> selectTNewsCollectList(TNewsCollect tNewsCollect)
    {
        return tNewsCollectMapper.selectTNewsCollectList(tNewsCollect);
    }

    /**
     * 新增新闻评论
     * 
     * @param tNewsCollect 新闻评论
     * @return 结果
     */
    @Override
    public int insertTNewsCollect(TNewsCollect tNewsCollect)
    {
        tNewsCollect.setCreateTime(DateUtils.getNowDate());
        return tNewsCollectMapper.insertTNewsCollect(tNewsCollect);
    }

    /**
     * 修改新闻评论
     * 
     * @param tNewsCollect 新闻评论
     * @return 结果
     */
    @Override
    public int updateTNewsCollect(TNewsCollect tNewsCollect)
    {
        tNewsCollect.setUpdateTime(DateUtils.getNowDate());
        return tNewsCollectMapper.updateTNewsCollect(tNewsCollect);
    }

    /**
     * 批量删除新闻评论
     * 
     * @param ids 需要删除的新闻评论ID
     * @return 结果
     */
    @Override
    public int deleteTNewsCollectByIds(Long[] ids)
    {
        return tNewsCollectMapper.deleteTNewsCollectByIds(ids);
    }

    /**
     * 删除新闻评论信息
     * 
     * @param id 新闻评论ID
     * @return 结果
     */
    @Override
    public int deleteTNewsCollectById(Long id)
    {
        return tNewsCollectMapper.deleteTNewsCollectById(id);
    }
}
