package com.limuren.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.limuren.blog.common.Const;
import com.limuren.blog.common.ResponseCode;
import com.limuren.blog.common.ServerResponse;
import com.limuren.blog.mapper.ArticleMapper;
import com.limuren.blog.common.Const.Role;
import com.limuren.blog.pojo.Article;
import com.limuren.blog.pojo.User;
import com.limuren.blog.service.ArticleService;
import com.limuren.blog.service.UserService;

@RestController
public class ArticleController {
	
	@Autowired
	ArticleService articleService;
	
	@Autowired
	UserService userService;
	
	@GetMapping("getAllArticleList.do")
	public ServerResponse getAllArticleList(HttpSession session,
			@RequestParam(value = "categoryId",required = false)Integer categoryId,
            @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize",defaultValue = "10") int pageSize,
            @RequestParam(value = "orderBy",defaultValue = "") String orderBy) {
		return articleService.getAllArticleList(categoryId,pageNum,pageSize);
	}
	
	@GetMapping("getArticleDetail.do")
	public ServerResponse getArticlePage(HttpSession session,Integer articleid) {
		if(articleid==null)
			return ServerResponse.createByErrorMessage("请指定文章");
		return articleService.getArticleDetailPage(articleid);
	}
}
