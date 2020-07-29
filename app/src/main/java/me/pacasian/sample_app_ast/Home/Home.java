package me.pacasian.sample_app_ast.Home;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.readystatesoftware.viewbadger.BadgeView;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


import me.pacasian.sample_app_ast.R;

public class Home extends AppCompatActivity {
ImageButton imageButton;
Button btn;


    private static final String TAG = "Home";

    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Fragment(), "Offers");
        adapter.addFragment(new Tab2Fragment(), "Products");
        adapter.addFragment(new Tab3Fragment(), "Shops");
        mViewPager.setAdapter(adapter);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_local_offer_white_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_products_white_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_storefront_white_24dp);

    }
    private void setupViewPager(ViewPager viewPager) {

    }
}