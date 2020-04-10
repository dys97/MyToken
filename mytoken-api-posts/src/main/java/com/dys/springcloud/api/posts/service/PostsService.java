package com.dys.springcloud.api.posts.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dys.springcloud.api.posts.service.fallback.PostsServiceFallback;
import com.dys.springcloud.common.domain.TbPostsPost;
import com.dys.springcloud.common.dto.BaseResult;

@FeignClient(value = "MYTOKEN-SERVICE-POSTS", path = "/v1/posts", fallback = PostsServiceFallback.class)
public interface PostsService {

	@RequestMapping(value = "/{postGuid}", method = RequestMethod.GET)
	public BaseResult getPostsHandler(@PathVariable(value =  "postGuid", required = true) String postGuid);
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public BaseResult savePostsHandler(@RequestBody(required = true) TbPostsPost tbPostsPost, 
			                           @RequestParam(value =  "optsBy", required = true) String optsBy);
	
	@RequestMapping(value = "/page/{pageNum}/{pageSize}", method = RequestMethod.GET)
	public BaseResult pagePostsHandler(@PathVariable(value =  "pageNum", required = true) int pageNum,
									   @PathVariable(value =  "pageSize", required = true) int pageSize);
}
