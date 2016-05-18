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
import android.widget.Toast;

public class SettingActivity extends Activity {

	private EditText mEditOldPwd;
	private EditText mEditNewPwd1;
	private EditText mEditNewPwd2;
	private Button	mBtnSave;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		final ActionBar actionBar = getActionBar();
				actionBar.setDisplayShowHomeEnabled(false);
				actionBar.setDisplayShowTitleEnabled(true);
				actionBar.setTitle("修改密码");
				actionBar.setHomeButtonEnabled(true);
				actionBar.setDisplayHomeAsUpEnabled(true);
				mEditOldPwd = (EditText)findViewById(R.id.old_pwd);
				mEditNewPwd1 = (EditText)findViewById(R.id.new_pwd);
				mEditNewPwd2 = (EditText)findViewById(R.id.validate_pwd);
				mBtnSave = (Button)findViewById(R.id.setting_ok);
				mBtnSave.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						if(save())
						{
							Toast.makeText(SettingActivity.this, "密码修改成功！", Toast.LENGTH_LONG).show();
						}
						else
						{
							Toast.makeText(SettingActivity.this, "旧密码错误！", Toast.LENGTH_LONG).show();
						}
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.setting, menu);
		return true;
	}
	
	
	private boolean save(){
		
		
		String oldPwd = mEditOldPwd.getText().toString();
		String newPwd1 = mEditNewPwd1.getText().toString();
		String newPwd2 = mEditNewPwd2.getText().toString();
		if(!newPwd1.equals(newPwd2))
		{
			Toast.makeText(this, "两次输入的新密码不一致！", Toast.LENGTH_LONG).show();
			return false;
		}
		
		
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("user.id", ""+User.getInstance().getId());
		map.put("user.pwd", oldPwd);
		map.put("newPwd", newPwd1);
		
		try {
			JSONObject json = new JSONObject(HttpUtil.postRequest(API.CHANGE_PWD, map));
			Log.d("CHANGE_PWD-json", json.toString());
			if(json.getInt("result") == 0)
			{
				return true;
			}
			else
			{
				return false;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if(item.getItemId() == android.R.id.home)
		{
			finish();
		}
			
		return super.onOptionsItemSelected(item);
	}

}
