package com.limuren.blog.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.limuren.blog.common.ServerResponse;
import com.limuren.blog.mapper.ArticleMapper;
import com.limuren.blog.pojo.Article;
import com.limuren.blog.util.DateTimeUtil;
import com.limuren.blog.util.MyStringUtils;
import com.limuren.blog.vo.ArticleListVo;

@Service
public class ArticleService{
	
	private static Logger logger = LoggerFactory.getLogger(ArticleService.class);
	
	@Autowired
	ArticleMapper articleMapper;
	
	public ServerResponse addArticle(Article article) {
		if(MyStringUtils.isNotBlank(article.getTittle())
    			&&MyStringUtils.isNotBlank(article.getSimpletext())
    			&&MyStringUtils.isNotBlank(article.getRichtext())
    			) {
			
			if(article.getCategory()==null) {
		    	return ServerResponse.createByErrorMessage("请指定文章目录");
			}
			
			int count = 0;
			count = articleMapper.insertSelective(article);
			
			if(count==1) {
				return ServerResponse.createBySuccessMessage("文章添加成功");
			}
			if(count==0) {
				return ServerResponse.createByErrorMessage("文章添加失败");
			}
			logger.error("添加文章异常 {}",article);
			return ServerResponse.createByErrorMessage("系统异常");
		}
    	return ServerResponse.createByErrorMessage("文章标题、简介和内容不能为空");
	}
	
	public ServerResponse setArticle(Article article) {
		if(article.getArticleid()==null)
			return ServerResponse.createByErrorMessage("请指定文章");
		Article save = articleMapper.selectByPrimaryKey(article.getArticleid());
		if(save==null) {
			return ServerResponse.createByErrorMessage("指定文章不存在");
		}
		Integer updateUser = save.getUser();
		if(updateUser!=article.getUser()) {
			return ServerResponse.createByErrorMessage("无权限操作当前文章");
		}
		if(article.getCategory()==null&&article.getStatus()==null) {
			return ServerResponse.createByErrorMessage("操作错误");
		}
		Article update = new Article();
		update.setArticleid(article.getArticleid());
		update.setCategory(article.getCategory());
		update.setTittle(article.getTittle());
		update.setSimpletext(article.getSimpletext());
		update.setImgurl(article.getImgurl());
		update.setStatus(article.getStatus());
		update.setRichtext(article.getRichtext());
		int count = articleMapper.updateByPrimaryKeySelective(update);
		if(count==1)
			return ServerResponse.createBySuccessMessage("修改成功");
		if(count==0)
			return ServerResponse.createByErrorMessage("修改失败");
		logger.error("修改文章错误{}",article);
		return ServerResponse.createByErrorMessage("服务器异常");
	}
	public ServerResponse deleteArticle(Article article) {
		if(article.getArticleid()==null)
			return ServerResponse.createByErrorMessage("请指定文章");
		Article save = articleMapper.selectByPrimaryKey(article.getArticleid());
		if(save==null) {
			return ServerResponse.createByErrorMessage("指定文章不存在");
		}
		Integer updateUser = save.getUser();
		if(updateUser!=article.getUser()) {
			return ServerResponse.createByErrorMessage("无权限操作当前文章");
		}
		int count = articleMapper.deleteByPrimaryKey(article.getArticleid());
		if(count==1) return ServerResponse.createBySuccessMessage("删除文章成功");
		return ServerResponse.createByErrorMessage("删除文章失败");
	}

	public ServerResponse getAllArticleList(Integer categoryId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Article> list = null;
		if(categoryId!=null) {
			list=articleMapper.getAllArticleListByCategory(categoryId);
		}else {
			list=articleMapper.getAllArticleList();
		}
		PageInfo pageResult = new PageInfo(list);
		
		pageResult.setList(assembleArticleListVo(list));
		return ServerResponse.createBySuccess(pageResult);
	}

	private List<ArticleListVo> assembleArticleListVo(List<Article> list) {
		List<ArticleListVo> voList = new ArrayList();
		ArticleListVo vo ;
		for (Article article : list) {
			vo = new ArticleListVo();
			voList.add(vo);
			vo.articleid = article.getArticleid();
			vo.user = article.getUser();
			vo.category = article.getCategory();
			vo.imgurl = article.getImgurl();
			vo.tittle = article.getTittle();
			vo.simpletext = article.getSimpletext();
			vo.createtime = DateTimeUtil.dateToStr(article.getCreatetime());
			vo.updatetime = DateTimeUtil.dateToStr(article.getUpdatetime());
		}
		return voList;
	}

	public ServerResponse getArticleDetailPage(Integer articleid) {
		Article article = articleMapper.selectByPrimaryKey(articleid);
		if(article==null)
			return ServerResponse.createByErrorMessage("查看的文章不存在可能已被删除");
		
		return ServerResponse.createBySuccess(assembleArticleDetailPageVo(article));
	}

	private Object assembleArticleDetailPageVo(Article article) {
		
		return null;
	}
	
}
