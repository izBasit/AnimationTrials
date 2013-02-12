package com.mobien.animationtesting;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class SideNavigationActivity extends Activity {
	
	private TextView tvListItem;
	private LinearLayout sideNavigationMenu;
	private ListView lvItems;
	private View outsideView;
	private Button btn;
	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_side_navigation);
		mContext = this;
		tvListItem = (TextView)findViewById(R.id.textView11);
		lvItems = (ListView)findViewById(R.id.side_navigation_listview);
		sideNavigationMenu = (LinearLayout)findViewById(R.id.side_navigation_menu);
		outsideView = (View) findViewById(R.id.side_navigation_outside_view);
		btn = (Button)findViewById(R.id.button11);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				toggleMenu();
			}
		});
		
		outsideView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				toggleMenu();
			}
		});
		
		
		String temp [] = {"ABC", "BCD", "EFG", "GHI"};
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, temp);
		lvItems.setAdapter(adapter);
		lvItems.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapt, View view, int position,
					long id) {
				String listItem = adapt.getItemAtPosition(position).toString();
				tvListItem.setText(listItem);
				toggleMenu();
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_side_navigation, menu);
		return true;
	}
	
	
    @Override
    public void onBackPressed() {
        // hide menu if it shown
        if (sideNavigationMenu.isShown()) {
        		hideMenu();
        } else {
            super.onBackPressed();
        }
    }
    
    public boolean isShown() {
        return sideNavigationMenu.isShown();
    }
    
    /**
     * Show/Hide side navigation menu depending on visibility.
     */
    public void toggleMenu() {
        if (isShown()) {
            hideMenu();
        } else {
            showMenu();
        }
    }
    
    /**
     * Hide side navigation menu.
     */
    public void hideMenu() {
        outsideView.setVisibility(View.GONE);
        outsideView.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.side_navigation_fade_out));
        // hide navigation menu with animation
        int animRes = R.anim.side_navigation_out_to_right;
        sideNavigationMenu.setVisibility(View.GONE);
        sideNavigationMenu.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), animRes));
    }
    
    /**
     * Show side navigation menu.
     */
    public void showMenu() {
        outsideView.setVisibility(View.VISIBLE);
        outsideView.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.side_navigation_fade_in));
        // show navigation menu with animation
        int animRes = R.anim.side_navigation_in_from_right;
        sideNavigationMenu.setVisibility(View.VISIBLE);
        sideNavigationMenu.startAnimation(AnimationUtils.loadAnimation(mContext, animRes));
    }
    
	
}
