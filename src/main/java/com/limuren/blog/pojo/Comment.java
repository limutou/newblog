package com.limuren.blog.pojo;

import java.util.Date;

public class Comment {
    private Integer commentid;

    private String commentcontent;

    private Integer opuser;

    private Integer article;

    private Integer comment;

    private String loginip;

    private Integer status;

    private Date createtime;

    private Date updatetime;

    public Comment(Integer commentid, String commentcontent, Integer opuser, Integer article, Integer comment, String loginip, Integer status, Date createtime, Date updatetime) {
        this.commentid = commentid;
        this.commentcontent = commentcontent;
        this.opuser = opuser;
        this.article = article;
        this.comment = comment;
        this.loginip = loginip;
        this.status = status;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public Comment() {
        super();
    }

    public Integer getCommentid() {
        return commentid;
    }

    public void setCommentid(Integer commentid) {
        this.commentid = commentid;
    }

    public String getCommentcontent() {
        return commentcontent;
    }

    public void setCommentcontent(String commentcontent) {
        this.commentcontent = commentcontent == null ? null : commentcontent.trim();
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