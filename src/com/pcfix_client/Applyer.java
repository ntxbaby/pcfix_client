package com.pcfix_client;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Applyer {
	private int id;
	private int orderId;
	private int serverId;
	private int price;
	private int selected;
	private String serverName;
	public Applyer(int id, int orderId, int serverId, int price, int selected,
			String serverName) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.serverId = serverId;
		this.price = price;
		this.selected = selected;
		this.serverName = serverName;
	}
	public Applyer() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getServerId() {
		return serverId;
	}
	public void setServerId(int serverId) {
		this.serverId = serverId;
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
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	
	public static List<Map<String, Object>> toAdapterData(JSONArray ja){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < ja.length(); i++) {

			JSONObject jo;
			
			try {

				jo = ja.getJSONObject(i);
				
				
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("id", jo.getInt("id"));
				m.put("orderId", jo.getInt("orderId"));
				m.put("serverId", "维修者ID:" + jo.getInt("serverId"));
				m.put("price", "报价:" + jo.getInt("price"));
				m.put("selected", jo.getInt("selected"));
				m.put("serverName", "维修者名字:" + jo.getString("serverName"));
				
				list.add(m);

			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
		return list;
	}
	
	public static List<Applyer> toApplyerInfo(JSONArray ja){
		List<Applyer> list = new ArrayList<Applyer>();
		for (int i = 0; i < ja.length(); i++) {
			JSONObject jo;
			try {
				jo = ja.getJSONObject(i);
				Applyer a = new Applyer();
				a.setOrderId(jo.getInt("orderId"));
				a.setId(jo.getInt("id"));
				a.setOrderId(jo.getInt("orderId"));
				a.setServerId(jo.getInt("serverId"));
				a.setServerName(jo.getString("serverName"));
				a.setPrice(jo.getInt("price"));
				a.setSelected(jo.getInt("selected"));
				list.add(a);

			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
		return list;
	}
	
}
