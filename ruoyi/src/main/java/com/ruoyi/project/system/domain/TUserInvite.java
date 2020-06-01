package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 用户邀请商对象 t_user_invite
 * 
 * @author ruoyi
 * @date 2020-05-10
 */
public class TUserInvite extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    private Long userId;

    /** 邀请码 */
    @Excel(name = "邀请码")
    private String inviteCode;

    /** 总邀请人数 */
    @Excel(name = "总邀请人数")
    private Long inviteNumberTotal;

    /** 未提现的邀请人数 */
    @Excel(name = "未提现的邀请人数")
    private Long inviteNumberNotMoney;

    /** 已提现的邀请人数 */
    @Excel(name = "已提现的邀请人数")
    private Long inviteNumberMoney;

    /** 总金额 */
    @Excel(name = "总金额")
    private Long inviteMoneyTotal;

    /** 未提现金额 */
    @Excel(name = "未提现金额")
    private Long inviteMoneyNot;

    /** 已提现金额 */
    @Excel(name = "已提现金额")
    private Long inviteMoneyAlready;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setInviteCode(String inviteCode) 
    {
        this.inviteCode = inviteCode;
    }

    public String getInviteCode() 
    {
        return inviteCode;
    }
    public void setInviteNumberTotal(Long inviteNumberTotal) 
    {
        this.inviteNumberTotal = inviteNumberTotal;
    }

    public Long getInviteNumberTotal() 
    {
        return inviteNumberTotal;
    }
    public void setInviteNumberNotMoney(Long inviteNumberNotMoney) 
    {
        this.inviteNumberNotMoney = inviteNumberNotMoney;
    }

    public Long getInviteNumberNotMoney() 
    {
        return inviteNumberNotMoney;
    }
    public void setInviteNumberMoney(Long inviteNumberMoney) 
    {
        this.inviteNumberMoney = inviteNumberMoney;
    }

    public Long getInviteNumberMoney() 
    {
        return inviteNumberMoney;
    }
    public void setInviteMoneyTotal(Long inviteMoneyTotal) 
    {
        this.inviteMoneyTotal = inviteMoneyTotal;
    }

    public Long getInviteMoneyTotal() 
    {
        return inviteMoneyTotal;
    }
    public void setInviteMoneyNot(Long inviteMoneyNot) 
    {
        this.inviteMoneyNot = inviteMoneyNot;
    }

    public Long getInviteMoneyNot() 
    {
        return inviteMoneyNot;
    }
    public void setInviteMoneyAlready(Long inviteMoneyAlready) 
    {
        this.inviteMoneyAlready = inviteMoneyAlready;
    }

    public Long getInviteMoneyAlready() 
    {
        return inviteMoneyAlready;
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
            .append("userId", getUserId())
            .append("inviteCode", getInviteCode())
            .append("inviteNumberTotal", getInviteNumberTotal())
            .append("inviteNumberNotMoney", getInviteNumberNotMoney())
            .append("inviteNumberMoney", getInviteNumberMoney())
            .append("inviteMoneyTotal", getInviteMoneyTotal())
            .append("inviteMoneyNot", getInviteMoneyNot())
            .append("inviteMoneyAlready", getInviteMoneyAlready())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
