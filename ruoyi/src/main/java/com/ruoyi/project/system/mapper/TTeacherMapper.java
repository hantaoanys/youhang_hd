package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.TTeacher;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2020-05-10
 */
public interface TTeacherMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public TTeacher selectTTeacherById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param tTeacher 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TTeacher> selectTTeacherList(TTeacher tTeacher);

    /**
     * 新增【请填写功能名称】
     * 
     * @param tTeacher 【请填写功能名称】
     * @return 结果
     */
    public int insertTTeacher(TTeacher tTeacher);

    /**
     * 修改【请填写功能名称】
     * 
     * @param tTeacher 【请填写功能名称】
     * @return 结果
     */
    public int updateTTeacher(TTeacher tTeacher);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteTTeacherById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTTeacherByIds(Long[] ids);
}
