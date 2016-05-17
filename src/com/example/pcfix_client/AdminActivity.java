package com.example.pcfix_client;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class AdminActivity extends Activity implements ActionBar.TabListener{
	Fragment fragment = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin);
		
				final ActionBar actionBar = getActionBar();
				actionBar.setDisplayShowHomeEnabled(false);
				actionBar.setDisplayShowTitleEnabled(false);
				actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
				actionBar.addTab(actionBar.newTab().setText("用户管理").setTabListener(this));
				actionBar.addTab(actionBar.newTab().setText("订单管理").setTabListener(this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.admin, menu);
		return true;
	}
	
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

		Log.d("fix", "" + tab.getPosition());
		
		switch (tab.getPosition()) {
		case 0:
			fragment = new UserMgrFragment();
			break;
		case 1:
			fragment = new OrderMgrFragment();
			break;

		default:
			break;
		}
		FragmentTransaction ftr = getFragmentManager().beginTransaction();
		ftr.replace(R.id.admin_container, fragment);
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
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId())
		{
		case R.id.admin_delete:
		{
			if (fragment instanceof UserMgrFragment) {
				UserMgrFragment umf = (UserMgrFragment) fragment;
				umf.deleteUsers();
			}
			else{
				OrderMgrFragment omf = (OrderMgrFragment) fragment;
				omf.deleteOrders();
			}
			break;
		}
		
		
		}
		return super.onOptionsItemSelected(item);
	}

}
