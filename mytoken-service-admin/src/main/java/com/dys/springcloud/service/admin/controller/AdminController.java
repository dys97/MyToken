package com.dys.springcloud.service.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dys.springcloud.common.domain.TbSysUser;
import com.dys.springcloud.common.dto.BaseResult;
import com.dys.springcloud.common.dto.BaseResult.Cursor;
import com.dys.springcloud.service.admin.service.AdminService;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping(value = "/v1/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	/**
	 * 根据id获取信息
	 * @param userCode
	 * @return
	 */
	@RequestMapping(value = "/{userCode}", method = RequestMethod.GET)
	public BaseResult getUserHandler(@PathVariable(required = true) String userCode) {
		TbSysUser tbSysUser = new TbSysUser();
		tbSysUser.setUserCode(userCode);
		TbSysUser selectByOne = adminService.selectByOne(tbSysUser);
		if(selectByOne == null) {
			return BaseResult.ok("不存在该ID的用户！");
		}
		return BaseResult.ok(selectByOne);
	}
	
	/**
	 * 保存管理员
	 * @param tbSysUser
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public BaseResult saveUserHandler(@RequestBody(required = true)TbSysUser tbSysUser) {
		Map<String, String> info = new HashMap<String, String>();
		if(tbSysUser == null) {
			info.put("message", "参数有误！");
			return BaseResult.notOk(info);
		}
		int result = adminService.save(tbSysUser);
		if(result > 0) {
			return BaseResult.ok("保存管理员成功！");
		}
		info.put("message", "保存管理员失败！");
		return BaseResult.notOk(info);
	}
	
	/**
	 * 分页查询
	 */
	@RequestMapping(value = "/page/{pageNum}/{pageSize}", method = RequestMethod.GET)
	public BaseResult pageUserHandler(@PathVariable(required = true) int pageNum,
									@PathVariable(required = true) int pageSize) {
		PageInfo<TbSysUser> pageInfo = adminService.page(pageNum, pageSize, new TbSysUser());
		List<TbSysUser> list = pageInfo.getList();
		BaseResult.Cursor cursor = new Cursor();
		cursor.setTotal(new Long(pageInfo.getTotal()).intValue());
		cursor.setLimit(pageInfo.getSize());
		cursor.setOffset(pageInfo.getPageNum());
		return BaseResult.ok(list, cursor);
	}
	
}
