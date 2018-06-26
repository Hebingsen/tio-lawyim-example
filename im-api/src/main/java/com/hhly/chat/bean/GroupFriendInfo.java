package com.hhly.chat.bean;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupFriendInfo {
	
	private String groupname;
	private Integer id;
	private Integer online;
	private List<Mine> list;
}
