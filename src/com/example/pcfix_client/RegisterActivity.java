package com.example.pcfix_client;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.pcfix_client.API;
import com.pcfix_client.HttpUtil;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity {

	RadioGroup rg;
	EditText editName;
	EditText editPwd;
	EditText editPhone;
	EditText editAddr;
	String msg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		rg = (RadioGroup)findViewById(R.id.register_user_type);
		editName = (EditText)findViewById(R.id.register_name);
		editPwd = (EditText)findViewById(R.id.register_pwd);
		editPhone = (EditText)findViewById(R.id.register_phone);
		editAddr = (EditText)findViewById(R.id.register_addr);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}
	
	public void onRegister(View v) {
		// TODO Auto-generated method stub
		if(register()){
			Toast.makeText(this, "恭喜您，注册成功！", Toast.LENGTH_LONG).show();
			
			Intent intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
		}
		else{
			Toast.makeText(this, "对不起，注册失败！" + msg, Toast.LENGTH_LONG).show();
			((TextView)findViewById(R.id.register_error)).setText(msg);
		}
		
	}
	
	private boolean register(){
		String type = rg.getCheckedRadioButtonId() == R.id.register_normal ? "0" : "1";
		String name = editName.getText().toString();
		String pwd = editPwd.getText().toString();
		String phone = editPhone.getText().toString();
		String addr = editAddr.getText().toString();
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("user.type", type);
		map.put("user.name", name);
		map.put("user.pwd", pwd);
		map.put("user.phone", phone);
		map.put("user.addr", addr);
		
		StringBuilder sb = new StringBuilder();
		sb.append("type:"+type +"\n");
		sb.append("name:"+name +"\n");
		sb.append("pwd:"+pwd +"\n");
		sb.append("phone:"+phone +"\n");
		sb.append("addr:"+addr +"\n");
		Log.d("signup", sb.toString());
		
		try {
			JSONObject json = new JSONObject(HttpUtil.postRequest(API.SIGNUP, map));
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
		return false;
	}

}
