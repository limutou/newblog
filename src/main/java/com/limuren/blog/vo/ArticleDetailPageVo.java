package com.limuren.blog.vo;

import java.util.Date;

import com.limuren.blog.util.DateTimeUtil;

public class ArticleDetailPageVo {
	
	private Integer articleid;
	
	private Integer id;
	
	private String name;
	
	private String tittle;
	
	private String richText;
	
	private String imgurl;
	
	private Boolean status;
	
	private Date updateTime;
	
	private Integer userid;
	
	private String nickname;
	
	private String userhead;
	
	private String signature;
	
	public ArticleDetailPageVo() {
		super();
	}

	public ArticleDetailPageVo(Integer articleid, Integer id, String name, String tittle, String richText,
			String imgurl, Boolean status, Date updateTime, Integer userid, String nickname, String userhead,
			String signature) {
		super();
		this.articleid = articleid;
		this.id = id;
		this.name = name;
		this.tittle = tittle;
		this.richText = richText;
		this.imgurl = imgurl;
		this.status = status;
		this.updateTime = updateTime;
		this.userid = userid;
		this.nickname = nickname;
		this.userhead = userhead;
		this.signature = signature;
	}

	public Integer getArticleid() {
		return articleid;
	}

	public void setArticleid(Integer articleid) {
		this.articleid = articleid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public String getRichText() {
		return richText;
	}

	public void setRichText(String richText) {
		this.richText = richText;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getUpdateTime() {
		return DateTimeUtil.dateToStr(updateTime);
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUserhead() {
		return userhead;
	}

	public void setUserhead(String userhead) {
		this.userhead = userhead;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	@Override
	public String toString() {
		return "ArticleDetailPageVo [articleid=" + articleid + ", id=" + id + ", name=" + name + ", tittle=" + tittle
				+ ", richText=" + richText + ", imgurl=" + imgurl + ", status=" + status + ", updateTime=" + updateTime
				+ ", userid=" + userid + ", nickname=" + nickname + ", userhead=" + userhead + ", signature="
				+ signature + "]";
	}
	
	
}
