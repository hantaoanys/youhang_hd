package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.TTeacherMapper;
import com.ruoyi.project.system.domain.TTeacher;
import com.ruoyi.project.system.service.ITTeacherService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-10
 */
@Service
public class TTeacherServiceImpl implements ITTeacherService 
{
    @Autowired
    private TTeacherMapper tTeacherMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public TTeacher selectTTeacherById(Long id)
    {
        return tTeacherMapper.selectTTeacherById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param tTeacher 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<TTeacher> selectTTeacherList(TTeacher tTeacher)
    {
        return tTeacherMapper.selectTTeacherList(tTeacher);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param tTeacher 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertTTeacher(TTeacher tTeacher)
    {
        tTeacher.setCreateTime(DateUtils.getNowDate());
        return tTeacherMapper.insertTTeacher(tTeacher);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param tTeacher 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateTTeacher(TTeacher tTeacher)
    {
        tTeacher.setUpdateTime(DateUtils.getNowDate());
        return tTeacherMapper.updateTTeacher(tTeacher);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteTTeacherByIds(Long[] ids)
    {
        return tTeacherMapper.deleteTTeacherByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteTTeacherById(Long id)
    {
        return tTeacherMapper.deleteTTeacherById(id);
    }
}
