package com.example.pcfix_client;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class OrderDetailFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//return super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.order_detail, container, false);
		ListView serverList = (ListView)view.findViewById(R.id.order_detail_server_candidate);
		TextView detailInfo = (TextView)view.findViewById(R.id.order_detail_info);
		String si = "" + getArguments().getInt("select_item");
		detailInfo.setText(si);
		return view;
	}

}
