package com.mobien.slidinglistmenu;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

public class SideNavigationAdapter extends BaseAdapter {

	private Activity mActivity;
	private ArrayList<HashMap<String, String>> data;
	private LayoutInflater inflater;

	public SideNavigationAdapter(Activity mActivity,
			ArrayList<HashMap<String, String>> data) {

		System.out.println("Constructor of customList");
		this.mActivity = mActivity;
		this.data = data;
		inflater = LayoutInflater.from(mActivity);

	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = convertView;

		if (convertView == null)
			view = inflater.inflate(R.layout.side_navigation_item, null);
		TextView tvLabel = (TextView) view
				.findViewById(R.id.side_navigation_item_text);

		HashMap<String, String> tempHm = new HashMap<String, String>();
		tempHm = data.get(position);
		tvLabel.setText(tempHm.get("LABEL"));

		return view;
	}
}
