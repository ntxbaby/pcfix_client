package com.example.pcfix_client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class OrderDetailActivity extends Activity {
	TextView info;
	ListView serverList;
	SimpleAdapter sa;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_detail);
		final ActionBar actionBar = getActionBar();
		actionBar.hide();
		
		serverList = (ListView)findViewById(R.id.order_detail_server_list);
		info = (TextView)findViewById(R.id.order_detail_info);
		info.setText("问题类型：cpu\n描述：cpu风扇不转\n处理方式：上面服务\n订单状态：等待接单...");
		
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
		
		
		sa = new ServersSimpleAdapter(this, list,
				R.layout.order_detail_server_list_item, 
				new String[]{"name","price", "time"},
                new int[]{R.id.detail_list_item_name,R.id.detail_list_item_price, R.id.detail_list_item_time} );
		serverList.setAdapter(sa);
		
		
	}
	
	private class ServersSimpleAdapter extends SimpleAdapter {

	    public ServersSimpleAdapter(Context context,
				List<? extends Map<String, ?>> data, int resource,
				String[] from, int[] to) {
			super(context, data, resource, from, to);
			// TODO Auto-generated constructor stub
		}



		@Override

	    public View getView(int position, View convertView, ViewGroup parent) {

	        // TODO Auto-generated method stub
	        View v = super.getView(position, convertView, parent);
	        Button btn=(Button) v.findViewById(R.id.detail_list_item_select);
	        btn.setTag(position);
	        btn.setOnClickListener(new OnClickListener() {
	        @Override
	        public void onClick(View v) {
	           // TODO Auto-generated method stub
	           //notifyDataSetChanged();
	        	info.setText("问题类型：cpu\n描述：cpu风扇不转\n处理方式：上面服务\n订单状态：处理中...");
	        	serverList.setVisibility(View.INVISIBLE);
	           Toast.makeText(getApplicationContext(), "订单详情页点击了"+v.getTag(), 1).show();
	           
	        }

	      });

	      return v;
	    }

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.order_detail, menu);
		return true;
	}

}


