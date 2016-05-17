package com.example.pcfix_client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pcfix_client.DataManager;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class HistoryOrderFragment extends Fragment {
	ListView listView;
	List<Map<String, Object> > list;
	String msg;
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
		orderMap.put("name", "维修员名字");
		orderMap.put("comment", "评论:这个师傅的技术很牛逼，稍微看了一下，很快就找到了问题");
		orderMap.put("createTime", "2016-05-01");
		orderMap.put("desc", "描述:我的电脑无法开机，电脑启动后进行磁盘自检，然后卡住");
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
				R.layout.history_order_list_item, 
				new String[]{"name","createTime", "desc", "comment"},
                new int[]{R.id.list_item_name,R.id.list_item_time, R.id.list_item_desc,R.id.list_item_comment} );
		listView.setAdapter(sa);
		*/
		refresh();
		return listView;
	}
	
	public boolean refresh()
	{
			int ret = DataManager.getInstance().refreshHistory();
			if (ret == 0) {

				list = DataManager.getInstance().getHisOrderMap();
				SimpleAdapter sa = new SimpleAdapter(getActivity(), list,
						R.layout.history_order_list_item, 
						new String[]{"clientName","finishType", "desc", "comment"},
		                new int[]{R.id.list_item_name,R.id.list_item_time, R.id.list_item_desc,R.id.list_item_comment} );
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
