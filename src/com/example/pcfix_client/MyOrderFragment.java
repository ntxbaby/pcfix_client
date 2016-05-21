package com.example.pcfix_client;

import java.io.ObjectOutputStream.PutField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.pcfix_client.API;
import com.pcfix_client.DataManager;
import com.pcfix_client.HttpUtil;
import com.pcfix_client.Order;
import com.pcfix_client.OrderInfo;
import com.pcfix_client.User;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AbsListView.LayoutParams;
import android.widget.AdapterView.OnItemClickListener;

public class MyOrderFragment extends Fragment {

	private String msg;
	private List<Map<String, Object>> data;
	ListView listView;
	
	public void refresh()
	{
		if (User.getInstance().getType() == 0) {
			listClientOrders1();
		} else {
			listServerOrders1();
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		listView = new ListView(getActivity());
		listView.setLayoutParams(new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));

		refresh();
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Intent intent = new Intent(getActivity(),
						OrderDetailActivity.class);

				Bundle mBundle = new Bundle();
				mBundle.putInt("pos", position);
			/*	Map<String, Object> map = data.get(position);

				mBundle.putString("addr", (String) map.get("addr"));
				mBundle.putInt("clientId", (Integer) map.get("clientId"));
				mBundle.putString("createTime", (String) map.get("createTime"));
				mBundle.putString("desc", (String) map.get("desc"));
				//mBundle.putInt("mathod", (Integer) map.get("mathod"));
				mBundle.putInt("orderId", (Integer) map.get("orderId"));
				mBundle.putString("phone", (String) map.get("phone"));
				mBundle.putInt("priceId", (Integer) map.get("priceId"));
				mBundle.putInt("problem", (Integer) map.get("problem"));
				mBundle.putString("serveTime", (String) map.get("serveTime"));
				mBundle.putInt("serverId", (Integer) map.get("serverId"));
				mBundle.putInt("status", (Integer) map.get("status"));
*/
				intent.putExtras(mBundle);

				startActivity(intent);

			}

		});

		return listView;
	}

	private boolean listClientOrders1() {

		int ret = DataManager.getInstance().refreshClient();
		
			if (ret == 0) {

				data = DataManager.getInstance().getClientOrderMap();;
				SimpleAdapter sa = new SimpleAdapter(getActivity(), data,
						R.layout.my_order_list_item, new String[] { "status",
								"createTime", "desc" }, new int[] {
								R.id.list_item_state, R.id.list_item_time,
								R.id.list_item_desc });
				listView.setAdapter(sa);

				return true;
			} else {
				switch (ret) {
				case 300:
					msg = "订单不存在";
					break;
				case 301:
					msg = "登陆用户密码错误";
					break;
				default:
					break;
				}

				return false;
			}
		

	}
	
	private boolean listServerOrders1() {

		int ret = DataManager.getInstance().refreshServer();
		
		if (ret == 0) {

			data = DataManager.getInstance().getServerOrderMap();;
			SimpleAdapter sa = new SimpleAdapter(getActivity(), data,
					R.layout.my_order_list_item, new String[] { "status",
							"createTime", "desc" }, new int[] {
							R.id.list_item_state, R.id.list_item_time,
							R.id.list_item_desc });
			listView.setAdapter(sa);

			return true;
		} else {
			switch (ret) {
			case 300:
				msg = "订单不存在";
				break;
			case 301:
				msg = "登陆用户密码错误";
				break;
			default:
				break;
			}

			return false;
		}
	
	}
	
	private boolean listClientOrders() {

		Map<String, String> map = new HashMap<String, String>();

		map.put("clientId", "" + User.getInstance().getId());

		try {
			JSONObject json = new JSONObject(HttpUtil.postRequest(
					API.LIST_CLIENT_ORDERS, map));
			Log.d("LIST_CLIENT_ORDERS-json", json.toString());
			if (json.getInt("result") == 0) {
				JSONArray ja = json.getJSONArray("orderInfos");

				data = OrderInfo.toAdapterData(ja);
				SimpleAdapter sa = new SimpleAdapter(getActivity(), data,
						R.layout.my_order_list_item, new String[] { "status",
								"createTime", "desc" }, new int[] {
								R.id.list_item_state, R.id.list_item_time,
								R.id.list_item_desc });
				listView.setAdapter(sa);

				return true;
			} else {
				switch (json.getInt("error")) {
				case 300:
					msg = "订单不存在";
					break;
				case 301:
					msg = "登陆用户密码错误";
					break;
				default:
					break;
				}

				return false;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}
	
	private boolean listServerOrders() {

		Map<String, String> map = new HashMap<String, String>();

		map.put("serverId", "" + User.getInstance().getId());

		try {
			JSONObject json = new JSONObject(HttpUtil.postRequest(
					API.LIST_SERVER_ORDERS, map));
			Log.d("LIST_SERVER_ORDERS-json", json.toString());
			if (json.getInt("result") == 0) {
				JSONArray ja = json.getJSONArray("orderInfos");

				data = OrderInfo.toAdapterData(ja);
				SimpleAdapter sa = new SimpleAdapter(getActivity(), data,
						R.layout.my_order_list_item, new String[] { "status",
								"createTime", "desc" }, new int[] {
								R.id.list_item_state, R.id.list_item_time,
								R.id.list_item_desc });
				listView.setAdapter(sa);

				return true;
			} else {
				switch (json.getInt("error")) {
				case 300:
					msg = "订单不存在";
					break;
				case 301:
					msg = "登陆用户密码错误";
					break;
				default:
					break;
				}

				return false;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

}
