package com.hhly.chat.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hhly.chat.bean.Group;
import com.hhly.chat.bean.GroupFriendInfo;
import com.hhly.chat.bean.LoadStaticInfo;
import com.hhly.chat.bean.Mine;
import com.hhly.chat.common.ResponseResult;

/**
 * IM 用户信息controller层
 * 
 * @作者 hebs1111
 * @时间 2018年5月11日
 * @公司 法义律师端
 */
@RestController
@RequestMapping("/api/user")
public class ChatUserInfoController {

	@GetMapping("/chat-init/{id}")
	public Object chatInit(@PathVariable String id) {

		ResponseResult result = new ResponseResult();
		result.setCode(0);

		Map<String, Object> data = new HashMap<String, Object>();

		// 根据id查询用户个人信息:mine
		Mine mine = LoadStaticInfo.findUserInfoById(id);
		data.put("mine", mine);

		// 查询好友列表
		List<GroupFriendInfo> friendList = new ArrayList<GroupFriendInfo>();
		// 好友分组1
		GroupFriendInfo groupFriendInfo = new GroupFriendInfo();
		groupFriendInfo.setGroupname("我的好友");
		groupFriendInfo.setId(1001);
		groupFriendInfo.setOnline(4);
		groupFriendInfo.setList(LoadStaticInfo.findOtherUserListExcludeId(id));
		friendList.add(groupFriendInfo);

		data.put("friend", friendList);

		// 群组信息:group
		ArrayList<Group> groupList = new ArrayList<Group>();
		Group group1 = new Group("前端群", "101", "http://tp2.sinaimg.cn/2211874245/180/40050524279/0");
		Group group2 = new Group("Fly社区官方群", "101", "http://tp2.sinaimg.cn/2211874245/180/40050524279/1");
		groupList.add(group1);
		groupList.add(group2);

		data.put("group", groupList);

		result.setData(data);
		return result;
	}

}
