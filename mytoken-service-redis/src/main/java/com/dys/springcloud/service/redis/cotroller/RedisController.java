package com.dys.springcloud.service.redis.cotroller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dys.springcloud.common.dto.BaseResult;
import com.dys.springcloud.common.utils.JsonUtils;
import com.dys.springcloud.service.redis.service.RedisService;

@RestController
@RequestMapping(value = "/redis")
public class RedisController {

	@Autowired
	private RedisService redisService;

	@RequestMapping(value = "/put", method = RequestMethod.POST)
	public BaseResult put(@RequestParam(required = true)String key, @RequestParam(required = true)String value, @RequestParam(required = true)long seconds) {
		Map<String, String> errorInfo = new HashMap<String, String>();
		
		//参数检查
		if(StringUtils.isBlank(key) || StringUtils.isBlank(value) || StringUtils.isBlank(Long.toString(seconds))) {
			errorInfo.put("400", "请求参数不能位空");
			return BaseResult.notOk(errorInfo);
		}
		//设置成功
		String  reslut = redisService.put(key, value, seconds);
		if("OK".equals(reslut)) {
			return BaseResult.ok();
		}
		//设置失败
		errorInfo.put("", "redis设置失败");
		return BaseResult.notOk(errorInfo);
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public BaseResult get(String key) {
		Map<String, String> errorInfo = new HashMap<String, String>();
		//参数检查
		if(StringUtils.isBlank(key)) {
			errorInfo.put("400", "请求参数不能位空");
			return BaseResult.notOk(errorInfo);
		}	
		//获取value
		String json = redisService.get(key);
		Object object = null;
		try {
			object = JsonUtils.json2pojo(json, Object.class);
		} catch (Exception e) {
			//不是对象类型
			e.printStackTrace();
			return BaseResult.ok((Object)json);
		}
		return BaseResult.ok(object);
	}


}
