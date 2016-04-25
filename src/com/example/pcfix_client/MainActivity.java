package com.example.pcfix_client;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;

public class MainActivity extends Activity implements ActionBar.TabListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		//tab
		final ActionBar actionBar = getActionBar();
		//actionBar.hide();
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);
		//actionBar.setHomeButtonEnabled(true);
		//actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.addTab(actionBar.newTab().setText("添加订单").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("查看订单").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("用户信息").setTabListener(this));
		
	}
	
	

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

		Log.d("fix", "" + tab.getPosition());
		Fragment fragment = null;
		switch (tab.getPosition()) {
		case 0:
			fragment = new AddOrderFragment();
			break;
		case 1:
			fragment = new ViewOrderFragment();
			break;
		case 2:
			fragment = new UserInfoFragement();
			break;

		default:
			break;
		}
		Bundle args = new Bundle();
		args.putInt("section_num", tab.getPosition() + 1);
		fragment.setArguments(args);
		FragmentTransaction ftr = getFragmentManager().beginTransaction();
		ftr.replace(R.id.main_container, fragment);
		ftr.commit();
	}



	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
