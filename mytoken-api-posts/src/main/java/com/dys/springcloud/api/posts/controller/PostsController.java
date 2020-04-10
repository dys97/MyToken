package com.dys.springcloud.api.posts.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dys.springcloud.api.posts.service.PostsService;
import com.dys.springcloud.common.domain.TbPostsPost;
import com.dys.springcloud.common.dto.BaseResult;

@RestController
public class PostsController {

	@Autowired
	private PostsService postsService;

	@RequestMapping(value = "/{postGuid}", method = RequestMethod.GET)
	public BaseResult get(@PathVariable(value =  "postGuid", required = true) String postGuid){
		System.out.println("-----------------postGuid------------- :"+postGuid);
		return postsService.getPostsHandler(postGuid);
	}

	/**
	 * 保存管理员
	 * @param tbSysUser
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public BaseResult save(){
		// 初始化数据
		TbPostsPost tbPostsPost = new TbPostsPost();
		String optsBy = "test";
		tbPostsPost.setTitle("test");
		tbPostsPost.setTimePublished(new Date());
		tbPostsPost.setStatus("0");
		return postsService.savePostsHandler(tbPostsPost, optsBy);
	}

}
