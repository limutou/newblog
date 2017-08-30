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
	
	@PostMapping("addArticle.do")
	public ServerResponse addArticle(HttpSession session,Article article) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        //校验一下是否有权限
        if(userService.getUserRole(user.getUserid())>=Role.AUTHOR.getRole()){
        	//防止恶意操作
        	article.setArticleid(null);
        	
        	article.setUser(user.getUserid());
        	
        	article.setStatus(null);
        	
        	article.setCreatetime(null);
        	
        	article.setUpdatetime(null);
        	
            return articleService.addArticle(article);
        }else{
            return ServerResponse.createByErrorMessage("无权限操作,需要写作权限");
        }
	}
	@PostMapping("setArticle.do")
	public ServerResponse setArticle(HttpSession session,Article article) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        article.setUser(user.getUserid());
		return articleService.setArticle(article);
	}
	@GetMapping("deleteArticle.do")
	public ServerResponse deleteArticle(HttpSession session,Integer articleid) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        Article article = new Article();
        article.setArticleid(articleid);
        article.setUser(user.getUserid());
		return articleService.deleteArticle(article);
	}
	
	@GetMapping("getAllArticleList.do")
	public ServerResponse getAllArticleList(HttpSession session,
			@RequestParam(value = "categoryId",required = false)Integer categoryId,
            @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize",defaultValue = "10") int pageSize,
            @RequestParam(value = "orderBy",defaultValue = "") String orderBy) {
		return articleService.getAllArticleList(categoryId,pageNum,pageSize);
	}
	
	@GetMapping("getArticleDetail")
	public ServerResponse getArticlePage(HttpSession session,Integer articleid) {
		if(articleid==null) 
			return ServerResponse.createByErrorMessage("请指定文章");
		return articleService.getArticleDetailPage(articleid);
	}
}
