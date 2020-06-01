package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.TPrivacy;

/**
 * 隐私政策Mapper接口
 * 
 * @author ruoyi
 * @date 2020-05-27
 */
public interface TPrivacyMapper 
{
    /**
     * 查询隐私政策
     * 
     * @param id 隐私政策ID
     * @return 隐私政策
     */
    public TPrivacy selectTPrivacyById(Long id);

    /**
     * 查询隐私政策列表
     * 
     * @param tPrivacy 隐私政策
     * @return 隐私政策集合
     */
    public List<TPrivacy> selectTPrivacyList(TPrivacy tPrivacy);

    /**
     * 新增隐私政策
     * 
     * @param tPrivacy 隐私政策
     * @return 结果
     */
    public int insertTPrivacy(TPrivacy tPrivacy);

    /**
     * 修改隐私政策
     * 
     * @param tPrivacy 隐私政策
     * @return 结果
     */
    public int updateTPrivacy(TPrivacy tPrivacy);

    /**
     * 删除隐私政策
     * 
     * @param id 隐私政策ID
     * @return 结果
     */
    public int deleteTPrivacyById(Long id);

    /**
     * 批量删除隐私政策
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTPrivacyByIds(Long[] ids);
}
