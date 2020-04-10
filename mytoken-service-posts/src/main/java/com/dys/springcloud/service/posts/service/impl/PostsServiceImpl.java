package com.dys.springcloud.service.posts.service.impl;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dys.springcloud.common.domain.TbPostsPost;
import com.dys.springcloud.common.service.service.impl.BaseServiceImpl;
import com.dys.springcloud.service.posts.mapper.TbPostsPostExtendMapper;
import com.dys.springcloud.service.posts.service.PostsService;

@Service
@Transactional(readOnly = true)
public class PostsServiceImpl extends BaseServiceImpl<TbPostsPost, TbPostsPostExtendMapper> implements PostsService{

	@Override
	@Transactional(readOnly = false)
	public int save(TbPostsPost tbPostsPost, String optsBy) {
		// 主键为空，表示新增
		if (StringUtils.isBlank(tbPostsPost.getPostGuid())) {
			tbPostsPost.setPostGuid(UUID.randomUUID().toString());
			return insert(tbPostsPost, optsBy);
		}
		// 编辑
		else {
			return update(tbPostsPost, optsBy);
		}
	}
	
}
