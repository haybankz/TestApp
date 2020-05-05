package com.example.testapp.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.testapp.Constant;
import com.example.testapp.R;
import com.example.testapp.ui.home.HomeFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SharedPreferences.OnSharedPreferenceChangeListener {



    private final String TAG = getClass().getSimpleName();
    private SharedPreferences sharedPref;

    private TextView drawerNameTextView;
    private TextView drawerEmailTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        LinearLayout header = (LinearLayout) navigationView.getHeaderView(0);
        drawerNameTextView = header.findViewById(R.id.textview_name);
        drawerEmailTextView = header.findViewById(R.id.textView_email);



        MenuItem menuItem = navigationView.getMenu().getItem(0);
        selectedMenuItem(menuItem);

        sharedPref = getPreferences(Context.MODE_PRIVATE);
        sharedPref.registerOnSharedPreferenceChangeListener(this);

        getData();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {



            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.


            selectedMenuItem(item);

            setTitle(item.getTitle());



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




    public void selectedMenuItem(MenuItem item) {
        int itemId = item.getItemId();
        Fragment fragment;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        FragmentManager fm = getSupportFragmentManager();


        List<Fragment> fragmentList = fm.getFragments();
        if (fragmentList.size() > 0) {
            for (Fragment f : fragmentList) {
                transaction.hide(f);
            }
        }

        switch (itemId) {
            case R.id.nav_home:

                fragment = fm.findFragmentByTag("home_fragment");
                if (fragment == null) {
                    fragment = HomeFragment.newInstance();
                    transaction.add(R.id.frame_layout_content, fragment, "home_fragment");
                    transaction.commit();

                } else {
                    transaction.show(fragment);
                    transaction.commit();
                    fm.popBackStack();
                }

                break;

            case R.id.nav_favourites:
                fragment = fm.findFragmentByTag("favourite_fragment");
                if (fragment == null) {
                    fragment = FavouriteFragment.newInstance();
                    transaction.add(R.id.frame_layout_content, fragment, "favourite_fragment");
                    transaction.commit();
                } else {
                    transaction.show(fragment);
                    transaction.commit();
                    fm.popBackStack();
                }
                break;

            case R.id.nav_profile:

                fragment = fm.findFragmentByTag("profile_fragment");
                if (fragment == null) {
                    fragment = ProfileFragment.newInstance();
                    transaction.add(R.id.frame_layout_content, fragment, "profile_fragment");

                    transaction.commit();
                } else {
                    transaction.show(fragment);
                    transaction.commit();
                    fm.popBackStack();
                }
                break;


        }

        setTitle(item.getTitle());
        item.setChecked(true);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sharedPref.unregisterOnSharedPreferenceChangeListener(this);
    }

    private void getData(){
        String name = sharedPref.getString(Constant.NAME, Constant.MY_NAME);
        drawerNameTextView.setText(name);

        String email = sharedPref.getString(Constant.EMAIL, Constant.MY_EMAIL);
        drawerEmailTextView.setText(email);
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
                                          String key) {

        String value = sharedPreferences.getString(key, getString(R.string.unknown_key));
        if(key.equals(Constant.NAME))
            drawerNameTextView.setText(value);
        else if(key.equals(Constant.EMAIL))
            drawerEmailTextView.setText(value);


//        Log.d(TAG, "onSharedPreferenceChanged: key "+key +" : value "+value);
    }


}
