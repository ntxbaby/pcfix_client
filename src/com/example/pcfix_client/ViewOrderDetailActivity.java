package com.example.pcfix_client;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.pcfix_client.API;
import com.pcfix_client.HttpUtil;
import com.pcfix_client.User;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//活动订单详情页
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
		// actionbar设置
		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle("活动订单详情");
		actionBar.setDisplayHomeAsUpEnabled(true);

		info = (TextView) findViewById(R.id.view_order_detail_info);
		apply = (Button) findViewById(R.id.view_order_detail_apply);
		price = (EditText) findViewById(R.id.view_order_detail_price);
		mBundle = getIntent().getExtras();

		String text = mBundle.getString("addr") + "\n";
		text += mBundle.getString("clientName") + "\n";
		text += "创建时间:" + mBundle.getString("createTime") + "\n";
		text += mBundle.getString("desc") + "\n";
		text += mBundle.getString("applyerNum") + "\n";
		info.setText(text);
		// 如果是维修用户
		if (User.getInstance().getType() == 1) {
			//显示申请者数目和价格控件
			apply.setVisibility(View.VISIBLE);
			price.setVisibility(View.VISIBLE);
			
			//设置申请按钮回调函数
			apply.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					int orderId = mBundle.getInt("orderId");
					String m = "orderId:" + orderId;
					if (apply(orderId)) {
						m = m + " 申请成功!";
					} else {
						m = m + " 申请失败!";
					}
					Toast.makeText(ViewOrderDetailActivity.this, m,
							Toast.LENGTH_LONG).show();
					//申请完后退出详情页
					ViewOrderDetailActivity.this.finish();
				}
			});
		} else {
			//隐藏申请者数目和价格控件
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
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.order_detail, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
		}
		return super.onOptionsItemSelected(item);
	}

}
