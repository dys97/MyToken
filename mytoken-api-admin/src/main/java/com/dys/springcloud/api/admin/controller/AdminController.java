package com.dys.springcloud.api.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dys.springcloud.api.admin.service.AdminService;
import com.dys.springcloud.common.domain.TbSysUser;
import com.dys.springcloud.common.dto.BaseResult;

@RestController
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	/**
	 * 保存管理员
	 * @param tbSysUser
	 * @return
	 */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public BaseResult save(TbSysUser tbSysUser){
        // 初始化数据
        tbSysUser.setUserType("1");
        tbSysUser.setMgrType("1");
        tbSysUser.setStatus("0");
        tbSysUser.setCorpCode("0");
        tbSysUser.setCorpName("myToken");
        return adminService.saveUserHandler(tbSysUser);
    }
	
}
