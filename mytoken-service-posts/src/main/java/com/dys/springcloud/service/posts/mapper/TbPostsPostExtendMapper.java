package com.dys.springcloud.service.posts.mapper;

import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.stereotype.Repository;

import com.dys.springcloud.common.domain.TbPostsPost;
import com.dys.springcloud.common.service.utils.RedisCache;

import tk.mybatis.mapper.MyMapper;

@Repository
@CacheNamespace(implementation = RedisCache.class)
public interface TbPostsPostExtendMapper extends MyMapper<TbPostsPost>{

}
