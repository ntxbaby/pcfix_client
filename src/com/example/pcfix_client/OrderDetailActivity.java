package com.example.pcfix_client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class OrderDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_detail);
		final ActionBar actionBar = getActionBar();
		actionBar.hide();
		
		ListView serverList = (ListView)findViewById(R.id.order_detail_server_list);
		
		List<Map<String, Object> > list = new ArrayList<Map<String, Object> >();
		//test data
		Map<String, Object> orderMap = new HashMap<String, Object>();
		orderMap.put("name", "维修员:成栋");
		orderMap.put("price", "出价:1000元");
		orderMap.put("time", "2016-05-01");
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
		
		
		SimpleAdapter sa = new SimpleAdapter(this, list,
				R.layout.order_detail_server_list_item, 
				new String[]{"name","price", "time"},
                new int[]{R.id.detail_list_item_name,R.id.detail_list_item_price, R.id.detail_list_item_time} );
		serverList.setAdapter(sa);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.order_detail, menu);
		return true;
	}

}
