package com.viewpagerindicator.as.sample.recycler;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.LinearLayoutManager;

import com.viewpagerindicator.as.recycler.indicator.RecyclerTabPageIndicator;
import com.viewpagerindicator.as.recycler.pageview.RecyclerViewPager;
import com.viewpagerindicator.as.sample.R;
import com.viewpagerindicator.as.sample.TestFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SampleTabsDefaultRecycler extends FragmentActivity {
    private static final String[] CONTENT = new String[] { "Recent", "Artists", "Albums", "Songs", "Playlists", "Genres" };



    @Bind(R.id.viewpager)
    RecyclerViewPager pager;
    @Bind(R.id.indicator)
    RecyclerTabPageIndicator indicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_tabs_recycler);
        ButterKnife.bind(this);

        FragmentsAdapter adapter = new FragmentsAdapter(getSupportFragmentManager());

//        FragmentPagerAdapter adapter = new GoogleMusicAdapter(getSupportFragmentManager());

//        ViewPager pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation (LinearLayoutManager.HORIZONTAL);
        pager.setLayoutManager(manager);

//        TabPageIndicator indicator = (TabPageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(pager);
    }

    class GoogleMusicAdapter extends FragmentPagerAdapter {
        public GoogleMusicAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return TestFragment.newInstance(CONTENT[position % CONTENT.length]);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return CONTENT[position % CONTENT.length].toUpperCase();
        }

        @Override
        public int getCount() {
          return CONTENT.length;
        }
    }
}
