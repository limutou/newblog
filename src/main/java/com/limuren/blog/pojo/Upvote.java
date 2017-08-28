package com.limuren.blog.pojo;

import java.util.Date;

public class Upvote {
    private Integer upvoteid;

    private Integer opuser;

    private Integer article;

    private Integer comment;

    private String loginip;

    private Integer status;

    private Date createtime;

    private Date updatetime;

    public Upvote(Integer upvoteid, Integer opuser, Integer article, Integer comment, String loginip, Integer status, Date createtime, Date updatetime) {
        this.upvoteid = upvoteid;
        this.opuser = opuser;
        this.article = article;
        this.comment = comment;
        this.loginip = loginip;
        this.status = status;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public Upvote() {
        super();
    }

    public Integer getUpvoteid() {
        return upvoteid;
    }

    public void setUpvoteid(Integer upvoteid) {
        this.upvoteid = upvoteid;
    }

    public Integer getOpuser() {
        return opuser;
    }

    public void setOpuser(Integer opuser) {
        this.opuser = opuser;
    }

    public Integer getArticle() {
        return article;
    }

    public void setArticle(Integer article) {
        this.article = article;
    }

    public Integer getComment() {
        return comment;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
    }

    public String getLoginip() {
        return loginip;
    }

    public void setLoginip(String loginip) {
        this.loginip = loginip == null ? null : loginip.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}