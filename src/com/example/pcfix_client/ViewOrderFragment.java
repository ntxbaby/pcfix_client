package com.example.pcfix_client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.pcfix_client.API;
import com.pcfix_client.HttpUtil;
import com.pcfix_client.Order;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class ViewOrderFragment extends Fragment {

	ListView orderList;
	Button btnRefresh;
	Button btnMyOrder;
	String [] problems = new String[]{"cpu","ƒ⁄¥Ê","œ‘ø®","”≤≈Ã","œ‘ æ∆˜","º¸≈Ã"," Û±Í"};//getResources().getStringArray(R.array.problems);
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//return super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.view_order, container, false);
		orderList = (ListView)view.findViewById(R.id.view_order_list);
		btnRefresh = (Button)view.findViewById(R.id.view_order_refresh);
		btnMyOrder = (Button)view.findViewById(R.id.view_order_my_order);
		
		btnRefresh.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				list();
			}
		});
		
		
		orderList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Fragment fragment = null;
				
				fragment = new OrderDetailFragment();
				
				Bundle args = new Bundle();
				args.putInt("select_item", position);
				fragment.setArguments(args);
				FragmentTransaction ftr = getFragmentManager().beginTransaction();
				ftr.replace(R.id.main_container, fragment);
				ftr.commit();
			}
			
		});
		
		list();
		
		return view;
	}
	
	
	List<Map<String, Object> > getData(JSONArray orders){
		
		List<Map<String, Object> > list = new ArrayList<Map<String, Object> >();
		for (int i = 0; i < orders.length(); i++) {
			
			JSONObject order;
			try {
				
				order = orders.getJSONObject(i);
				Order o = new Order();
				o.fromJSONObject(order);
				list.add(o.toOrderMap(problems));
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return list;
	}
	
	String msg;
	private boolean list(){
		String type = "1";
		String name = "1";
		String pwd = "1";
		
		StringBuilder sb = new StringBuilder();
		sb.append("type:"+type +"\n");
		sb.append("name:"+name +"\n");
		sb.append("pwd:"+pwd +"\n");
		
		Log.d("LISTORDER", sb.toString());
		
		Map<String, String> map = new HashMap<String, String>();
		
		
		try {
			JSONObject json = new JSONObject(HttpUtil.postRequest(API.LISTORDER, map));
			Log.d("LISTORDER-json", json.toString());
			if(json.getInt("result") == 0)
			{
				JSONArray orders = json.getJSONArray("orders");
				SimpleAdapter sa = new SimpleAdapter(getActivity(), getData(orders),
						R.layout.view_order_list_item, 
						new String[]{"head","createTime","problem", "desc"},
		                new int[]{R.id.list_item_head,R.id.list_item_time,R.id.list_item_type, R.id.list_item_desc} );
				orderList.setAdapter(sa);
				return true;
			}
			else
			{
				switch (json.getInt("error")) {
				case 300:
					msg = "∂©µ•≤ª¥Ê‘⁄";
					break;
				case 301:
					msg = "µ«¬Ω”√ªß√‹¬Î¥ÌŒÛ";	
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
	

}
