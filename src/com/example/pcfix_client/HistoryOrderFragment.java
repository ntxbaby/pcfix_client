package com.example.pcfix_client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class HistoryOrderFragment extends Fragment {
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
		orderMap.put("name", "ά��Ա����");
		orderMap.put("comment", "����:���ʦ���ļ�����ţ�ƣ���΢����һ�£��ܿ���ҵ�������");
		orderMap.put("createTime", "2016-05-01");
		orderMap.put("desc", "����:�ҵĵ����޷�������������������д����Լ죬Ȼ��ס");
		orderMap.put("problem", "10����Ո");
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
				R.layout.history_order_list_item, 
				new String[]{"name","createTime", "desc", "comment"},
                new int[]{R.id.list_item_name,R.id.list_item_time, R.id.list_item_desc,R.id.list_item_comment} );
		listView.setAdapter(sa);
		
		return listView;
	}

}
