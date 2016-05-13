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
	SimpleAdapter sa;
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	SerializableMap serializableMap;
	Bundle mBundle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_detail);
		final ActionBar actionBar = getActionBar();
		actionBar.hide();

		serverList = (ListView) findViewById(R.id.order_detail_server_list);
		info = (TextView) findViewById(R.id.order_detail_info);
		price = (TextView) findViewById(R.id.order_detail_price);
		status = (TextView) findViewById(R.id.order_detail_state);

		mBundle = getIntent().getExtras();

		String text = "��ַ:" + mBundle.getString("addr") + "\n";
		text += "����ʽ:" + mBundle.getInt("mathod") + "\n";
		text += "��ϵ��ʽ:" + mBundle.getString("phone") + "\n";
		text += "�������:" + mBundle.getString("problem") + "\n";
		text += "����ʱ��:" + mBundle.getString("serveTime") + "\n";
		text += "����ʱ��:" + mBundle.getString("createTime") + "\n";
		text += "����:" + mBundle.getString("desc") + "\n";
		info.setText(text);
		price.setText("�����۸�:--");
		status.setText("����״̬:" + mBundle.getString("status"));

		// list = new ArrayList<Map<String, Object> >();
		// //test data
		// Map<String, Object> orderMap = new HashMap<String, Object>();
		// orderMap.put("name", "ά��Ա:�ɶ�");
		// orderMap.put("price", "����:1000Ԫ");
		// orderMap.put("time", "2016-05-01");
		// orderMap.put("desc", "hard disk ���ˣ�Ո������һ�͎���");
		// orderMap.put("problem", "10����Ո");
		// list.add(orderMap);
		// list.add(orderMap);
		// list.add(orderMap);
		// list.add(orderMap);
		// list.add(orderMap);
		// list.add(orderMap);
		// list.add(orderMap);
		// list.add(orderMap);
		// list.add(orderMap);
		// list.add(orderMap);
		// list.add(orderMap);

		listMyOrder();
		sa = new ServersSimpleAdapter(
				this,
				list,
				R.layout.order_detail_server_list_item,
				new String[] { "name", "price", "time" },
				new int[] { R.id.detail_list_item_name,
						R.id.detail_list_item_price, R.id.detail_list_item_time });
		serverList.setAdapter(sa);

	}

	public List<Map<String, Object>> getData(JSONArray prices) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < prices.length(); i++) {

			JSONObject price;
			try {

				price = prices.getJSONObject(i);
				Price p = new Price();
				p.fromJSONObject(price);
				list.add(p.toPriceMap());

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return list;
	}

	String msg;

	private boolean listMyOrder() {

		Map<String, String> map = new HashMap<String, String>();
		Log.d("DDDD", "listMyOrder111111111111");
		map.put("price.orderId", "" + mBundle.getInt("orderId"));

		Log.d("DDDD",
				"listMyOrder22222222222222\norderId="
						+ mBundle.getInt("orderId"));
		try {
			JSONObject json = new JSONObject(HttpUtil.postRequest(
					API.LISTPRICE, map));
			Log.d("LISTMYORDER-json", json.toString());
			if (json.getInt("result") == 0) {
				JSONArray prices = json.getJSONArray("prices");

				list = getData(prices);

				return true;
			} else {
				switch (json.getInt("error")) {
				case 300:
					msg = "����������";
					break;
				case 301:
					msg = "��½�û��������";
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
					// TODO Auto-generated method stub
					// notifyDataSetChanged();
					// info.setText("�������ͣ�cpu\n������cpu���Ȳ�ת\n����ʽ���������\n����״̬��������...");
					price.setText("�����۸�:100");
					status.setText("����״̬��������...");
					serverList.setVisibility(View.INVISIBLE);
					Toast.makeText(getApplicationContext(),
							"��������ҳ�����" + v.getTag(), 1).show();

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
