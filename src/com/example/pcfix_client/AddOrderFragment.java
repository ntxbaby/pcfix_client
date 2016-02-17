package com.example.pcfix_client;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.pcfix_client.API;
import com.pcfix_client.HttpUtil;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class AddOrderFragment extends Fragment {

	private Button btnSubmit;
	private EditText editDesc;
	private EditText editPhone;
	private EditText editAddr;
	private EditText editTime;
	private RadioGroup rgProcessMathod;
	private Spinner spinProblem;
	private String msg;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//return super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.activity_add_order, container, false);
		editDesc = (EditText)view.findViewById(R.id.add_order_desc);
		editPhone = (EditText)view.findViewById(R.id.add_order_phone);
		editAddr = (EditText)view.findViewById(R.id.add_order_addr);
		editTime = (EditText)view.findViewById(R.id.add_order_time);
		rgProcessMathod = (RadioGroup)view.findViewById(R.id.add_order_process_method);
		spinProblem = (Spinner)view.findViewById(R.id.add_order_problem);
		btnSubmit = (Button)view.findViewById(R.id.add_order_submit);
		
		btnSubmit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onSubmit(v);
			}
		});
		
		
		
		return view;
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
		map.put("order.time", time);
		map.put("order.mathod", mathod);
		map.put("order.problem", problem);
		
		StringBuilder sb = new StringBuilder();
		sb.append("desc:"+desc +"\n");
		sb.append("phone:"+phone +"\n");
		sb.append("addr:"+addr +"\n");
		sb.append("time:"+time +"\n");
		sb.append("mathod:"+mathod +"\n");
		sb.append("problem:"+problem +"\n");
		Log.d("onSubmit", sb.toString());
		
		try {
			JSONObject json = new JSONObject(HttpUtil.postRequest(API.ADDORDER, map));
			Log.d("json", json.toString());
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
	
	public void onSubmit(View v) {
		
		if (submit()) {
			Toast.makeText(getActivity(), "提交成功！", Toast.LENGTH_LONG).show();
			
			Fragment fragment = null;
			
			fragment = new ViewOrderFragment();
			FragmentTransaction ftr = getFragmentManager().beginTransaction();
			ftr.replace(R.id.main_container, fragment);
			ftr.commit();
			
		}else{
			Toast.makeText(getActivity(), "提交失败！" + msg, Toast.LENGTH_LONG).show();
		}
	}

}
