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
			listClientOrders();
		} else {
			listServerOrders();
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
				intent.putExtras(mBundle);

				startActivity(intent);

			}

		});

		return listView;
	}

	private boolean listClientOrders() {

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
	
	private boolean listServerOrders() {

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
	


}
