package com.ruoyi.project.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 新闻评论对象 t_news_collect
 * 
 * @author ruoyi
 * @date 2020-05-23
 */
public class TNewsCollect extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long newsId;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 用户姓名 */
    @Excel(name = "用户姓名")
    private String userName;

    /** 用户昵称 */
    @Excel(name = "用户昵称")
    private String userNickName;

    /** 是否收藏 */
    @Excel(name = "是否收藏")
    private String isCollect;

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

    /** 是否展示 */
    @Excel(name = "是否展示")
    private String isDelete;

    /** $column.columnComment */
    @Excel(name = "是否展示")
    private String param1;

    /** $column.columnComment */
    @Excel(name = "是否展示")
    private String param2;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setNewsId(Long newsId) 
    {
        this.newsId = newsId;
    }

    public Long getNewsId() 
    {
        return newsId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setUserNickName(String userNickName) 
    {
        this.userNickName = userNickName;
    }

    public String getUserNickName() 
    {
        return userNickName;
    }
    public void setIsCollect(String isCollect) 
    {
        this.isCollect = isCollect;
    }

    public String getIsCollect() 
    {
        return isCollect;
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
            .append("newsId", getNewsId())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("userNickName", getUserNickName())
            .append("isCollect", getIsCollect())
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
