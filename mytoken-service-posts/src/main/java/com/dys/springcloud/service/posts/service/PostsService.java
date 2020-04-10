package com.dys.springcloud.service.posts.service;

import com.dys.springcloud.common.domain.TbPostsPost;
import com.dys.springcloud.common.service.service.BaseService;

public interface PostsService extends BaseService<TbPostsPost> {

	/**
	 * 保存或修改文章
	 * @param tbPostsPost
	 * @param optsBy 创建者
	 * @return
	 */
	int save(TbPostsPost tbPostsPost, String optsBy);
}
