package com.dys.springcloud.api.admin.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dys.springcloud.api.admin.service.fallback.AdminServiceFallback;
import com.dys.springcloud.common.domain.TbSysUser;
import com.dys.springcloud.common.dto.BaseResult;

@FeignClient(value = "MYTOKEN-SERVICE-ADMIN", path = "/v1/admin", fallback = AdminServiceFallback.class)
public interface AdminService {
	
	
	@RequestMapping(value = "/{userCode}", method = RequestMethod.GET)
	public BaseResult getUserHandler(@PathVariable(value = "userCode", required = true) String userCode);
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public BaseResult saveUserHandler(@RequestBody(required = true)TbSysUser tbSysUser);
	
	@RequestMapping(value = "/page/{pageNum}/{pageSize}", method = RequestMethod.GET)
	public BaseResult pageUserHandler(@PathVariable(value = "pageNum", required = true) int pageNum,
									  @PathVariable(value = "pageSize", required = true) int pageSize);
}

