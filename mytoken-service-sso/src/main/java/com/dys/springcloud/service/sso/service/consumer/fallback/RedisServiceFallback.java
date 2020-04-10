package com.dys.springcloud.service.sso.service.consumer.fallback;

import org.springframework.stereotype.Component;

import com.dys.springcloud.common.dto.BaseResult;
import com.dys.springcloud.common.hystrix.Fallback;
import com.dys.springcloud.service.sso.service.consumer.RedisService;

@Component
public class RedisServiceFallback implements RedisService{

	@Override
	public BaseResult put(String key, String value, long seconds) {
		return Fallback.BadGetWay();
	}

	@Override
	public BaseResult get(String key) {
		return Fallback.BadGetWay();
	}

}
