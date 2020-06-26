package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.TAppupdate;

/**
 * app升级Service接口
 * 
 * @author ruoyi
 * @date 2020-06-21
 */
public interface ITAppupdateService 
{
    /**
     * 查询app升级
     * 
     * @param id app升级ID
     * @return app升级
     */
    public TAppupdate selectTAppupdateById(Long id);

    /**
     * 查询app升级列表
     * 
     * @param tAppupdate app升级
     * @return app升级集合
     */
    public List<TAppupdate> selectTAppupdateList(TAppupdate tAppupdate);

    /**
     * 新增app升级
     * 
     * @param tAppupdate app升级
     * @return 结果
     */
    public int insertTAppupdate(TAppupdate tAppupdate);

    /**
     * 修改app升级
     * 
     * @param tAppupdate app升级
     * @return 结果
     */
    public int updateTAppupdate(TAppupdate tAppupdate);

    /**
     * 批量删除app升级
     * 
     * @param ids 需要删除的app升级ID
     * @return 结果
     */
    public int deleteTAppupdateByIds(Long[] ids);

    /**
     * 删除app升级信息
     * 
     * @param id app升级ID
     * @return 结果
     */
    public int deleteTAppupdateById(Long id);
}
