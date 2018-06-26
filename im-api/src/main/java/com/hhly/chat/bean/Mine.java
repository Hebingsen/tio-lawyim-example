package com.hhly.chat.bean;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 当前用户信息
 * 
 * @作者 hebs1111
 * @时间 2018年5月11日
 * @公司 法义律师端
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mine {

	//用户名称
	private String username;
	//用户ID
	private String id;
	//头像链接
	private String avatar;
	//个性签名
	private String sign;
	//在线状态
	private String status;
	
}
