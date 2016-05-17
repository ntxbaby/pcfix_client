package com.example.pcfix_client;

import com.pcfix_client.User;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements ActionBar.TabListener {
	Fragment fragment = null;
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
		actionBar.addTab(actionBar.newTab().setText("活动订单").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("我的订单").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("历史订单").setTabListener(this));
		
	}
	
	

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

		Log.d("fix", "" + tab.getPosition());
		
		switch (tab.getPosition()) {
		case 0:
			fragment = new ViewOrderFragment();
			break;
		case 1:
			fragment = new MyOrderFragment();
			break;
		case 2:
			fragment = new HistoryOrderFragment();
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
		menu.getItem(0).setVisible(User.getInstance().getType() == 0);
		return true;
	}



	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		
		return super.onMenuItemSelected(featureId, item);
	}



	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId())
		{
		case R.id.action_add:
		{
			Intent intent = new Intent(this, AddOrderActivity.class);
			startActivity(intent);
			break;
		}
		case R.id.action_refresh:
		{
			
			Toast.makeText(this, "刷新", Toast.LENGTH_LONG).show();
			if (fragment instanceof ViewOrderFragment) {
				ViewOrderFragment vf = (ViewOrderFragment) fragment;
				vf.list1();
			}
			else if( fragment instanceof MyOrderFragment)
			{
				MyOrderFragment mf = (MyOrderFragment) fragment;
				mf.refresh();
			}
			else if( fragment instanceof HistoryOrderFragment)
			{
				HistoryOrderFragment hf = (HistoryOrderFragment) fragment;
				hf.refresh();
			}
			break;
		}
			
		case R.id.action_user:
		{
			Intent intent = new Intent(this, SettingActivity.class);
			startActivity(intent);
			
			break;
		}
		
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	

}
