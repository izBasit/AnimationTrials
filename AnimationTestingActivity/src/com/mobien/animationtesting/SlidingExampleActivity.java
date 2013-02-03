package com.mobien.animationtesting;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
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
		
		a = AnimationUtils.loadAnimation(this, R.anim.slide_left_exit);
		a.reset();
		ll.startAnimation(a);
		lvListItems.setVisibility(View.VISIBLE);
		lvListItems.startAnimation(a);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_sliding_example, menu);
		return true;
	}

}
