package com.dys.springcloud.service.redis.service.impl;


import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.dys.springcloud.service.redis.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService{

	@Resource
	private RedisTemplate<String, String> redisTemplate;
	
	@Override
	public String put(String key, String value, long seconds) {
		redisTemplate.opsForValue().set(key, value, seconds,TimeUnit.SECONDS);
		return "OK";
	}

	@Override
	public String get(String key) {
		return redisTemplate.opsForValue().get(key);
	}

}
