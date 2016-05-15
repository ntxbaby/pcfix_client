package com.pcfix_client;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class Price {
	private int id;
	private int orderId;
	private int serverId;
	private int price;
	private int selected;
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
	
	public void fromJSONObject(JSONObject price) throws JSONException {
		orderId = price.getInt("orderId");
		serverId = price.getInt("serverId");
		this.price = price.getInt("price");
		selected = price.getInt("selected");
		id		= price.getInt("id");
	
	}
public Map<String, Object> toPriceMap(){
	Map<String, Object> orderMap = new HashMap<String, Object>();
	orderMap.put("orderId", orderId);
	orderMap.put("serverId", serverId);
	orderMap.put("price", ""+price);
	orderMap.put("selected", selected);
	orderMap.put("id", id);
	return orderMap;
}
}
