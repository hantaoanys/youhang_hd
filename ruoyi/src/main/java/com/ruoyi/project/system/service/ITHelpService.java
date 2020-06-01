package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.THelp;

/**
 * 帮助中心Service接口
 * 
 * @author ruoyi
 * @date 2020-05-27
 */
public interface ITHelpService 
{
    /**
     * 查询帮助中心
     * 
     * @param id 帮助中心ID
     * @return 帮助中心
     */
    public THelp selectTHelpById(Long id);

    /**
     * 查询帮助中心列表
     * 
     * @param tHelp 帮助中心
     * @return 帮助中心集合
     */
    public List<THelp> selectTHelpList(THelp tHelp);

    /**
     * 新增帮助中心
     * 
     * @param tHelp 帮助中心
     * @return 结果
     */
    public int insertTHelp(THelp tHelp);

    /**
     * 修改帮助中心
     * 
     * @param tHelp 帮助中心
     * @return 结果
     */
    public int updateTHelp(THelp tHelp);

    /**
     * 批量删除帮助中心
     * 
     * @param ids 需要删除的帮助中心ID
     * @return 结果
     */
    public int deleteTHelpByIds(Long[] ids);

    /**
     * 删除帮助中心信息
     * 
     * @param id 帮助中心ID
     * @return 结果
     */
    public int deleteTHelpById(Long id);
}
