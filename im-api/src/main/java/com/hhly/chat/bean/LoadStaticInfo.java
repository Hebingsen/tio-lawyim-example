package com.hhly.chat.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 加载静态信息
 * 
 * @作者 hebs1111
 * @时间 2018年5月11日
 * @公司 法义律师端
 */
public class LoadStaticInfo {

	public static Map<String, Mine> userMap = new HashMap<String, Mine>();
	static {
		Mine hebingsen = new Mine("何炳森", "1111", "http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg",
				"我是个性签名", "online");
		Mine xianxin = new Mine("贤心", "2222", "http://tp1.sinaimg.cn/1571889140/180/40030060651/1", "我是个性签名", "online");
		Mine ziqing = new Mine("Z_子晴", "3333", "http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg",
				"微电商达人", "online");
		Mine maxiaoyun = new Mine("马小云", "4444", "http://tp4.sinaimg.cn/2145291155/180/5601307179/1", "让天下没有难写的代码",
				"online");
		Mine xuxiaozheng = new Mine("徐小峥", "5555", "http://tp2.sinaimg.cn/1783286485/180/5677568891/1", "代码在囧途，也要写到底",
				"online");
		userMap.put(hebingsen.getId(), hebingsen);
		userMap.put(xianxin.getId(), xianxin);
		userMap.put(ziqing.getId(), ziqing);
		userMap.put(maxiaoyun.getId(), maxiaoyun);
		userMap.put(xuxiaozheng.getId(), xuxiaozheng);
	}

	/**
	 * 根据用户ID查询用户信息
	 * 
	 * @param id
	 */
	public static Mine findUserInfoById(String id) {
		return userMap.get(id);
	}

	/**
	 * 查询除了当前用户之外的所有其他用户
	 */
	public static List<Mine> findOtherUserListExcludeId(String id) {
		ArrayList<Mine> list = new ArrayList<Mine>();
		Set<String> keySet = userMap.keySet();
		Iterator<String> it = keySet.iterator();
		while (it.hasNext()) {
			String key = it.next();
			if (!id.equals(key)) {
				list.add(userMap.get(key));
			}
		}
		return list;
	}

}
