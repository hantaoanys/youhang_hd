package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 用户邀请对象 t_user_invite
 * 
 * @author ruoyi
 * @date 2020-06-10
 */
public class TUserInvite extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private TUserInviteHistory tUserInviteHistory;

    private boolean canInvite;
    /** 用户ID */
    private Long userId;

    /** 用户手机号 */
    @Excel(name = "用户手机号")
    private String phone;

    /** 我的邀请码 */
    @Excel(name = "我的邀请码")
    private String inviteCode;

    /** 总邀请人数 */
    @Excel(name = "总邀请人数")
    private Long inviteNumberTotal;

    /** 总金额 */
    @Excel(name = "总金额")
    private Long inviteMoneyTotal;

    /** 未提现金额 */
    @Excel(name = "未提现金额")
    private Long inviteMoneyNot;

    /** 已提现金额 */
    @Excel(name = "已提现金额")
    private Long inviteMoneyAlready;

    /** 支付宝账户姓名 */
    @Excel(name = "支付宝账户姓名")
    private String zfbName;

    /** 支付宝账户 */
    @Excel(name = "支付宝账户")
    private String zfbAccount;

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
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
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
    public void setZfbName(String zfbName) 
    {
        this.zfbName = zfbName;
    }

    public String getZfbName() 
    {
        return zfbName;
    }
    public void setZfbAccount(String zfbAccount) 
    {
        this.zfbAccount = zfbAccount;
    }

    public String getZfbAccount() 
    {
        return zfbAccount;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public boolean isCanInvite() {
        return canInvite;
    }

    public void setCanInvite(boolean canInvite) {
        this.canInvite = canInvite;
    }

    public String getDelFlag()
    {
        return delFlag;
    }

    public TUserInviteHistory gettUserInviteHistory() {
        return tUserInviteHistory;
    }

    public void settUserInviteHistory(TUserInviteHistory tUserInviteHistory) {
        this.tUserInviteHistory = tUserInviteHistory;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("phone", getPhone())
            .append("inviteCode", getInviteCode())
            .append("inviteNumberTotal", getInviteNumberTotal())
            .append("inviteMoneyTotal", getInviteMoneyTotal())
            .append("inviteMoneyNot", getInviteMoneyNot())
            .append("inviteMoneyAlready", getInviteMoneyAlready())
            .append("zfbName", getZfbName())
            .append("zfbAccount", getZfbAccount())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
