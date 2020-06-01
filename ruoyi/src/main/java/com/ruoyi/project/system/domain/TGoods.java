package com.ruoyi.project.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 商品对象 t_goods
 * 
 * @author ruoyi
 * @date 2020-05-27
 */
public class TGoods extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 商品标题 */
    @Excel(name = "商品标题")
    private String name;

    /** 简述 */
    @Excel(name = "简述")
    private String sketch;

    /** 商品关键字 */
    @Excel(name = "商品关键字")
    private String keywords;

    /** 商品排序 */
    @Excel(name = "商品排序")
    private Long seq;

    /** 商品图片地址 */
    @Excel(name = "商品图片地址")
    private String picture;

    /** 商品浏览量 */
    @Excel(name = "商品浏览量")
    private Long view;

    /** 评分 */
    @Excel(name = "评分")
    private Double matchPoint;

    /** 商品总价 */
    @Excel(name = "商品总价")
    private Double price;

    /** 商品标价 */
    @Excel(name = "商品标价")
    private Long marketPrice;

    /** 商品库存 */
    @Excel(name = "商品库存")
    private Long stock;

    /** 销售数量 */
    @Excel(name = "销售数量")
    private Long sales;

    /** 商户ID */
    @Excel(name = "商户ID")
    private String merchantId;

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

    /** 是否上架 */
    @Excel(name = "是否上架")
    private String isDelete;

    /** $column.columnComment */
    @Excel(name = "是否上架")
    private String merchant;

    /** 佣金率 */
    @Excel(name = "佣金率")
    private String commissionrate;

    /** 佣金金额 */
    @Excel(name = "佣金金额")
    private Long invitemoney;

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
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setSketch(String sketch) 
    {
        this.sketch = sketch;
    }

    public String getSketch() 
    {
        return sketch;
    }
    public void setKeywords(String keywords) 
    {
        this.keywords = keywords;
    }

    public String getKeywords() 
    {
        return keywords;
    }
    public void setSeq(Long seq) 
    {
        this.seq = seq;
    }

    public Long getSeq() 
    {
        return seq;
    }
    public void setPicture(String picture) 
    {
        this.picture = picture;
    }

    public String getPicture() 
    {
        return picture;
    }
    public void setView(Long view) 
    {
        this.view = view;
    }

    public Long getView() 
    {
        return view;
    }
    public void setMatchPoint(Double matchPoint) 
    {
        this.matchPoint = matchPoint;
    }

    public Double getMatchPoint() 
    {
        return matchPoint;
    }
    public void setPrice(Double price) 
    {
        this.price = price;
    }

    public Double getPrice() 
    {
        return price;
    }
    public void setMarketPrice(Long marketPrice) 
    {
        this.marketPrice = marketPrice;
    }

    public Long getMarketPrice() 
    {
        return marketPrice;
    }
    public void setStock(Long stock) 
    {
        this.stock = stock;
    }

    public Long getStock() 
    {
        return stock;
    }
    public void setSales(Long sales) 
    {
        this.sales = sales;
    }

    public Long getSales() 
    {
        return sales;
    }
    public void setMerchantId(String merchantId) 
    {
        this.merchantId = merchantId;
    }

    public String getMerchantId() 
    {
        return merchantId;
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
    public void setMerchant(String merchant) 
    {
        this.merchant = merchant;
    }

    public String getMerchant() 
    {
        return merchant;
    }
    public void setCommissionrate(String commissionrate) 
    {
        this.commissionrate = commissionrate;
    }

    public String getCommissionrate() 
    {
        return commissionrate;
    }
    public void setInvitemoney(Long invitemoney) 
    {
        this.invitemoney = invitemoney;
    }

    public Long getInvitemoney() 
    {
        return invitemoney;
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
            .append("name", getName())
            .append("sketch", getSketch())
            .append("keywords", getKeywords())
            .append("seq", getSeq())
            .append("picture", getPicture())
            .append("view", getView())
            .append("matchPoint", getMatchPoint())
            .append("price", getPrice())
            .append("marketPrice", getMarketPrice())
            .append("stock", getStock())
            .append("sales", getSales())
            .append("merchantId", getMerchantId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("deleteTime", getDeleteTime())
            .append("createUser", getCreateUser())
            .append("updateUser", getUpdateUser())
            .append("deleteUser", getDeleteUser())
            .append("isDelete", getIsDelete())
            .append("merchant", getMerchant())
            .append("commissionrate", getCommissionrate())
            .append("invitemoney", getInvitemoney())
            .append("param1", getParam1())
            .append("param2", getParam2())
            .toString();
    }
}
