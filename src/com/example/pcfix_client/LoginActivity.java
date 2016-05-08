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
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class LoginActivity extends Activity {

	EditText editName, editPwd;
	Button btnLogin, btnCancel, btnRegister;
	RadioGroup rg;
	String msg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		editName = (EditText)findViewById(R.id.login_user);
		editPwd = (EditText)findViewById(R.id.login_pwd);
		btnLogin = (Button)findViewById(R.id.btnLogin);
		btnCancel = (Button)findViewById(R.id.btnCancel);
		btnRegister = (Button)findViewById(R.id.btnRegister);
		rg = (RadioGroup)findViewById(R.id.login_user_type);
		final ActionBar actionBar = getActionBar();
		actionBar.hide();
	}
	
	public void onLogin(View v) {
		// TODO Auto-generated method stub
		if(validate()){
			if(loginPro()){
				Toast.makeText(this, "恭喜您，登陆成功！", Toast.LENGTH_LONG).show();
				Intent intent = new Intent(LoginActivity.this, MainActivity.class);
				startActivity(intent);
			}else{
				Toast.makeText(this, "对不起，登陆失败！" + msg, Toast.LENGTH_LONG).show();
			}
		}
	}

	public void onRegister(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, RegisterActivity.class);
		startActivity(intent);
	}
	
	private boolean validate(){
		return true;
	}
	
	private boolean loginPro(){
	
		String type = rg.getCheckedRadioButtonId() == R.id.login_client ? "0" : "1";
		String name = editName.getText().toString();
		String pwd = editPwd.getText().toString();
		
		StringBuilder sb = new StringBuilder();
		sb.append("type:"+type +"\n");
		sb.append("name:"+name +"\n");
		sb.append("pwd:"+pwd +"\n");
		
		Log.d("LOGIN", sb.toString());
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("user.type", type);
		map.put("user.name", name);
		map.put("user.pwd", pwd);
		
		try {
			JSONObject json = new JSONObject(HttpUtil.postRequest(API.LOGIN, map));
			Log.d("LOGIN-json", json.toString());
			if(json.getInt("result") == 0)
			{
				JSONObject jsonUser = json.getJSONObject("user");
				User.fromJSONObject(jsonUser);
				return true;
			}
			else
			{
				switch (json.getInt("error")) {
				case 200:
					msg = "登陆用户不存在";
					break;
				case 201:
					msg = "登陆用户密码错误";	
					break;
				case 202:
					msg = "登陆用户类型错误";
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
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
