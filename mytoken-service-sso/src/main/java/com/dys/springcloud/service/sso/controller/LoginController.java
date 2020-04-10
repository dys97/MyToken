package com.dys.springcloud.service.sso.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dys.springcloud.common.domain.TbSysUser;
import com.dys.springcloud.common.dto.BaseResult;
import com.dys.springcloud.common.utils.CookieUtils;
import com.dys.springcloud.service.sso.service.LoginService;
import com.dys.springcloud.service.sso.service.consumer.RedisService;

@RestController
@RequestMapping(value = "/sso")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private RedisService redisService;

	/**
	 * 登录
	 * @param loginCode
	 * @param plantPassword
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public BaseResult login(@RequestParam(required = true)String loginCode, 
							@RequestParam(required = true)String plantPassword,
							HttpServletResponse response,
							HttpServletRequest request) {
		Map<String, String> errorInfo = new HashMap<String, String>();
		//参数检查
		if(StringUtils.isBlank(loginCode) || StringUtils.isBlank(plantPassword)) {
			errorInfo.put("400", "请求参数不能为空");
			return BaseResult.notOk(errorInfo);
		}
		TbSysUser tbSysUser = loginService.login(loginCode, plantPassword);
		BaseResult baseResult = null;
		//登录失败
		if(tbSysUser == null) {
			errorInfo.put("message", "用户名或密码错误");
			return BaseResult.notOk(errorInfo);
		}else {	//登录成功
			String cookie = UUID.randomUUID().toString();
			// 将 token 放入缓存
			String key = "token";
			String value = cookie;
			long seconds = 60 * 60 * 24;
			baseResult = redisService.put(key, value, seconds);
			if("ok".equals(baseResult.getResult())) {	//设置cookie
				CookieUtils.setCookie(request, response, "token", value, (int) seconds);
				return BaseResult.ok(tbSysUser);
			}else {	//触发了熔断
				errorInfo.put("message", "触发了熔断");
				return BaseResult.notOk(errorInfo);
			}
		}
	}
	
	/**
	 * 注销
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public BaseResult logout(HttpServletRequest request, HttpServletResponse response) {
		//删除cookie
		CookieUtils.deleteCookie(request, response, "token");
		return BaseResult.ok();
	}

}
