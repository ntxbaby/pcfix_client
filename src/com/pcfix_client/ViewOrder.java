package com.pcfix_client;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;


public class ViewOrder {
	private int orderId;
	private String desc;
	private String name;
	private String addr;
	//private Date createTime;
	private String createTime; 
	private int apply;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	public int getApply() {
		return apply;
	}
	public void setApply(int apply) {
		this.apply = apply;
	}
	
	public void fromJSONObject(JSONObject order) throws JSONException {
		name = order.getString("name");
		addr = order.getString("addr");
		createTime = order.getString("createTime");
		desc = order.getString("desc");
		orderId = order.getInt("orderId");
		apply = order.getInt("apply");
}
public Map<String, Object> toOrderMap(){
	Map<String, Object> orderMap = new HashMap<String, Object>();
	orderMap.put("addr", "地址:"+addr);
	orderMap.put("name", "求助者:"+name);
	orderMap.put("createTime", createTime);
	orderMap.put("desc", "问题描述:"+desc);
	orderMap.put("apply", "申请人数:"+apply);
	orderMap.put("orderId", orderId);
	return orderMap;
}
	
	
	
}
