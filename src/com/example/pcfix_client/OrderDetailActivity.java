package com.example.pcfix_client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.pcfix_client.API;
import com.pcfix_client.Applyer;
import com.pcfix_client.HttpUtil;
import com.pcfix_client.Order;
import com.pcfix_client.Price;
import com.pcfix_client.SerializableMap;
import com.pcfix_client.User;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
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
	TextView price;
	TextView status;
	ListView serverList;
	//在处理中 状态下 server页面的完成按钮
	Button btnFinish;
	
	//在验收 状态下  client页面的确认完成按钮
	Button btnOk;
	
	SimpleAdapter sa;
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	SerializableMap serializableMap;
	Bundle mBundle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_detail);
		//隐藏actionbar
		final ActionBar actionBar = getActionBar();
		actionBar.hide();

		serverList = (ListView) findViewById(R.id.order_detail_server_list);
		info = (TextView) findViewById(R.id.order_detail_info);
		price = (TextView) findViewById(R.id.order_detail_price);
		status = (TextView) findViewById(R.id.order_detail_state);
		btnFinish = (Button) findViewById(R.id.order_detail_finish);
		btnOk = (Button) findViewById(R.id.order_detail_ok);
		
		

		mBundle = getIntent().getExtras();

		String text = "地址:" + mBundle.getString("addr") + "\n";
		text += "服务方式:" + mBundle.getInt("mathod") + "\n";
		text += "联系方式:" + mBundle.getString("phone") + "\n";
		text += "问题分类:" + mBundle.getString("problem") + "\n";
		text += "服务时间:" + mBundle.getString("serveTime") + "\n";
		text += "创建时间:" + mBundle.getString("createTime") + "\n";
		text += "描述:" + mBundle.getString("desc") + "\n";
		
		info.setText(text);
		price.setText("订单价格:--");
		status.setText("订单状态:" + mBundle.getString("status"));

		//普通用户，我的订单详情页，显示订单申请者列表，以便选择一个人处理
		if (User.getInstance().getType() == 0) {
			listApplyer();
			sa = new ServersSimpleAdapter(this, list,
					R.layout.order_detail_server_list_item, new String[] {
							"serverName", "price", "serverId" }, new int[] {
							R.id.detail_list_item_name,
							R.id.detail_list_item_price,
							R.id.detail_list_item_time });
			serverList.setAdapter(sa);
		}

	}


	String msg;

	private boolean listApplyer() {

		Map<String, String> map = new HashMap<String, String>();
		Log.d("DDDD", "listMyOrder111111111111");
		map.put("price.orderId", "" + mBundle.getInt("orderId"));

		Log.d("DDDD",
				"listMyOrder22222222222222\norderId="
						+ mBundle.getInt("orderId"));
		try {
			
			JSONObject json = new JSONObject(HttpUtil.postRequest(
					API.LIST_APPLYER, map));
			Log.d("LISTMYORDER-json", json.toString());
			if (json.getInt("result") == 0) {
				
				JSONArray ja = json.getJSONArray("applyers");
				list = Applyer.toAdapterData(ja);

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

	//申请这列表的adapter
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
			Button btn = (Button) v.findViewById(R.id.detail_list_item_select);
			btn.setTag(position);
			btn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					//price.setText("订单价格:100");
					status.setText("订单状态：处理中...");
					serverList.setVisibility(View.INVISIBLE);
					
					Map<String,Object> m = OrderDetailActivity.this.list.get((Integer)v.getTag());
					int priceId = (Integer)m.get("id");
					int orderId = (Integer)m.get("orderId");
					int serverId = (Integer)m.get("serverId");
					int pricevalue = (Integer)m.get("price");
					
					String text = "priceId=" + priceId;
					text += "orderId=" + orderId;
					text += "serverId=" + serverId;
					
					if(selectApplyer(priceId, orderId, serverId))
					{
						text+=" 成功";
						//显示server的名字，price
						price.setText("订单价格:"+pricevalue);
					}
					else
					{
						text+=" 失败";
					}
					
					Toast.makeText(getApplicationContext(),
							"订单详情页点击了" + text, 1).show();
					
					

				}

			});

			return v;
		}
		
		boolean selectApplyer(int priceId, int orderId, int serverId) {
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("priceId", ""+priceId);
			map.put("orderId", ""+orderId);
			map.put("serverId", ""+serverId);
			
			try {
				JSONObject json = new JSONObject(HttpUtil.postRequest(API.SELECTAPPLYER, map));
				Log.d("ADDORDER-json", json.toString());
				if(json.getInt("result") == 0)
				{
					return true;
				}
				else
				{
					msg = json.getInt("error") == 100 ? "用户已经存在" : "未知错误";
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.order_detail, menu);
		return true;
	}

}
