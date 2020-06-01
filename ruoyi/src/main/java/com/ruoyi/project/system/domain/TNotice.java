package com.ruoyi.project.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 公告对象 t_notice
 * 
 * @author ruoyi
 * @date 2020-05-13
 */
public class TNotice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 公告名称 */
    @Excel(name = "公告名称")
    private String noticeName;

    /** 公告内容 */
    @Excel(name = "公告内容")
    private String noticeDiscription;

    /** 排序 */
    @Excel(name = "排序")
    private String noticeSeq;

    /** 删除时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "删除时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deleteTime;

    /** 是否展示 */
    @Excel(name = "是否展示")
    private String isDeleted;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createUser;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setNoticeName(String noticeName) 
    {
        this.noticeName = noticeName;
    }

    public String getNoticeName() 
    {
        return noticeName;
    }
    public void setNoticeDiscription(String noticeDiscription) 
    {
        this.noticeDiscription = noticeDiscription;
    }

    public String getNoticeDiscription() 
    {
        return noticeDiscription;
    }
    public void setNoticeSeq(String noticeSeq) 
    {
        this.noticeSeq = noticeSeq;
    }

    public String getNoticeSeq() 
    {
        return noticeSeq;
    }
    public void setDeleteTime(Date deleteTime) 
    {
        this.deleteTime = deleteTime;
    }

    public Date getDeleteTime() 
    {
        return deleteTime;
    }
    public void setIsDeleted(String isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public String getIsDeleted() 
    {
        return isDeleted;
    }
    public void setCreateUser(String createUser) 
    {
        this.createUser = createUser;
    }

    public String getCreateUser() 
    {
        return createUser;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("noticeName", getNoticeName())
            .append("noticeDiscription", getNoticeDiscription())
            .append("noticeSeq", getNoticeSeq())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("deleteTime", getDeleteTime())
            .append("isDeleted", getIsDeleted())
            .append("createUser", getCreateUser())
            .toString();
    }
}
