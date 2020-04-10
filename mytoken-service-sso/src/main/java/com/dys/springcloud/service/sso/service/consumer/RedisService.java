package com.dys.springcloud.service.sso.service.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dys.springcloud.common.dto.BaseResult;
import com.dys.springcloud.service.sso.service.consumer.fallback.RedisServiceFallback;

@FeignClient(value = "MYTOKEN-SERVICE-REDIS", path = "/redis", fallback = RedisServiceFallback.class)
public interface RedisService {
	
	@RequestMapping(value = "/put", method = RequestMethod.POST)
	public BaseResult put(@RequestParam(value = "key")String key, @RequestParam(value = "value")String value, @RequestParam(value = "seconds")long seconds);
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public BaseResult get(@RequestParam(value = "key")String key);
}
 