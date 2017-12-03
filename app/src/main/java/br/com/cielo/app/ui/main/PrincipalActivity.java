package br.com.cielo.app.ui.main;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.Profile;
import com.facebook.login.widget.ProfilePictureView;

import br.com.cielo.app.R;
import br.com.cielo.app.ui.base.BaseActivity;
import butterknife.ButterKnife;

public class PrincipalActivity extends BaseActivity {

    private static final String EXTRA_TRIGGER_SYNC_FLAG =
            "br.com.cielo.app.ui.main.MainActivity.EXTRA_TRIGGER_SYNC_FLAG";

    FragmentPagerAdapter adapterViewPager;

    Context context;

    CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this.getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_bar);
        View view =getSupportActionBar().getCustomView();

        ButterKnife.bind(this);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {
            }

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Code goes here
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {
                // Code goes here
            }
        });

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        LinearLayout linearLayout = (LinearLayout)tabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.parseColor("#ff33b5e5"));
        drawable.setSize(1, 1);
        linearLayout.setDividerPadding(10);
        linearLayout.setDividerDrawable(drawable);


        ProfilePictureView profilePictureView;

        profilePictureView = (ProfilePictureView) findViewById(R.id.action_bar_back);

        profilePictureView.setProfileId(AccessToken.getCurrentAccessToken().getUserId());
        Profile profile = Profile.getCurrentProfile();
        if(profile!=null)
            ((TextView)findViewById(R.id.store_name)).setText(profile.getName());


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 2;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return VendasFragment.newInstance(0, "Vendas");
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return AnalyticsFragment.newInstance(1, "Analytics");
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return "Vendas";
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return "Analytics";
            default:
                return null;
        }
        }

    }




}
