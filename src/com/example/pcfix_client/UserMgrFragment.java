package com.example.pcfix_client;

import java.io.ObjectOutputStream.PutField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.pcfix_client.API;
import com.pcfix_client.DataManager;
import com.pcfix_client.HttpUtil;
import com.pcfix_client.Order;
import com.pcfix_client.OrderInfo;
import com.pcfix_client.SerializableMap;
import com.pcfix_client.User;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AbsListView.LayoutParams;
import android.widget.AdapterView.OnItemClickListener;

public class UserMgrFragment extends Fragment {

	private String msg;
	private List<Map<String, Object>> data;
	ListView listView;
	private List<User> mUserList;
	private ArrayAdapter<String> mAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		listView = new ListView(getActivity());
		listView.setLayoutParams(new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		// test
		refresh();
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

			}

		});

		return listView;
	}

	public void deleteUsers() {
		SparseBooleanArray checkedItemPositions = listView
				.getCheckedItemPositions();
		Log.d("deleteUsers", "size="+checkedItemPositions.size());
		for (int i = 0; i < checkedItemPositions.size(); i++) {
			int key = checkedItemPositions.keyAt(i);
			if(checkedItemPositions.get(key))
			{
				Log.d("deleteUsers", "i="+ i + "key="+ key);
				DataManager.getInstance().deleteUser(mUserList.get(i).getId());
			}
		}
		refresh();
		Toast.makeText(getActivity(), "勾选了:" + listView.getCheckedItemCount(),
				Toast.LENGTH_LONG).show();

	}

	public boolean refresh() {

		int ret = DataManager.getInstance().refreshUser();
		if (0 == ret)

		{
			ArrayList<String> al = new ArrayList<String>();
			mUserList = DataManager.getInstance().getUserList();
			for (User u : mUserList) {
				al.add(u.getName());
			}
			mAdapter = new ArrayAdapter<String>(getActivity(),
					android.R.layout.simple_list_item_multiple_choice, al);
			listView.setAdapter(mAdapter);

			return true;
		} else {
			switch (ret) {
			case 300:
				msg = "订单不存在";
				break;
			case 301:
				msg = "登陆用户密码错误";
				break;
			default:
				break;
			}

			return false;
		}

	}

}
