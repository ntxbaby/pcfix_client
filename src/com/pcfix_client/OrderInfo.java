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


public class OrderInfo {
	
	private int orderId;
	private String desc;
	private String phone;
	private String addr;
	private String createTime;
	private String serveTime;
	private int mathod;
	private int problem;
	private int clientId;
	private String clientName;
	private int priceId;
	private int status;
	//申请者(服务者)信息
	private int serverId;
	private String serverName;
	private int price;
	private int selected;
	//申请者个数
	private BigInteger applyerNum;


	
	
	public OrderInfo(int orderId, String desc, String phone, String addr,
			String createTime, String serveTime, int mathod, int problem,
			int clientId, String clientName, int priceId, int status,
			int serverId, String serverName, int price, int selected,
			BigInteger applyerNum) {
		super();
		this.orderId = orderId;
		this.desc = desc;
		this.phone = phone;
		this.addr = addr;
		this.createTime = createTime;
		this.serveTime = serveTime;
		this.mathod = mathod;
		this.problem = problem;
		this.clientId = clientId;
		this.clientName = clientName;
		this.priceId = priceId;
		this.status = status;
		this.serverId = serverId;
		this.serverName = serverName;
		this.price = price;
		this.selected = selected;
		this.applyerNum = applyerNum;
	}
	
	
	
	public OrderInfo() {
		// TODO Auto-generated constructor stub
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
	public int getSelected() {
		return selected;
	}
	public void setSelected(int selected) {
		this.selected = selected;
	}
	public BigInteger getApplyerNum() {
		return applyerNum;
	}
	public void setApplyerNum(BigInteger applyerNum) {
		this.applyerNum = applyerNum;
	}

	public static List<Map<String, Object>> toAdapterData(JSONArray ja){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < ja.length(); i++) {

			JSONObject jo;
			
			try {

				jo = ja.getJSONObject(i);
				
				Map<String, Object> m = new HashMap<String, Object>();
				
				m.put("orderId", jo.getInt("orderId"));
				m.put("desc", "问题描述:"+jo.getString("desc"));
				m.put("phone", jo.getString("phone"));
				m.put("addr", "地址:"+jo.getString("addr"));
				m.put("createTime", jo.getString("createTime"));
				m.put("serveTime", jo.getString("serveTime"));
				m.put("mathod", jo.getInt("mathod"));
				m.put("problem", jo.getInt("problem"));
				m.put("clientId", jo.getInt("clientId"));
				m.put("clientName", "求助者:"+jo.getString("clientName"));
				m.put("priceId", jo.getInt("priceId"));
				m.put("status", "状态:"+OrderInfo.STATUS_STRING[jo.getInt("status")]);
				m.put("serverId", jo.getInt("serverId"));
				m.put("serverName", jo.getString("serverName"));
				m.put("price", jo.getInt("price"));
				m.put("selected", jo.getInt("selected"));
				m.put("applyerNum", "申请人数:"+jo.getInt("applyerNum"));
				
				list.add(m);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return list;
	}
	
	public static List<OrderInfo> toOrderInfo(JSONArray ja){
		List<OrderInfo> list = new ArrayList<OrderInfo>();
		for (int i = 0; i < ja.length(); i++) {

			JSONObject jo;
			
			try {

				jo = ja.getJSONObject(i);
				OrderInfo o = new OrderInfo();
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
				o.setPriceId(jo.getInt("priceId"));
				o.setStatus(jo.getInt("status"));
				o.setServerId(jo.getInt("serverId"));
				o.setServerName(jo.getString("serverName"));
				o.setPrice(jo.getInt("price"));
				o.setSelected(jo.getInt("selected"));
				o.setApplyerNum(BigInteger.valueOf(jo.getInt("applyerNum")) );
				
				list.add(o);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return list;
	}

	public static final int STATUS_APPLY = 0;
	public static final int STATUS_DEAL = 1;
	public static final int STATUS_VARIFY = 2;
	public static final int STATUS_FINISH = 3;
	public static final int STATUS_TIMEOUT = 4;
	public static final String [] STATUS_STRING = new String[]{"竞价中...", "处理中...", "验收", "完成", "过期"};
	public static final String [] MATHOD_STRING = new String[]{"上门服务", "远程服务"};
	public static final String [] PROBLEMS = new String[]{"cpu","内存","显卡","硬盘","显示器","键盘","鼠标"};
	
	
}
