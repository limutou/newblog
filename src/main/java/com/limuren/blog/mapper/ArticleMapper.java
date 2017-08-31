package com.limuren.blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.limuren.blog.pojo.Article;
import com.limuren.blog.vo.ArticleDetailPageVo;

public interface ArticleMapper {
	
    int deleteByPrimaryKey(Integer articleid);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer articleid);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);
    
    /*List<Article> getAllArticleList();*/
    
    List<Article> getAllArticleListByCategory(@Param("category")Integer category);
    
    ArticleDetailPageVo selectArticleDetailPageByPrimaryKey(Integer articleid);
    
}