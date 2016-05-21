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
import com.pcfix_client.DataManager;
import com.pcfix_client.HttpUtil;
import com.pcfix_client.Order;
import com.pcfix_client.OrderInfo;
import com.pcfix_client.Price;
import com.pcfix_client.User;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
	String msg;
	TextView info;
	TextView price;
	TextView status;
	ListView serverList;
	//在处理中 状态下 server页面的完成按钮
	Button btnFinish;
	
	//在验收 状态下  client页面的确认完成按钮
	Button btnOk;
	
	SimpleAdapter sa;
	List<Map<String, Object>> mListMap = new ArrayList<Map<String, Object>>();
	List<Applyer> mListApplyer;
	Bundle mBundle;
	OrderInfo o = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_detail);
		//隐藏actionbar
		//actionbar设置
		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle("我的订单详情");
		actionBar.setDisplayHomeAsUpEnabled(true);

		serverList = (ListView) findViewById(R.id.order_detail_server_list);
		info = (TextView) findViewById(R.id.order_detail_info);
		price = (TextView) findViewById(R.id.order_detail_price);
		status = (TextView) findViewById(R.id.order_detail_state);
		btnFinish = (Button) findViewById(R.id.order_detail_finish);
		btnOk = (Button) findViewById(R.id.order_detail_ok);
		
		//btnFinish
		btnFinish.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(clickFinish())
						{
					refresh(true);
						}
			}
		});
		
		btnOk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(clickOk()){
					//确认完成
					Toast.makeText(OrderDetailActivity.this, "订单完成",
							Toast.LENGTH_LONG).show();
					//订单完后退出详情页
					OrderDetailActivity.this.finish();
				}
			}
		});
		

		refresh(false);
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		//返回到主activity
		if(item.getItemId() == android.R.id.home)
		{
			finish();
		}
			
		return super.onOptionsItemSelected(item);
	}


	
	//普通用户从申请者列表中选择一个维修者调用
	private boolean listApplyer() {

		Map<String, String> map = new HashMap<String, String>();
		map.put("price.orderId", "" + o.getOrderId());

		try {
			
			JSONObject json = new JSONObject(HttpUtil.postRequest(
					API.LIST_APPLYER, map));
			Log.d("LISTMYORDER-json", json.toString());
			if (json.getInt("result") == 0) {
				
				JSONArray ja = json.getJSONArray("applyers");
				mListMap = Applyer.toAdapterData(ja);
				mListApplyer = Applyer.toApplyerInfo(ja);
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
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}
	
	//刷新页面
	//我的订单详情页面根据 1维修用户还是普通用户 2订单当前状态等 显示有所不同
	//订单基本信息总是会显示
	//1普通用户 在: 申请状态 会显示显示申请者列表，处理状态无额外显示，验收状态会显示一个确认完成按钮
	//2维修用户在 ：申请状态和验收状态无额外显示，处理状态会显示完成按钮
	private void refresh(boolean isForce)
	{
		boolean isClient = User.getInstance().getType() == 0;
		mBundle = getIntent().getExtras();
		int pos = mBundle.getInt("pos");
		if(isForce)
		{
			//请求订单状态
			if(isClient)
			{	
				DataManager.getInstance().refreshClient();
			}
			else
			{
				DataManager.getInstance().refreshServer();
			}
		}
		
		
		if(isClient)
		{	
			o = DataManager.getInstance().getClientOrderList().get(pos);
		}
		else
		{
			o = DataManager.getInstance().getServerOrderList().get(pos);
		}
		
		String text = "地址:" + o.getAddr() + "\n";
		text += "服务方式:" + OrderInfo.MATHOD_STRING[o.getMathod()] + "\n";
		text += "联系方式:" + o.getPhone() + "\n";
		text += "问题分类:" + OrderInfo.PROBLEMS[o.getProblem()] + "\n";
		text += "服务时间:" + o.getServeTime() + "\n";
		text += "创建时间:" + o.getCreateTime() + "\n";
		text += "问题描述:" + o.getDesc() + "\n";
		info.setText(text);
		
		
		int sta = o.getStatus();
		status.setText("订单状态:" + OrderInfo.STATUS_STRING[sta]);
		listApplyer();
		
		if(isClient){
			
			if(sta == OrderInfo.STATUS_APPLY)
			{
				
				price.setText("订单价格:--");
				
				sa = new ServersSimpleAdapter(this, mListMap,
						R.layout.order_detail_server_list_item, new String[] {
								"serverName", "price", "serverId" }, new int[] {
								R.id.detail_list_item_name,
								R.id.detail_list_item_price,
								R.id.detail_list_item_time });
				if(sa == null)
				{
					Log.d("debug", "sa=null");
				}
				if(mListMap == null)
				{
					Log.d("debug", "mListMap=null");
				}
				if(serverList == null)
				{
					Log.d("debug", "serverList=null");
				}
				
				serverList.setAdapter(sa);
			}
			else if(sta == OrderInfo.STATUS_DEAL){
				price.setText("订单价格:"+mListMap.get(0).get("price")+"\n维修者:"+mListMap.get(0).get("serverName"));
				serverList.setVisibility(View.INVISIBLE);
			}
			else if(sta == OrderInfo.STATUS_VARIFY){
				price.setText("订单价格:"+mListMap.get(0).get("price")+"\n维修者:"+mListMap.get(0).get("serverName"));
				serverList.setVisibility(View.INVISIBLE);
				btnOk.setVisibility(View.VISIBLE);
			}
			
		}
		else
		{
			serverList.setVisibility(View.INVISIBLE);
			
			if(sta == OrderInfo.STATUS_APPLY)
			{
				
			}
			else if(sta == OrderInfo.STATUS_DEAL){
				price.setText("订单价格:"+mListMap.get(0).get("price")+"\n维修者:"+mListMap.get(0).get("serverName"));
				btnFinish.setVisibility(View.VISIBLE);
			}
			else if(sta == OrderInfo.STATUS_VARIFY){
				price.setText("订单价格:"+mListMap.get(0).get("price")+"\n维修者:"+mListMap.get(0).get("serverName"));
				btnFinish.setVisibility(View.INVISIBLE);
			}
		}
		
	}

	//申请这列表的adapter
	private class ServersSimpleAdapter extends SimpleAdapter {

		public ServersSimpleAdapter(Context context,
				List<? extends Map<String, ?>> data, int resource,
				String[] from, int[] to) {
			super(context, data, resource, from, to);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			View v = super.getView(position, convertView, parent);
			Button btn = (Button) v.findViewById(R.id.detail_list_item_select);
			btn.setTag(position);
			btn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					//price.setText("订单价格:100");
					status.setText("订单状态：处理中...");
					serverList.setVisibility(View.INVISIBLE);
					
					Applyer a = OrderDetailActivity.this.mListApplyer.get((Integer)v.getTag());
					int priceId = a.getId();
					int orderId = a.getOrderId();
					int serverId = a.getServerId();
					int pricevalue = a.getPrice();
					
					String text = "priceId=" + priceId;
					text += "orderId=" + orderId;
					text += "serverId=" + serverId;
					
					if(selectApplyer(priceId, orderId, serverId))
					{
						text+=" 成功";
						//显示server的名字，price
						//price.setText("订单价格:"+pricevalue);
						refresh(true);
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
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.order_detail, menu);
		return true;
	}
	
	//维修者点“完成” 调用
	public boolean clickFinish(){
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("orderId", ""+o.getOrderId());
		
		try {
			JSONObject json = new JSONObject(HttpUtil.postRequest(API.FINISH, map));
			Log.d("FINISH-json", json.toString());
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
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	//客户点“确认完成” 调用
	public boolean clickOk(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("orderId", ""+o.getOrderId());
		
		try {
			JSONObject json = new JSONObject(HttpUtil.postRequest(API.OK, map));
			Log.d("OK-json", json.toString());
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
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

}
