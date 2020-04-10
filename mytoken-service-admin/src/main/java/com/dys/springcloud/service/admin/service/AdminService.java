package com.dys.springcloud.service.admin.service;

import com.dys.springcloud.common.domain.TbSysUser;
import com.dys.springcloud.common.service.service.BaseService;

public interface AdminService extends BaseService<TbSysUser>{
	
	/**
	 * 保存/修改管理员
	 * @param tbSysUser
	 * @return
	 */
	int save(TbSysUser tbSysUser);
	
}
