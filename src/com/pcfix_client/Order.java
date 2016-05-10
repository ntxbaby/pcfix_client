package com.pcfix_client;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;


public class Order {
	private int orderId;
	private String desc;
	private String phone;
	private String addr;
	private String createTime;
	private String serveTime;
	private int mathod;
	private int problem;
	private int clientId;
	private int serverId;
	private int priceId;
	private int status;
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
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getServeTime() {
		return serveTime;
	}
	public void setServeTime(String serveTime) {
		this.serveTime = serveTime;
	}
	public int getMathod() {
		return mathod;
	}
	public void setMathod(int mathod) {
		this.mathod = mathod;
	}
	public int getProblem() {
		return problem;
	}
	public void setProblem(int problem) {
		this.problem = problem;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public int getServerId() {
		return serverId;
	}
	public void setServerId(int serverId) {
		this.serverId = serverId;
	}
	public int getPriceId() {
		return priceId;
	}
	public void setPriceId(int priceId) {
		this.priceId = priceId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public void fromJSONObject(JSONObject order) throws JSONException {
			addr = order.getString("addr");
			clientId = order.getInt("clientId");
			createTime = order.getString("createTime");
			desc = order.getString("desc");
			mathod = order.getInt("mathod");
			orderId = order.getInt("orderId");
			phone = order.getString("phone");
			priceId = order.getInt("priceId");
			problem = order.getInt("problem");
			serveTime = order.getString("serveTime");
			serverId = order.getInt("serverId");
			status = order.getInt("status");
		
	}
	public Map<String, Object> toOrderMap(){
		Map<String, Object> orderMap = new HashMap<String, Object>();
		orderMap.put("addr", addr);
		orderMap.put("clientId", clientId);
		orderMap.put("createTime", createTime);
		orderMap.put("desc", desc);
		orderMap.put("mathod", mathod);
		orderMap.put("orderId", orderId);
		orderMap.put("phone", phone);
		orderMap.put("priceId", priceId);
		orderMap.put("problem", API.PROBLEMS[problem]);
		orderMap.put("serveTime", serveTime);
		orderMap.put("serverId", serverId);
		orderMap.put("status", API.STATES[status]);
		return orderMap;
	}
	public SerializableMap toOrderSerializableMap(){
		SerializableMap sm = new SerializableMap();
		Map<String,Object> orderMap = sm.getMap();
		orderMap.put("addr", addr);
		orderMap.put("clientId", clientId);
		orderMap.put("createTime", createTime);
		orderMap.put("desc", desc);
		orderMap.put("mathod", mathod);
		orderMap.put("orderId", orderId);
		orderMap.put("phone", phone);
		orderMap.put("priceId", priceId);
		orderMap.put("problem", API.PROBLEMS[problem]);
		orderMap.put("serveTime", serveTime);
		orderMap.put("serverId", serverId);
		orderMap.put("status", API.STATES[status]);
		return sm;
	}
	
     
	public static final int PROBLEM_CPU = 0;
	public static final int PROBLEM_MEM = 1;
	public static final int PROBLEM_GPU = 2;
	public static final int PROBLEM_DISK = 3;
	public static final int PROBLEM_MONITOR = 4;
	public static final int PROBLEM_KEYBOARD = 5;
	public static final int PROBLEM_MOUSE = 6;
	//public static final String[] PROBLEM = new String[]{"cpu","ƒ⁄¥Ê","œ‘ø®","”≤≈Ã","œ‘ æ∆˜","º¸≈Ã"," Û±Í"};
	
	
}
