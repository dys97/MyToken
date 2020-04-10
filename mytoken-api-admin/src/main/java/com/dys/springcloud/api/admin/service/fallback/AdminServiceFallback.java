package com.dys.springcloud.api.admin.service.fallback;

import org.springframework.stereotype.Component;

import com.dys.springcloud.api.admin.service.AdminService;
import com.dys.springcloud.common.domain.TbSysUser;
import com.dys.springcloud.common.dto.BaseResult;
import com.dys.springcloud.common.hystrix.Fallback;

@Component
public class AdminServiceFallback implements AdminService{

	@Override
	public BaseResult getUserHandler(String userCode) {
		return Fallback.BadGetWay();
	}

	@Override
	public BaseResult saveUserHandler(TbSysUser tbSysUser) {
		return Fallback.BadGetWay();
	}

	@Override
	public BaseResult pageUserHandler(int pageNum, int pageSize) {
		return Fallback.BadGetWay();
	}


}
