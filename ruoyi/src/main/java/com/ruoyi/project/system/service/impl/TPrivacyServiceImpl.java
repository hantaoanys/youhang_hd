package com.ruoyi.project.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.TPrivacyMapper;
import com.ruoyi.project.system.domain.TPrivacy;
import com.ruoyi.project.system.service.ITPrivacyService;

/**
 * 隐私政策Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-27
 */
@Service
public class TPrivacyServiceImpl implements ITPrivacyService 
{
    @Autowired
    private TPrivacyMapper tPrivacyMapper;

    /**
     * 查询隐私政策
     * 
     * @param id 隐私政策ID
     * @return 隐私政策
     */
    @Override
    public TPrivacy selectTPrivacyById(Long id)
    {
        return tPrivacyMapper.selectTPrivacyById(id);
    }

    /**
     * 查询隐私政策列表
     * 
     * @param tPrivacy 隐私政策
     * @return 隐私政策
     */
    @Override
    public List<TPrivacy> selectTPrivacyList(TPrivacy tPrivacy)
    {
        return tPrivacyMapper.selectTPrivacyList(tPrivacy);
    }

    /**
     * 新增隐私政策
     * 
     * @param tPrivacy 隐私政策
     * @return 结果
     */
    @Override
    public int insertTPrivacy(TPrivacy tPrivacy)
    {
        return tPrivacyMapper.insertTPrivacy(tPrivacy);
    }

    /**
     * 修改隐私政策
     * 
     * @param tPrivacy 隐私政策
     * @return 结果
     */
    @Override
    public int updateTPrivacy(TPrivacy tPrivacy)
    {
        return tPrivacyMapper.updateTPrivacy(tPrivacy);
    }

    /**
     * 批量删除隐私政策
     * 
     * @param ids 需要删除的隐私政策ID
     * @return 结果
     */
    @Override
    public int deleteTPrivacyByIds(Long[] ids)
    {
        return tPrivacyMapper.deleteTPrivacyByIds(ids);
    }

    /**
     * 删除隐私政策信息
     * 
     * @param id 隐私政策ID
     * @return 结果
     */
    @Override
    public int deleteTPrivacyById(Long id)
    {
        return tPrivacyMapper.deleteTPrivacyById(id);
    }
}
