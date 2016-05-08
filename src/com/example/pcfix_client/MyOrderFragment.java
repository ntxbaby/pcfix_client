package com.example.pcfix_client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.pcfix_client.API;
import com.pcfix_client.HttpUtil;
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

	ListView listView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		listView = new ListView(getActivity()); 
		listView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
		
		/*
		List<Map<String, Object> > list = new ArrayList<Map<String, Object> >();
		//test data
		Map<String, Object> orderMap = new HashMap<String, Object>();
		orderMap.put("state", "申请中...");
		orderMap.put("createTime", "2016-05-01");
		orderMap.put("desc", "hard disk 坏了，請幫我修一下幫幫我");
		list.add(orderMap);
		list.add(orderMap);
		list.add(orderMap);
		list.add(orderMap);
		list.add(orderMap);
		list.add(orderMap);
		list.add(orderMap);
		list.add(orderMap);
		list.add(orderMap);
		list.add(orderMap);
		list.add(orderMap);
		
		
		SimpleAdapter sa = new SimpleAdapter(getActivity(), list,
				R.layout.my_order_list_item, 
				new String[]{"state","createTime", "desc"},
                new int[]{R.id.list_item_state,R.id.list_item_time, R.id.list_item_desc} );
		listView.setAdapter(sa);
		
		*/
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
				startActivity(intent);
				
			}
			
		});
		
		listMyOrder();
		
		return listView;
	}
	
	String msg;
	private boolean listMyOrder(){
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("order.clientId", ""+User.getInstance().getId());
		
		
		try {
			JSONObject json = new JSONObject(HttpUtil.postRequest(API.LISTMYORDER, map));
			Log.d("LISTMYORDER-json", json.toString());
			if(json.getInt("result") == 0)
			{
				JSONArray orders = json.getJSONArray("orders");
				
				SimpleAdapter sa = new SimpleAdapter(getActivity(), ViewOrderFragment.getData(orders),
						R.layout.my_order_list_item, 
						new String[]{"status","createTime", "desc"},
		                new int[]{R.id.list_item_state,R.id.list_item_time, R.id.list_item_desc} );
				listView.setAdapter(sa);
				
				
				return true;
			}
			else
			{
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
