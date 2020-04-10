package com.dys.springcloud.common.service.service;

import com.dys.springcloud.common.domain.BaseDomain;
import com.github.pagehelper.PageInfo;

public interface BaseService<T extends BaseDomain > {

	int insert(T t, String createBy);
	
	int update(T t, String updateBy);
	
	int delete(T t);
	
	int count(T t);
	
	T selectByOne(T t);
	
	PageInfo<T> page(int pageNum, int pageSize, T t);
}
