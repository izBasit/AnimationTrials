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
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class SlideActivity extends Activity {

	
	FrameLayout frame1, frame2;
	ListView lv2;
	TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_slide);
		
		frame1 = (FrameLayout)findViewById(R.id.frame1);
		frame2 = (FrameLayout)findViewById(R.id.frame2);
		lv2 = (ListView)findViewById(R.id.listView2);
		tv = (TextView)findViewById(R.id.textView1);
		
		String temp [] = {"ABC", "BCD", "EFG", "GHI"};
		
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, temp);
		lv2.setAdapter(adapter);
		lv2.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapt, View view, int position,
					long id) {
				
				tv.setText(adapt.getItemAtPosition(position).toString());
				exitAnimation();
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_slide, menu);
		return true;
	}
	
	public void exitAnimation() {
		Animation a = AnimationUtils.loadAnimation(this, R.anim.slide_right);
		a.reset();
		
		frame1.startAnimation(a);
		frame2.startAnimation(a);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		params.setMargins(0, 0, 0, 0);
		frame1.setLayoutParams(params);
		
		LayoutParams params1 = new LayoutParams(250, LayoutParams.MATCH_PARENT);
		params1.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params1.setMargins(0, 0, -250, 0);
		frame2.setVisibility(View.INVISIBLE);
		frame2.setLayoutParams(params1);
	}
	
	public void onClickSlide1(View view) {
		
		Animation a;
		
		a = AnimationUtils.loadAnimation(this, R.anim.slide_left);
		a.reset();
		frame1.startAnimation(a);
		frame2.startAnimation(a);
		
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		params.addRule(RelativeLayout.LEFT_OF, frame2.getId());
		params.setMargins(-50, 0, 0, 0);
		
		frame2.setVisibility(View.VISIBLE);
		LayoutParams params1 = new LayoutParams(250, LayoutParams.MATCH_PARENT);
		params1.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params1.setMargins(0, 0, 0, 0);
//		frame2.startAnimation(a);
		
		frame1.setLayoutParams(params);
		frame2.setLayoutParams(params1);
	}

}
