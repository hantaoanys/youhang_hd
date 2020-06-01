package com.ruoyi.project.system.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 t_news
 * 
 * @author ruoyi
 * @date 2020-05-10
 */
public class TNews extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String newsTitle;

    /** 新闻类型 */
    @Excel(name = "新闻类型")
    private String newsType;

    /** 新闻排序字段 */
    @Excel(name = "新闻排序字段")
    private Long newsSeq;

    /** $column.columnComment */
    @Excel(name = "新闻排序字段")
    private String newsBody;

    /** $column.columnComment */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "新闻排序字段", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deleteTime;

    /** $column.columnComment */
    @Excel(name = "新闻排序字段")
    private String createUser;

    /** $column.columnComment */
    @Excel(name = "新闻排序字段")
    private String updateUser;

    /** $column.columnComment */
    @Excel(name = "新闻排序字段")
    private String deleteUser;

    /** $column.columnComment */
    @Excel(name = "新闻排序字段")
    private String isDelete;

    /** $column.columnComment */
    @Excel(name = "新闻排序字段")
    private Long likeNumber;

    /** $column.columnComment */
    @Excel(name = "新闻排序字段")
    private Long viewNumber;

    /** $column.columnComment */
    @Excel(name = "新闻排序字段")
    private Long commentNumber;

    /** $column.columnComment */
    @Excel(name = "新闻排序字段")
    private String newsPicture;

    /** $column.columnComment */
    @Excel(name = "新闻排序字段")
    private String param1;

    /** $column.columnComment */
    @Excel(name = "新闻排序字段")
    private String param2;

    /** $column.columnComment */
    @Excel(name = "新闻排序字段")
    private String param3;

    /** $column.columnComment */
    @Excel(name = "新闻排序字段")
    private String param4;

    /** $column.columnComment */
    @Excel(name = "新闻排序字段")
    private String param5;

    /** $column.columnComment */
    @Excel(name = "新闻排序字段")
    private String param6;

    /** $column.columnComment */
    @Excel(name = "新闻排序字段")
    private String param7;

    /** $column.columnComment */
    @Excel(name = "新闻排序字段")
    private String param8;

    /** $column.columnComment */
    @Excel(name = "新闻排序字段")
    private String param9;

    /** $column.columnComment */
    @Excel(name = "新闻排序字段")
    private String param10;

    List<TNewsComment> newComment;

    boolean isLike;
    boolean isCollect;

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public boolean isCollect() {
        return isCollect;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
    }

    public List<TNewsComment> getNewComment() {
        return newComment;
    }

    public void setNewComment(List<TNewsComment> newComment) {
        this.newComment = newComment;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setNewsTitle(String newsTitle) 
    {
        this.newsTitle = newsTitle;
    }

    public String getNewsTitle() 
    {
        return newsTitle;
    }
    public void setNewsType(String newsType) 
    {
        this.newsType = newsType;
    }

    public String getNewsType() 
    {
        return newsType;
    }
    public void setNewsSeq(Long newsSeq) 
    {
        this.newsSeq = newsSeq;
    }

    public Long getNewsSeq() 
    {
        return newsSeq;
    }
    public void setNewsBody(String newsBody) 
    {
        this.newsBody = newsBody;
    }

    public String getNewsBody() 
    {
        return newsBody;
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
    public void setLikeNumber(Long likeNumber) 
    {
        this.likeNumber = likeNumber;
    }

    public Long getLikeNumber() 
    {
        return likeNumber;
    }
    public void setViewNumber(Long viewNumber) 
    {
        this.viewNumber = viewNumber;
    }

    public Long getViewNumber() 
    {
        return viewNumber;
    }
    public void setCommentNumber(Long commentNumber) 
    {
        this.commentNumber = commentNumber;
    }

    public Long getCommentNumber() 
    {
        return commentNumber;
    }
    public void setNewsPicture(String newsPicture) 
    {
        this.newsPicture = newsPicture;
    }

    public String getNewsPicture() 
    {
        return newsPicture;
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
    public void setParam3(String param3) 
    {
        this.param3 = param3;
    }

    public String getParam3() 
    {
        return param3;
    }
    public void setParam4(String param4) 
    {
        this.param4 = param4;
    }

    public String getParam4() 
    {
        return param4;
    }
    public void setParam5(String param5) 
    {
        this.param5 = param5;
    }

    public String getParam5() 
    {
        return param5;
    }
    public void setParam6(String param6) 
    {
        this.param6 = param6;
    }

    public String getParam6() 
    {
        return param6;
    }
    public void setParam7(String param7) 
    {
        this.param7 = param7;
    }

    public String getParam7() 
    {
        return param7;
    }
    public void setParam8(String param8) 
    {
        this.param8 = param8;
    }

    public String getParam8() 
    {
        return param8;
    }
    public void setParam9(String param9) 
    {
        this.param9 = param9;
    }

    public String getParam9() 
    {
        return param9;
    }
    public void setParam10(String param10) 
    {
        this.param10 = param10;
    }

    public String getParam10() 
    {
        return param10;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("newsTitle", getNewsTitle())
            .append("newsType", getNewsType())
            .append("newsSeq", getNewsSeq())
            .append("newsBody", getNewsBody())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("deleteTime", getDeleteTime())
            .append("createUser", getCreateUser())
            .append("updateUser", getUpdateUser())
            .append("deleteUser", getDeleteUser())
            .append("isDelete", getIsDelete())
            .append("likeNumber", getLikeNumber())
            .append("viewNumber", getViewNumber())
            .append("commentNumber", getCommentNumber())
            .append("newsPicture", getNewsPicture())
            .append("param1", getParam1())
            .append("param2", getParam2())
            .append("param3", getParam3())
            .append("param4", getParam4())
            .append("param5", getParam5())
            .append("param6", getParam6())
            .append("param7", getParam7())
            .append("param8", getParam8())
            .append("param9", getParam9())
            .append("param10", getParam10())
            .toString();
    }
}
