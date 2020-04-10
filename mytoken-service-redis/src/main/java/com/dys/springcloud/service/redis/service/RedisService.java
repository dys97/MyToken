package com.dys.springcloud.service.redis.service;


public interface RedisService {

	/**
	 * 
	 * @param key
	 * @param value
	 * @param seconds 存储数据的超时时间，单位s
	 * @return
	 */
	public String put(String key, String value, long  seconds);
	
	/**
	 * 
	 * @param key
	 * @return value
	 */
	public String get(String key);
	
}
