package com.limuren.blog.util;

public class MyStringUtils {
	
//	@NotBlank:只用于String,不能为null且trim()之后size>0
	public static boolean isNotBlank(String str) {
		return (str != null && str.trim().length() > 0);
	}
//	@NotEmpty :不能为null，且Size>0
	public static boolean isNotEmpty(String str) {
		return (str!=null&&str.length()>0);
	}
//	@NotNull:不能为null，但可以为empty,没有Size的约束
	public static boolean isNotNull(String str) {
		return str!=null;
	}
	public static boolean isBlank(String value) {
		return !isNotBlank(value);
	}
	public static boolean isEmpty(String value) {
		return !isNotEmpty(value);
	}
	public static boolean isNull(String value) {
		return !isNotNull(value);
	}
	
}