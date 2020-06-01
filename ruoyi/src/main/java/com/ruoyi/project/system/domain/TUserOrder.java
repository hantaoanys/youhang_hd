package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 用户商品对象 t_user_order
 * 
 * @author ruoyi
 * @date 2020-06-01
 */
public class TUserOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodName;

    /** 图片地址 */
    @Excel(name = "图片地址")
    private String goodPath;

    /** 商品id */
    @Excel(name = "商品id")
    private Long goodId;

    /** 商品类型 */
    @Excel(name = "商品类型")
    private Long goodType;

    /** 消费金额 */
    @Excel(name = "消费金额")
    private Long payMoney;

    /** 服务进度 */
    @Excel(name = "服务进度")
    private String serviceProcess;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setGoodName(String goodName) 
    {
        this.goodName = goodName;
    }

    public String getGoodName() 
    {
        return goodName;
    }
    public void setGoodPath(String goodPath) 
    {
        this.goodPath = goodPath;
    }

    public String getGoodPath() 
    {
        return goodPath;
    }
    public void setGoodId(Long goodId) 
    {
        this.goodId = goodId;
    }

    public Long getGoodId() 
    {
        return goodId;
    }
    public void setGoodType(Long goodType) 
    {
        this.goodType = goodType;
    }

    public Long getGoodType() 
    {
        return goodType;
    }
    public void setPayMoney(Long payMoney) 
    {
        this.payMoney = payMoney;
    }

    public Long getPayMoney() 
    {
        return payMoney;
    }
    public void setServiceProcess(String serviceProcess) 
    {
        this.serviceProcess = serviceProcess;
    }

    public String getServiceProcess() 
    {
        return serviceProcess;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("goodName", getGoodName())
            .append("goodPath", getGoodPath())
            .append("goodId", getGoodId())
            .append("goodType", getGoodType())
            .append("payMoney", getPayMoney())
            .append("serviceProcess", getServiceProcess())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
