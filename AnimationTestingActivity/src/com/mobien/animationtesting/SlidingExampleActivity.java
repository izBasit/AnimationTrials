package com.mobien.animationtesting;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class SlidingExampleActivity extends Activity {
	
	ListView lvListItems;
	LinearLayout ll;
	
	TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().getAttributes().windowAnimations = R.style.SlidingAnimation;
		setContentView(R.layout.activity_sliding_example);
		
		ll = (LinearLayout)findViewById(R.id.LinearLayout1);
		lvListItems = (ListView)findViewById(R.id.listView1);
		
		String temp [] = {"ABC", "BCD", "EFG", "GHI"};
		tv = (TextView)findViewById(R.id.textView1);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, temp);
		lvListItems.setAdapter(adapter);
		lvListItems.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapt, View view, int position,
					long id) {
				
				tv.setText(adapt.getItemAtPosition(position).toString());
				exitAnimation();
				
			}
		});
		
	}
	
	public void exitAnimation() {
		Animation a = AnimationUtils.loadAnimation(this, R.anim.slide_right_exit);
		a.reset();
		lvListItems.setVisibility(View.GONE);
		lvListItems.startAnimation(a);
		
		ll.startAnimation(a);
	}
	
	public void onClickSlide(View view) {
		
		Animation a;
		
		a = AnimationUtils.loadAnimation(this, R.anim.slide_left);
		a.reset();
		ll.startAnimation(a);
		lvListItems.startAnimation(a);
		lvListItems.setVisibility(View.VISIBLE);
	}

}
