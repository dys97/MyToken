package com.dys.springcloud.common.service.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dys.springcloud.common.domain.BaseDomain;
import com.dys.springcloud.common.service.service.BaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.MyMapper;

@Service
@Transactional(readOnly = true)
public class BaseServiceImpl<T extends BaseDomain, D extends MyMapper<T>> implements BaseService<T> {

	@Autowired
	private D dao;
	
	@Override
	@Transactional(readOnly = false)
	public int insert(T t, String createBy) {
		t.setCreateBy(createBy);
		t.setCreateDate(new Date());
		t.setUpdateBy(createBy);
		t.setUpdateDate(new Date());
		return dao.insert(t);
	}

	@Override
	@Transactional(readOnly = false)
	public int update(T t, String updateBy) {
		t.setUpdateBy(updateBy);
		t.setUpdateDate(new Date());
		return dao.updateByPrimaryKey(t);
	}

	@Override
	@Transactional(readOnly = false)
	public int delete(T t) {
		return dao.delete(t);
	}

	@Override
	@Transactional(readOnly = true)
	public int count(T t) {
		return dao.selectCount(t);
	}

	@Override
	@Transactional(readOnly = true)
	public T selectByOne(T t) {
		return dao.selectOne(t);
	}

	@SuppressWarnings("static-access")
	@Override
	public PageInfo<T> page(int pageNum, int pageSize, T t) {
		PageHelper pageHelper = new PageHelper();
		pageHelper.startPage(pageNum, pageSize);
		PageInfo<T> pageInfo = new PageInfo<>(dao.select(t));
		return pageInfo;
	}

}
