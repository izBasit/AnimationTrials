package com.mobien.slidinglistmenu;

import com.mobien.slidinglistmenu.SideNavigationView.Mode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class SlidingListMenuActivity extends Activity {
	
	
    public static final String EXTRA_TITLE = "Sliding List Menu";
    public static final String EXTRA_RESOURCE_ID = "Resource ID";
    public static final String EXTRA_MODE = "Mode";
    
    
	private SideNavigationView sideNavigationView;
	private TextView tvLabel;
	private Button btnSelect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sliding_list_menu);
        
        sideNavigationView = (SideNavigationView)findViewById(R.id.side_navigation_view);
        sideNavigationView.setMode(Mode.RIGHT);
        tvLabel = (TextView)findViewById(R.id.textView1);
        btnSelect = (Button)findViewById(R.id.button1);
        btnSelect.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				sideNavigationView.toggleMenu();
			}
		});
        
        
        if (getIntent().hasExtra(EXTRA_TITLE)) {
            String title = getIntent().getStringExtra(EXTRA_TITLE);
            tvLabel.setText(title);
            sideNavigationView.setMode(getIntent().getIntExtra(EXTRA_MODE, 0) == 0 ? Mode.LEFT : Mode.RIGHT);
        }
    }
    
    public void onSideNavigationItemClick(int itemId) {
        switch (itemId) {
            case R.id.side_navigation_menu_item1:
            	invokeActivity(getString(R.string.title1));
                break;

            case R.id.side_navigation_menu_item2:
            	invokeActivity(getString(R.string.title2));
                break;

            case R.id.side_navigation_menu_item3:
            	invokeActivity(getString(R.string.title3));
                break;

            case R.id.side_navigation_menu_item4:
            	invokeActivity(getString(R.string.title4));
                break;

            case R.id.side_navigation_menu_item5:
            	invokeActivity(getString(R.string.title5));
                break;

            default:
                return;
        }
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_sliding_list_menu, menu);
        return true;
    }
    
    @Override
    public void onBackPressed() {
        // hide menu if it shown
        if (sideNavigationView.isShown()) {
            sideNavigationView.hideMenu();
        } else {
            super.onBackPressed();
        }
    }
    
    private void invokeActivity(String title) {
        Intent intent = new Intent(this, SlidingListMenuActivity.class);
        intent.putExtra(EXTRA_TITLE, title);
//        intent.putExtra(EXTRA_RESOURCE_ID, resId);
        intent.putExtra(EXTRA_MODE, sideNavigationView.getMode() == Mode.LEFT ? 0 : 1);

        // all of the other activities on top of it will be closed and this
        // Intent will be delivered to the (now on top) old activity as a
        // new Intent.
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
        // no animation of transition
        overridePendingTransition(0, 0);
    }
}
