package com.ruoyi.project.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 商品收藏对象 t_goodcollect
 * 
 * @author ruoyi
 * @date 2020-06-06
 */
public class TGoodcollect extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 商品ID */
    @Excel(name = "商品ID")
    private String goodId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private String userId;

    /** 用户手机号 */
    @Excel(name = "用户手机号")
    private Long userPhone;

    /** 用户联系方式 */
    @Excel(name = "用户联系方式")
    private String phone;

    /** 删除时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "删除时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deleteTime;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createUser;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updateUser;

    /** 删除人 */
    @Excel(name = "删除人")
    private String deleteUser;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private String isDelete;

    /** 预留字段1 */
    @Excel(name = "预留字段1")
    private String param1;

    /** 预留字段2 */
    @Excel(name = "预留字段2")
    private String param2;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setGoodId(String goodId) 
    {
        this.goodId = goodId;
    }

    public String getGoodId() 
    {
        return goodId;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setUserPhone(Long userPhone) 
    {
        this.userPhone = userPhone;
    }

    public Long getUserPhone() 
    {
        return userPhone;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setDeleteTime(Date deleteTime) 
    {
        this.deleteTime = deleteTime;
    }

    public Date getDeleteTime() 
    {
        return deleteTime;
    }
    public void setCreateUser(String createUser) 
    {
        this.createUser = createUser;
    }

    public String getCreateUser() 
    {
        return createUser;
    }
    public void setUpdateUser(String updateUser) 
    {
        this.updateUser = updateUser;
    }

    public String getUpdateUser() 
    {
        return updateUser;
    }
    public void setDeleteUser(String deleteUser) 
    {
        this.deleteUser = deleteUser;
    }

    public String getDeleteUser() 
    {
        return deleteUser;
    }
    public void setIsDelete(String isDelete) 
    {
        this.isDelete = isDelete;
    }

    public String getIsDelete() 
    {
        return isDelete;
    }
    public void setParam1(String param1) 
    {
        this.param1 = param1;
    }

    public String getParam1() 
    {
        return param1;
    }
    public void setParam2(String param2) 
    {
        this.param2 = param2;
    }

    public String getParam2() 
    {
        return param2;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("goodId", getGoodId())
            .append("userId", getUserId())
            .append("userPhone", getUserPhone())
            .append("phone", getPhone())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("deleteTime", getDeleteTime())
            .append("createUser", getCreateUser())
            .append("updateUser", getUpdateUser())
            .append("deleteUser", getDeleteUser())
            .append("isDelete", getIsDelete())
            .append("param1", getParam1())
            .append("param2", getParam2())
            .toString();
    }
}
