package com.mobien.animationtesting;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class AnimationTestingActivity extends Activity {

	LinearLayout lLeft, lRight, lRight2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animation_testing);

		lLeft = (LinearLayout) findViewById(R.id.ll1);
		lRight = (LinearLayout) findViewById(R.id.ll2);
		lRight2 = (LinearLayout) findViewById(R.id.ll3);

	}

	public void onClick(View view) {
		Animation a;
		Animation a1;
		switch (view.getId()) {
		case R.id.button1:
			a = AnimationUtils.loadAnimation(this, R.anim.slide_top_to_bottom);
			a.reset();
			lRight2.setVisibility(View.VISIBLE);
			lRight2.startAnimation(a);
			
			 a1 = AnimationUtils.loadAnimation(this,
			 R.anim.fade_out); a1.reset();
			 lRight.setVisibility(View.GONE); 
			 lRight.startAnimation(a1);
			 

			break;

		case R.id.button2:
			a = AnimationUtils.loadAnimation(this, R.anim.slide_top_to_bottom);
			a.reset();
			lRight2.setVisibility(View.VISIBLE);
			lRight2.startAnimation(a);

			Animation slideRight = AnimationUtils.loadAnimation(this,
					R.anim.slide_right_exit);
			lRight.startAnimation(slideRight);
			lRight.setVisibility(View.GONE);

			break;
			
		case R.id.button3:
			a = AnimationUtils.loadAnimation(this, R.anim.slide_right_exit);
			a.reset();
			lRight.setVisibility(View.GONE);
			lRight.startAnimation(a);

			Animation slideLeft = AnimationUtils.loadAnimation(this,
					R.anim.slide_left_enter);
			lRight2.startAnimation(slideLeft);
//			lRight.startAnimation(slideLeft);
			lRight2.setVisibility(View.VISIBLE);

			break;
			
		
		case R.id.button4:
			
			App_Dialog dialog = new App_Dialog(this, "Trial", "TRIAAALLLLLLLLLLLLLLLLLLLZZZ", 0);
			dialog.show();

			break;
			
			
		case R.id.button6:
			a = AnimationUtils.loadAnimation(this, R.anim.slide_left_exit);
			a.reset();
			lLeft.setVisibility(View.GONE);
			lLeft.startAnimation(a);

		    a1 = AnimationUtils.loadAnimation(this,
					R.anim.slide_right_enter);
			lRight2.startAnimation(a1);
//			lRight.startAnimation(slideLeft);
			lRight2.setVisibility(View.VISIBLE);
			break;
			
		case R.id.button9:
			a = AnimationUtils.loadAnimation(this, R.anim.slide_right_exit);
			a.reset();
			lRight.setVisibility(View.GONE);
			lRight.startAnimation(a);

			slideLeft = AnimationUtils.loadAnimation(this,
					R.anim.slide_left_enter);
			lRight2.startAnimation(slideLeft);
//			lRight.startAnimation(slideLeft);
			lRight2.setVisibility(View.VISIBLE);

			break;

		default:
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_animation_testing, menu);
		return true;
	}

}
