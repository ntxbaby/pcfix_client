package com.pcfix_client;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
	private static User inst = null;
	private int id;
	private String name;
	private String pwd;
	private int type;
	private String phone;
	private String addr;
	private int star;
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public static User getInstance(){
		return inst;
	}
	
	public static User fromJSONObject(JSONObject jsonUser) throws JSONException {
		inst = new User();
		inst.id = jsonUser.getInt("id");
		inst.name = jsonUser.getString("name");
		inst.pwd = jsonUser.getString("pwd");
		inst.type = jsonUser.getInt("type");
		inst.phone = jsonUser.getString("phone");
		inst.addr = jsonUser.getString("addr");
		inst.star = jsonUser.getInt("star");
		return inst;
	}
	

	public Map<String, Object> toUserMap() {
		Map<String, Object> userMap = new HashMap<String, Object>();
		userMap.put("id", id);
		userMap.put("name", name);
		userMap.put("pwd", pwd);
		userMap.put("type", type);
		userMap.put("phone", phone);
		userMap.put("addr", addr);
		userMap.put("star", star);
		return userMap;
	}

}
