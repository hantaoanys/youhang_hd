package com.ruoyi.project.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * app升级对象 t_appupdate
 * 
 * @author ruoyi
 * @date 2020-06-21
 */
public class TAppupdate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** Id */
    private Long id;

    /** 升级标题 */
    @Excel(name = "升级标题")
    private String title;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 版本号 */
    @Excel(name = "版本号")
    private String version;

    /** 链接1 */
    @Excel(name = "链接1")
    private String url;

    /** 链接2 */
    @Excel(name = "链接2")
    private String url2;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createDate;

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
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setdescription(String description) 
    {
        this.description = description;
    }

    public String getdescription() 
    {
        return description;
    }
    public void setVersion(String version) 
    {
        this.version = version;
    }

    public String getVersion() 
    {
        return version;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }
    public void setUrl2(String url2) 
    {
        this.url2 = url2;
    }

    public String getUrl2() 
    {
        return url2;
    }
    public void setCreateDate(Date createDate) 
    {
        this.createDate = createDate;
    }

    public Date getCreateDate() 
    {
        return createDate;
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
            .append("title", getTitle())
            .append("description", getdescription())
            .append("version", getVersion())
            .append("url", getUrl())
            .append("url2", getUrl2())
            .append("remark", getRemark())
            .append("createDate", getCreateDate())
            .append("param1", getParam1())
            .append("param2", getParam2())
            .toString();
    }
}
