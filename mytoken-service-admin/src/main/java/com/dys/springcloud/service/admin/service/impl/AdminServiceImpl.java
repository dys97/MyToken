package com.dys.springcloud.service.admin.service.impl;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.dys.springcloud.common.domain.TbSysUser;
import com.dys.springcloud.common.service.service.impl.BaseServiceImpl;
import com.dys.springcloud.service.admin.mapper.TbSysUserExtendMapper;
import com.dys.springcloud.service.admin.service.AdminService;

@Service
@Transactional(readOnly = true)
public class AdminServiceImpl extends BaseServiceImpl<TbSysUser, TbSysUserExtendMapper> implements AdminService{
	
	@Override
	@Transactional(readOnly = false)
	public int save(TbSysUser tbSysUser) {
		//密码加密
		String password = DigestUtils.md5DigestAsHex(tbSysUser.getPassword().getBytes());
		tbSysUser.setPassword(password);
		//新增
		if(StringUtils.isBlank(tbSysUser.getUserCode())) {
			tbSysUser.setUserCode(UUID.randomUUID().toString());
			return insert(tbSysUser, tbSysUser.getLoginCode());
		}else {	//修改
			return update(tbSysUser, tbSysUser.getLoginCode());
		}
	}

}
