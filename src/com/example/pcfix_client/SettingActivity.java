package com.example.pcfix_client;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.view.Menu;

public class SettingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		//actionBar.hide();
		final ActionBar actionBar = getActionBar();
				actionBar.setDisplayShowHomeEnabled(false);
				actionBar.setDisplayShowTitleEnabled(true);
				actionBar.setTitle("�޸�����");
				//actionBar.setHomeButtonEnabled(true);
				actionBar.setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.setting, menu);
		return true;
	}

}