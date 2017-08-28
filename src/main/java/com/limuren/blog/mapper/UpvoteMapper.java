package com.limuren.blog.mapper;

import com.limuren.blog.pojo.Upvote;

public interface UpvoteMapper {
    int deleteByPrimaryKey(Integer upvoteid);

    int insert(Upvote record);

    int insertSelective(Upvote record);

    Upvote selectByPrimaryKey(Integer upvoteid);

    int updateByPrimaryKeySelective(Upvote record);

    int updateByPrimaryKey(Upvote record);
}