package com.example.demo.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;
import lombok.ToString;

/**
 * @Author: jack
 * @Description: 用于返回前端需要的json数据
 * @Date Create in 16:14 2019-04-12
 **/
@Data
@ToString
public class Result {
	private Boolean success;
	private String message;
	private Object result;
	private String status;

	public Result() {
	}

	public Result(Boolean success, String msg, Object result, String status) {
		this.success = success;
		this.message = msg;
		this.result = result;
		this.status = status;
	}

	public static Result ok() {
		return new Result(true, "操作成功!", null, "200");
	}

	public static Result ok(Object data) {
		return new Result(true, "操作成功!", data, "200");
	}

	public static Result ok(String msg, String status) {
		return new Result(true, msg, null, status);
	}

	public static Result err(String msg, Object data) {
		return new Result(false, msg, data, "200");
	}

	public static Result err(String msg) {
		return new Result(false, msg, null, "200");
	}

	public static Result page(IPage page) {
		return new Result(true, "查询成功", PageResult.ok(page), "200");
	}
}
