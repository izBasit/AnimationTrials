package com.mobien.slidinglistmenu;

import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * View of displaying side navigation.
 * 
 * @author e.shishkin
 * 
 */
public class SideNavigationView extends LinearLayout {
    private static final String LOG_TAG = SideNavigationView.class.getSimpleName();

    private LinearLayout navigationMenu;
    private ListView listView;
    private View outsideView;

    private ISideNavigationCallback callback;
    private ArrayList<SideNavigationItem> menuItems;
    private Mode mMode = Mode.RIGHT;

    public static enum Mode {
        LEFT, RIGHT
    };

    /**
     * Constructor of {@link SideNavigationView}.
     * 
     * @param context
     */
    public SideNavigationView(Context context) {
        super(context);
        load();
    }

    /**
     * Constructor of {@link SideNavigationView}.
     * 
     * @param context
     * @param attrs
     */
    public SideNavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        load();
    }

    /**
     * Loading of side navigation view.
     */
    private void load() {
        if (isInEditMode()) {
            return;
        }
        initView();
    }

    /**
     * Initialization layout of side menu.
     */
    private void initView() {
        removeAllViews();
        int sideNavigationRes;
        switch (mMode) {
            case LEFT:
                sideNavigationRes = R.layout.side_navigation_left;
                break;
            case RIGHT:
                sideNavigationRes = R.layout.side_navigation_right;
                break;

            default:
                sideNavigationRes = R.layout.side_navigation_right;
                break;
        }
        LayoutInflater.from(getContext()).inflate(sideNavigationRes, this, true);
        navigationMenu = (LinearLayout) findViewById(R.id.side_navigation_menu);
        listView = (ListView) findViewById(R.id.side_navigation_listview);
        outsideView = (View) findViewById(R.id.side_navigation_outside_view);
        outsideView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                hideMenu();
            }
        });
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (callback != null) {
                    callback.onSideNavigationItemClick(menuItems.get(position).getId());
                }
                hideMenu();
            }
        });
    }

    /**
     * Setup of {@link ISideNavigationCallback} for callback of item click.
     * 
     * @param callback
     */
    public void setMenuClickCallback(ISideNavigationCallback callback) {
        this.callback = callback;
    }

    /**
     * Setup sliding mode of side menu ({@code Mode.LEFT} or {@code Mode.RIGHT}). {@code Mode.LEFT} by default.
     * 
     * @param mode Sliding mode
     */
    public void setMode(Mode mode) {
        if (isShown()) {
            hideMenu();
        }
        mMode = mode;
        initView();
        // setup menu items
        if (menuItems != null && menuItems.size() > 0) {
            listView.setAdapter(new SideNavigationAdapter(null, null));
        }
    }

    /**
     * Getting current side menu mode ({@code Mode.LEFT} or {@code Mode.RIGHT}). {@code Mode.LEFT} by default.
     * 
     * @return side menu mode
     */
    public Mode getMode() {
        return mMode;
    }

    /**
	 * 
	 */
    public void setBackgroundResource(int resource) {
        listView.setBackgroundResource(resource);
    }

    /**
     * Show side navigation menu.
     */
    public void showMenu() {
        outsideView.setVisibility(View.VISIBLE);
        outsideView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.side_navigation_fade_in));
        // show navigation menu with animation
        int animRes;
        switch (mMode) {
            case LEFT:
                animRes = R.anim.side_navigation_in_from_left;
                break;
            case RIGHT:
                animRes = R.anim.side_navigation_in_from_right;
                break;

            default:
                animRes = R.anim.side_navigation_in_from_left;
                break;
        }
        navigationMenu.setVisibility(View.VISIBLE);
        navigationMenu.startAnimation(AnimationUtils.loadAnimation(getContext(), animRes));
    }

    /**
     * Hide side navigation menu.
     */
    public void hideMenu() {
        outsideView.setVisibility(View.GONE);
        outsideView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.side_navigation_fade_out));
        // hide navigation menu with animation
        int animRes;
        switch (mMode) {
            case LEFT:
                animRes = R.anim.side_navigation_out_to_left;
                break;
            case RIGHT:
                animRes = R.anim.side_navigation_out_to_right;
                break;

            default:
                animRes = R.anim.side_navigation_out_to_left;
                break;
        }
        navigationMenu.setVisibility(View.GONE);
        navigationMenu.startAnimation(AnimationUtils.loadAnimation(getContext(), animRes));
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

    @Override
    public boolean isShown() {
        return navigationMenu.isShown();
    }

}
