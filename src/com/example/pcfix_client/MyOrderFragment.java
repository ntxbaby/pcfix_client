package com.example.pcfix_client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AbsListView.LayoutParams;
import android.widget.AdapterView.OnItemClickListener;

public class MyOrderFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		ListView listView = new ListView(getActivity()); 
		listView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
		
		
		List<Map<String, Object> > list = new ArrayList<Map<String, Object> >();
		//test data
		Map<String, Object> orderMap = new HashMap<String, Object>();
		orderMap.put("state", "申请中...");
		orderMap.put("addr", "地址:湖北武汉");
		orderMap.put("createTime", "2016-05-01");
		orderMap.put("desc", "hard disk 坏了，請幫我修一下幫幫我");
		orderMap.put("problem", "10人申請");
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
		
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
				startActivity(intent);
				
			}
			
		});
		
		return listView;
	}

	
}
