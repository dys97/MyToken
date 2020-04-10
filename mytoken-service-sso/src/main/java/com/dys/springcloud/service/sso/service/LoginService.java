package com.dys.springcloud.service.sso.service;

import com.dys.springcloud.common.domain.TbSysUser;

public interface LoginService {

	/**
	 * 登录业务
	 * @param loginCode
	 * @param plantPassword
	 * @return
	 */
	public TbSysUser login(String loginCode, String plantPassword);
		
}
