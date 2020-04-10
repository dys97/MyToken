package com.dys.springcloud.service.sso.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.dys.springcloud.common.domain.TbSysUser;
import com.dys.springcloud.common.dto.BaseResult;
import com.dys.springcloud.common.utils.JsonUtils;
import com.dys.springcloud.service.sso.mapper.TbSysUserMapper;
import com.dys.springcloud.service.sso.service.LoginService;
import com.dys.springcloud.service.sso.service.consumer.RedisService;

import tk.mybatis.mapper.entity.Example;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private RedisService redisService;
	
	@Autowired
	private TbSysUserMapper tbSysUserMapper;
	
	@Override
	public TbSysUser login(String loginCode, String plantPassword) {
		//从redis中查询是否存在，存在
		BaseResult baseResult = redisService.get(loginCode);
		Object redisData = baseResult.getData();
		if(redisData != null) {
			try {
				return (TbSysUser) redisData;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//不存在。从数据库中查询
		Example example = new Example(TbSysUser.class);
		example.createCriteria().andEqualTo("loginCode",loginCode);
		TbSysUser tbSysUser = tbSysUserMapper.selectOneByExample(example);
		String password = DigestUtils.md5DigestAsHex(plantPassword.getBytes());
		//数据库中存在
		if(password.equals(tbSysUser.getPassword())) {
			//存入redis中
			tbSysUser.setPassword(null);
			try {
				String key = loginCode;
				String value = JsonUtils.obj2json(tbSysUser);
				long seconds = 60 * 60 * 24;
				redisService.put(key, value, seconds);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return tbSysUser;
		}
		return null;
	}

}
