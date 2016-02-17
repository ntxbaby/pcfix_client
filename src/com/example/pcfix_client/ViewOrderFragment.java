package com.example.pcfix_client;

import android.app.ListFragment;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class ViewOrderFragment extends ListFragment {

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		String [] items = {"item1","item2","item3","item4"};
		setListAdapter(new ArrayAdapter<String>(getActivity(),  
                android.R.layout.simple_list_item_activated_1,  
                items)); //使用静态数组填充列表  
		
	}

}
