package com.limuren.blog;

import javax.servlet.MultipartConfigElement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("com.limuren.blog.mapper")
public class NewblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewblogApplication.class, args);
	}
	// http://blog.csdn.net/u014695188/article/details/52398239
	// http://blog.csdn.net/a258831020/article/details/46889001
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize("10MB");
		factory.setMaxRequestSize("10MB");

		return factory.createMultipartConfig();
	}

}
