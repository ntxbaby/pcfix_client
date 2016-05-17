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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ViewOrderDetailActivity extends Activity {
	TextView info;
	Button apply;
	EditText price;
	String msg;
	private Bundle mBundle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_order_detail);
		final ActionBar actionBar = getActionBar();
		actionBar.hide();

		info = (TextView) findViewById(R.id.view_order_detail_info);
		apply = (Button) findViewById(R.id.view_order_detail_apply);
		price = (EditText) findViewById(R.id.view_order_detail_price);
		mBundle = getIntent().getExtras();

		String text = mBundle.getString("addr") + "\n";
		text += mBundle.getString("name") + "\n";
		text += "创建时间:" + mBundle.getString("createTime") + "\n";
		text += mBundle.getString("desc") + "\n";
		text += mBundle.getString("apply") + "\n";
		info.setText(text);
		// 如果是维修用户
		if (User.getInstance().getType() == 1) {
			apply.setVisibility(View.VISIBLE);
			price.setVisibility(View.VISIBLE);
			apply.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					int orderId = mBundle.getInt("orderId");
					String m = "orderId:" + orderId;
					if (apply(orderId)) {
						m = m + " 申请成功!";
					} else {
						m = m + " 申请失败!";
					}
					Toast.makeText(ViewOrderDetailActivity.this,
							m, Toast.LENGTH_SHORT).show();
				}
			});
		} else {
			apply.setVisibility(View.INVISIBLE);
			price.setVisibility(View.INVISIBLE);
		}

	}

	private boolean apply(int orderId) {

		String p = price.getText().toString();

		Map<String, String> map = new HashMap<String, String>();

		map.put("price.orderId", "" + orderId);
		map.put("price.serverId", "" + User.getInstance().getId());
		map.put("price.price", p);

		try {
			JSONObject json = new JSONObject(HttpUtil.postRequest(API.ADDPRICE,
					map));
			Log.d("AddPrice-json", json.toString());
			if (json.getInt("result") == 0) {

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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.order_detail, menu);
		return true;
	}

}
