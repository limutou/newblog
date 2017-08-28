package com.limuren.blog.common;

public class Const {
	//Session
    public static final String CURRENT_USER = "currentUser";
    
    //UserService
    public static final String EMAIL = "email";
    public static final String USERNAME = "username";
    
	public static enum Role{
		READ(0,"只有最基本的阅读权限"),
		INTERACTIVE(10,"可以进行点赞、评论等操作"),
		AUTHOR(20,"拥有发表文章权限"),
		MANAGE(30,"低级管理权限"),
		ADMIN(50,"拥有管理员权限"),
		ROOT(100,"ROOT用戶做什么都行");
		private int role;
		private String desc;
		
		private Role(int role, String desc) {
			this.role = role;
			this.desc = desc;
		}
		public int getRole() {
			return role;
		}
		public String getDesc() {
			return desc;
		}
		
	}
	public static enum Status{
		NORMAL(0,"正常"),
		DESTROY(1,"删除"),
		TENTATIVE(2,"暂定"),
		;
		private int statusCode;
		private String statusName;
		private Status(int statusCode,String statusName) {
			this.statusCode=statusCode;
			this.statusName=statusName;
		}
		public int getStatusCode() {
			return statusCode;
		}
		public void setStatusCode(int statusCode) {
			this.statusCode = statusCode;
		}
		public String getStatusName() {
			return statusName;
		}
		public void setStatusName(String statusName) {
			this.statusName = statusName;
		}
		
	}
}
