package com.pcfix_client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import android.widget.SimpleAdapter;

import com.example.pcfix_client.R;

public class DataManager {
	private static DataManager mInst = null;
	public static DataManager getInstance(){
		if(mInst == null){
			return mInst = new DataManager();
		}
		else
		return mInst;
	}
	
	private List<OrderInfo> activeOrderList;
	private List<Map<String, Object>> activeOrderMap;
	
	private List<OrderInfo> clientOrderList;
	private List<Map<String, Object>> clientOrderMap;
	
	private List<OrderInfo> serverOrderList;
	private List<Map<String, Object>> serverOrderMap;
	
	private List<HistoryOrder> hisOrderList;
	private List<Map<String, Object>> hisOrderMap;
	

	public List<OrderInfo> getActiveOrderList() {
		return activeOrderList;
	}


	public List<Map<String, Object>> getActiveOrderMap() {
		return activeOrderMap;
	}


	public List<OrderInfo> getClientOrderList() {
		return clientOrderList;
	}


	public List<Map<String, Object>> getClientOrderMap() {
		return clientOrderMap;
	}


	public List<OrderInfo> getServerOrderList() {
		return serverOrderList;
	}


	public List<Map<String, Object>> getServerOrderMap() {
		return serverOrderMap;
	}


	public List<HistoryOrder> getHisOrderList() {
		return hisOrderList;
	}


	public List<Map<String, Object>> getHisOrderMap() {
		return hisOrderMap;
	}


	public int refreshActive() {

		Map<String, String> map = new HashMap<String, String>();

		try {
			JSONObject json = new JSONObject(HttpUtil.postRequest(
					API.LIST_ACTIVE_ORDERS, map));
			Log.d("LIST_ACTIVE_ORDERS-json", json.toString());
			
			if (json.getInt("result") == 0) {
				JSONArray ja = json.getJSONArray("orderInfos");

				activeOrderMap = OrderInfo.toAdapterData(ja);
				activeOrderList = OrderInfo.toOrderInfo(ja);

				return 0;
				
			} else {
				
				return json.getInt("error");

			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}
	
	
	public int refreshClient() {

		Map<String, String> map = new HashMap<String, String>();

		map.put("clientId", "" + User.getInstance().getId());

		try {
			JSONObject json = new JSONObject(HttpUtil.postRequest(
					API.LIST_CLIENT_ORDERS, map));
			Log.d("LIST_CLIENT_ORDERS-json", json.toString());
			if (json.getInt("result") == 0) {
				JSONArray ja = json.getJSONArray("orderInfos");

				clientOrderMap = OrderInfo.toAdapterData(ja);
				clientOrderList = OrderInfo.toOrderInfo(ja);

				return 0;
			} else {
				return json.getInt("error");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}
	
	public int refreshServer() {

		Map<String, String> map = new HashMap<String, String>();

		map.put("serverId", "" + User.getInstance().getId());

		try {
			JSONObject json = new JSONObject(HttpUtil.postRequest(
					API.LIST_SERVER_ORDERS, map));
			Log.d("LIST_SERVER_ORDERS-json", json.toString());
			if (json.getInt("result") == 0) {
				JSONArray ja = json.getJSONArray("orderInfos");

				serverOrderMap = OrderInfo.toAdapterData(ja);
				serverOrderList = OrderInfo.toOrderInfo(ja);
				return 0;
			} else {
				return json.getInt("error");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}
	
	public int refreshHistory() {

		Map<String, String> map = new HashMap<String, String>();

		map.put("userId", "" + User.getInstance().getId());

		try {
			JSONObject json = new JSONObject(HttpUtil.postRequest(
					API.LIST_HIS_ORDERS, map));
			Log.d("LIST_HIS_ORDERS-json", json.toString());
			if (json.getInt("result") == 0) {
				JSONArray ja = json.getJSONArray("hisOrders");

				hisOrderMap = HistoryOrder.toAdapterData(ja);
				hisOrderList = HistoryOrder.toOrderInfo(ja);
				return 0;
			} else {
				return json.getInt("error");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}
	
	public void refresh()
	{
		refreshActive();
		refreshClient();
		refreshServer();
		refreshHistory();
	}
	

}
