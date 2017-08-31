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
    
    /**
     * 查询全部文章的列表
     * @param category 根据目录ID查询 传入null时查询全部
     * @param status 文章状态，传入true时查询公开状态的文章，传入false时查询不公开状态文章，传入null时两种情况都查询
     * @return
     */
    List<Article> getAllArticleListByCategory(@Param("category")Integer category,@Param("status")Boolean status);
    
    /**
     * 查询文章详情
     * @param articleid 查询文章的ID
     * @param category 文章目录状态 传入null时查询全部
     * @param article 文章状态(公开/不公开) 传入null时查询全部
     * @return
     */
    ArticleDetailPageVo selectArticleDetailPageByPrimaryKey(@Param("articleid")Integer articleid,
    		@Param("category")Boolean category,@Param("article")Boolean article);
    
    
}