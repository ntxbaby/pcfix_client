package com.pcfix_client;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class HistoryOrder {
	
	private int hisOrderId;
	//order信息
	private int orderId;
	private String desc;
	private String phone;
	private String addr;
//	private Date createTime;
//	private Date serveTime;
	private String createTime;
	private String serveTime;
	private int mathod;
	private int problem;
	private int clientId;
	private String clientName;
	
	//维修信息
	private int serverId;
	private String serverName;
	private int price;
	//完成类型
	private int finishType;
	//private Date finishTime;
	private String finishTime;
	//评论
	private String comment;
	
	public static final int STATUS_APPLY = 0;
	public static final int STATUS_DEAL = 1;
	public static final int STATUS_VARIFY = 2;
	public static final int STATUS_FINISH = 3;
	public static final int STATUS_TIMEOUT = 4;
	//完成类型
	public static final int FINISH_TYPE_FINISH = 0;
	public static final int FINISH_TYPE_TIMEOUT = 1;
	public static String [] FINISH_TYPE_STRING = new String [] {"完成", "过期"};

	public int getHisOrderId() {
		return hisOrderId;
	}
	public void setHisOrderId(int hisOrderId) {
		this.hisOrderId = hisOrderId;
	}
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
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public int getServerId() {
		return serverId;
	}
	public void setServerId(int serverId) {
		this.serverId = serverId;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getFinishType() {
		return finishType;
	}
	public void setFinishType(int finishType) {
		this.finishType = finishType;
	}
	public String getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public static List<Map<String, Object>> toAdapterData(JSONArray ja){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < ja.length(); i++) {

			JSONObject jo;
			
			try {

				jo = ja.getJSONObject(i);
				
				Map<String, Object> m = new HashMap<String, Object>();
				
				m.put("hisOrderId", jo.getInt("hisOrderId"));
				m.put("orderId", jo.getInt("orderId"));
				m.put("desc", "问题描述:"+jo.getString("desc"));
				m.put("phone", jo.getString("phone"));
				m.put("addr", jo.getString("addr"));
				m.put("createTime", jo.getString("createTime"));
				m.put("serveTime", jo.getString("serveTime"));
				m.put("mathod", jo.getInt("mathod"));
				m.put("problem", jo.getInt("problem"));
				m.put("clientId", jo.getInt("clientId"));
				m.put("clientName", "求助者:"+jo.getString("clientName"));
				m.put("serverId", jo.getInt("serverId"));
				m.put("serverName", jo.getString("serverName"));
				m.put("price", jo.getInt("price"));
				m.put("finishType", "结束状态:"+HistoryOrder.FINISH_TYPE_STRING[jo.getInt("finishType")]);
				m.put("finishTime", jo.getString("finishTime"));
				m.put("comment", jo.getString("comment")=="null" ? "评论:暂无评论" : "评论:"+jo.getString("comment")); 
				
				list.add(m);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return list;
	}
	
	public static List<HistoryOrder> toOrderInfo(JSONArray ja){
		List<HistoryOrder> list = new ArrayList<HistoryOrder>();
		for (int i = 0; i < ja.length(); i++) {

			JSONObject jo;
			
			try {

				jo = ja.getJSONObject(i);
				HistoryOrder o = new HistoryOrder();
				o.setHisOrderId(jo.getInt("hisOrderId"));
				o.setOrderId(jo.getInt("orderId"));
				o.setDesc(jo.getString("desc"));
				o.setPhone(jo.getString("phone"));
				o.setAddr(jo.getString("addr"));
				o.setCreateTime(jo.getString("createTime"));
				o.setServeTime(jo.getString("serveTime"));
				o.setMathod(jo.getInt("mathod"));
				o.setProblem(jo.getInt("problem"));
				o.setClientId(jo.getInt("clientId"));
				o.setClientName(jo.getString("clientName"));
				o.setServerId(jo.getInt("serverId"));
				o.setServerName(jo.getString("serverName"));
				o.setPrice(jo.getInt("price"));
				o.setFinishType(jo.getInt("finishType"));
				o.setFinishTime(jo.getString("finishTime")) ;
				o.setComment(jo.getString("comment"));
				list.add(o);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return list;
	}
	
	
	
	
}
