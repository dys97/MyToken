package com.dys.springcloud.common.dto;

import java.io.Serializable;
import java.util.Map;

import lombok.Data;

/**
 * 数据传输对象
 */
@Data
public class BaseResult implements Serializable{

	private static final long serialVersionUID = -8255791453348379347L;

    public static final String RESULT_OK = "ok";
    
    public static final String RESULT_NOT_OK = "not_ok";
    
    public static final String SUCCESS = "操作成功";
    
    public static final String FALSE = "操作失败";
	
	private String result;
	
	private Object data;
	
	private String success;
	
	private Cursor cursor;
	
	private Map<String, String> info;
	
	public static BaseResult ok() {
		return createResult(RESULT_OK, null, SUCCESS, null, null);
	}
	
	public static BaseResult ok(Object data) {
		return createResult(RESULT_OK, data, SUCCESS, null, null);
	}
	
	public static BaseResult ok(String success) {
		return createResult(RESULT_OK, null, success, null, null);
	}
	
	public static BaseResult ok(String success, Map<String, String> info) {
		return createResult(RESULT_OK, null, success, null, info);
	}
	
	public static BaseResult ok(Object data, Cursor cursor) {
        return createResult(RESULT_OK, data, SUCCESS, cursor, null);
    }

    public static BaseResult notOk(Map<String, String> info) {
        return createResult(RESULT_NOT_OK, null, FALSE, null, info);
    }
	
	private static BaseResult createResult(String result, Object data, String success, Cursor cursor, Map<String, String> info ) {
        BaseResult baseResult = new BaseResult();
        baseResult.setResult(result);
        baseResult.setData(data);
        baseResult.setSuccess(success);
        baseResult.setCursor(cursor);
        baseResult.setInfo(info);
        return baseResult;
    }
	
	@Data
	public static class Cursor {
		//全部条数
		private int total;
		//当前所在位置
		private int offset;
		//每页条数
		private int limit;
	}
}
