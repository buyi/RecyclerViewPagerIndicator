package com.viewpagerindicator.as.sample;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.viewpagerindicator.as.library.indicator.RecyclerTabPageIndicator;
import com.viewpagerindicator.as.library.pageview.RecyclerViewPager;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SampleTabsDefaultRecycler extends BaseSampleActivity {
//    private static final String[] CONTENT = new String[]{"Recent", "Artists", "Albums", "Songs", "Playlists", "Genres"};


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
        pager.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        pager.setLayoutManager(manager);

        indicator.setViewPager(pager);
    }
//
//    class GoogleMusicAdapter extends FragmentPagerAdapter {
//        public GoogleMusicAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return TestFragment.newInstance(CONTENT[position % CONTENT.length]);
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return CONTENT[position % CONTENT.length].toUpperCase();
//        }
//
//        @Override
//        public int getCount() {
//            return CONTENT.length;
//        }
//    }
}
