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
	String name;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		final ActionBar actionBar = getActionBar();
		actionBar.hide();
		editName = (EditText)findViewById(R.id.login_user);
		editPwd = (EditText)findViewById(R.id.login_pwd);
		btnLogin = (Button)findViewById(R.id.btnLogin);
		btnCancel = (Button)findViewById(R.id.btnCancel);
		btnRegister = (Button)findViewById(R.id.btnRegister);
		//rg = (RadioGroup)findViewById(R.id.login_user_type);
		
	}
	
	public void onLogin(View v) {

			if(validate()){
				Toast.makeText(this, "恭喜您，登陆成功！", Toast.LENGTH_LONG).show();
				Intent intent = null;
				if (!name.equals("admin")) {
					intent = new Intent(LoginActivity.this, MainActivity.class);
				} else {
					intent = new Intent(LoginActivity.this, AdminActivity.class);
				}
				startActivity(intent);
			}else{
				Toast.makeText(this, "对不起，登陆失败！" + msg, Toast.LENGTH_LONG).show();
			}
		
	}

	//注册按钮的回调函数，启动注册activity
	public void onRegister(View v) {
		Intent intent = new Intent(this, RegisterActivity.class);
		startActivity(intent);
	}
	
	
	private boolean validate(){
	
		//String type = rg.getCheckedRadioButtonId() == R.id.login_client ? "0" : "1";
		name = editName.getText().toString();
		String pwd = editPwd.getText().toString();
		
		
		Map<String, String> map = new HashMap<String, String>();
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
					msg = "参数错误";
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
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
