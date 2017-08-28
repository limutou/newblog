package com.limuren.blog.pojo;

import java.util.Date;

public class Article {
    private Integer articleid;

    private Integer user;

    private String tittle;

    private String simpletext;

    private String imgurl;

    private Integer status;

    private Date createtime;

    private Date updatetime;

    private String richtext;

    public Article(Integer articleid, Integer user, String tittle, String simpletext, String imgurl, Integer status, Date createtime, Date updatetime, String richtext) {
        this.articleid = articleid;
        this.user = user;
        this.tittle = tittle;
        this.simpletext = simpletext;
        this.imgurl = imgurl;
        this.status = status;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.richtext = richtext;
    }

    public Article() {
        super();
    }

    public Integer getArticleid() {
        return articleid;
    }

    public void setArticleid(Integer articleid) {
        this.articleid = articleid;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle == null ? null : tittle.trim();
    }

    public String getSimpletext() {
        return simpletext;
    }

    public void setSimpletext(String simpletext) {
        this.simpletext = simpletext == null ? null : simpletext.trim();
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl == null ? null : imgurl.trim();
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

    public String getRichtext() {
        return richtext;
    }

    public void setRichtext(String richtext) {
        this.richtext = richtext == null ? null : richtext.trim();
    }
}