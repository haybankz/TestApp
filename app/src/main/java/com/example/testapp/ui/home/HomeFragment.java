package com.example.testapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.testapp.R;
import com.example.testapp.adapter.HomePagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    private ViewPager viewPager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        //instantiate views
        TabLayout tabLayout = root.findViewById(R.id.tab_layout);
        viewPager = root.findViewById(R.id.pager);

        setUpViewPager();
        tabLayout.setupWithViewPager(viewPager);

        return root;
    }

    private void setUpViewPager() {
        //add fragments to view pager adapter
        HomePagerAdapter adapter = new HomePagerAdapter(getChildFragmentManager());
        adapter.addFragment(NowPlayingFragment.newInstance(), getString(R.string.tab_now_playing));
        adapter.addFragment(UpcomingFragment.newInstance(), getString(R.string.tab_upcoming));


        //set adapter to view pager
        viewPager.setAdapter(adapter);
    }
}
