package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 帮助中心对象 t_help
 * 
 * @author ruoyi
 * @date 2020-05-27
 */
public class THelp extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 帮助中心名称 */
    @Excel(name = "帮助中心名称")
    private String helpName;

    /** 帮助中心标题 */
    @Excel(name = "帮助中心标题")
    private String title;

    /** 帮助中心内容 */
    @Excel(name = "帮助中心内容")
    private String body;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setHelpName(String helpName) 
    {
        this.helpName = helpName;
    }

    public String getHelpName() 
    {
        return helpName;
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
            .append("helpName", getHelpName())
            .append("title", getTitle())
            .append("body", getBody())
            .toString();
    }
}
