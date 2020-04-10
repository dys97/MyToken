package com.dys.springcloud.common.hystrix;

import java.util.HashMap;
import java.util.Map;

import com.dys.springcloud.common.constants.HttpStatusConstants;
import com.dys.springcloud.common.dto.BaseResult;

/**
 * 通用的熔断方法
 * @author 90655
 *
 */
public class Fallback {

	public static BaseResult BadGetWay() {
		Map<String, String> errorInfo = new HashMap<String, String>();
		errorInfo.put(HttpStatusConstants.STATUS, HttpStatusConstants.CONTENT);
		return BaseResult.notOk(errorInfo);
	}
}
