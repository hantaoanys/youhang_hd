package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.TNotice;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2020-05-10
 */
public interface TNoticeMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public TNotice selectTNoticeById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param tNotice 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TNotice> selectTNoticeList(TNotice tNotice);

    /**
     * 新增【请填写功能名称】
     * 
     * @param tNotice 【请填写功能名称】
     * @return 结果
     */
    public int insertTNotice(TNotice tNotice);

    /**
     * 修改【请填写功能名称】
     * 
     * @param tNotice 【请填写功能名称】
     * @return 结果
     */
    public int updateTNotice(TNotice tNotice);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteTNoticeById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTNoticeByIds(Long[] ids);
}
