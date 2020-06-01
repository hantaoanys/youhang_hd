package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 隐私政策对象 t_privacy
 * 
 * @author ruoyi
 * @date 2020-05-27
 */
public class TPrivacy extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 隐私政策名称 */
    @Excel(name = "隐私政策名称")
    private String privacyName;

    /** 隐私政策标题 */
    @Excel(name = "隐私政策标题")
    private String title;

    /** 隐私政策内容 */
    @Excel(name = "隐私政策内容")
    private String body;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPrivacyName(String privacyName) 
    {
        this.privacyName = privacyName;
    }

    public String getPrivacyName() 
    {
        return privacyName;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setBody(String body) 
    {
        this.body = body;
    }

    public String getBody() 
    {
        return body;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("privacyName", getPrivacyName())
            .append("title", getTitle())
            .append("body", getBody())
            .toString();
    }
}
