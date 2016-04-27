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
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class AddOrderActivity extends Activity {
	private Button btnSubmit;
	private EditText editDesc;
	private EditText editPhone;
	private EditText editAddr;
	private EditText editTime;
	private RadioGroup rgProcessMathod;
	private Spinner spinProblem;
	private String msg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_order);
		editDesc = (EditText)findViewById(R.id.add_order_desc);
		editPhone = (EditText)findViewById(R.id.add_order_phone);
		editAddr = (EditText)findViewById(R.id.add_order_addr);
		editTime = (EditText)findViewById(R.id.add_order_time);
		rgProcessMathod = (RadioGroup)findViewById(R.id.add_order_process_method);
		spinProblem = (Spinner)findViewById(R.id.add_order_problem);
		btnSubmit = (Button)findViewById(R.id.add_order_submit);
		
		btnSubmit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onSubmit(v);
			}
		});
		
		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle("��Ӷ���");
		//actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		
	}
	
private boolean submit(){
		
		String desc = editDesc.getText().toString();
		String phone = editPhone.getText().toString();
		String addr = editAddr.getText().toString();
		String time = editTime.getText().toString();
		String mathod = rgProcessMathod.getCheckedRadioButtonId() == R.id.add_order_onsite ? "0" : "1";
		String problem = ""+ spinProblem.getSelectedItemId();
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("order.desc", desc);
		map.put("order.phone", phone);
		map.put("order.addr", addr);
		map.put("order.createTime", time);
		map.put("order.serveTime", time);
		map.put("order.mathod", mathod);
		map.put("order.problem", problem);
		map.put("order.clientId", ""+User.getInstance().getId());
		
		StringBuilder sb = new StringBuilder();
		sb.append("desc:"+desc +"\n");
		sb.append("phone:"+phone +"\n");
		sb.append("addr:"+addr +"\n");
		sb.append("createTime:"+time +"\n");
		sb.append("serveTime:"+time +"\n");
		sb.append("mathod:"+mathod +"\n");
		sb.append("problem:"+problem +"\n");
		sb.append("order.clientId"+User.getInstance().getId());
		Log.d("ADDORDER", sb.toString());
		
		try {
			JSONObject json = new JSONObject(HttpUtil.postRequest(API.ADDORDER, map));
			Log.d("ADDORDER-json", json.toString());
			if(json.getInt("result") == 0)
			{
				return true;
			}
			else
			{
				msg = json.getInt("error") == 100 ? "�û��Ѿ�����" : "δ֪����";
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
	
	public void onSubmit(View v) {
		
		if (submit()) {
			Toast.makeText(this, "�ύ�ɹ���", Toast.LENGTH_LONG).show();
			
			Fragment fragment = null;
			
			fragment = new ViewOrderFragment();
			FragmentTransaction ftr = getFragmentManager().beginTransaction();
			ftr.replace(R.id.main_container, fragment);
			ftr.commit();
			
		}else{
			Toast.makeText(this, "�ύʧ�ܣ�" + msg, Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.add_order, menu);
		return true;
	}

}
