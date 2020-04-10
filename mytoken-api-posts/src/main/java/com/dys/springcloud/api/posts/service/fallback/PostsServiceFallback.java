package com.dys.springcloud.api.posts.service.fallback;

import org.springframework.stereotype.Component;

import com.dys.springcloud.api.posts.service.PostsService;
import com.dys.springcloud.common.domain.TbPostsPost;
import com.dys.springcloud.common.dto.BaseResult;
import com.dys.springcloud.common.hystrix.Fallback;

@Component
public class PostsServiceFallback implements PostsService{

	@Override
	public BaseResult getPostsHandler(String postGuid) {
		return Fallback.BadGetWay();
	}

	@Override
	public BaseResult savePostsHandler(TbPostsPost tbPostsPost, String optsBy) {
		return Fallback.BadGetWay();
	}

	@Override
	public BaseResult pagePostsHandler(int pageNum, int pageSize) {
		return Fallback.BadGetWay();
	}

}
