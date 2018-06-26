package com.hhly.chat.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 响应结构体
 * 
 * @作者 hebs1111
 * @时间 2018年5月14日
 * @公司 法义律师端
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult {

	private int code;
	private String msg;
	private Object data;

}
