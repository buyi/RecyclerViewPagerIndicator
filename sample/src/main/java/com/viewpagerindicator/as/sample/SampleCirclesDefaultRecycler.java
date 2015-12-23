package com.viewpagerindicator.as.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.viewpagerindicator.as.recycler.CheeseListFragment;
import com.viewpagerindicator.as.recycler.FragmentStatePagerAdapter;
import com.viewpagerindicator.as.recycler.RecyclerCirclePageIndicator;
import com.viewpagerindicator.as.recycler.RecyclerViewPager;

import java.util.LinkedHashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SampleCirclesDefaultRecycler extends BaseSampleActivity {

    @Bind(R.id.viewpager) RecyclerViewPager pager;
    @Bind(R.id.indicator)
    RecyclerCirclePageIndicator indicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_circles_recycler);

        ButterKnife.bind(this);


        FragmentsAdapter adapter = new FragmentsAdapter(getSupportFragmentManager());

//        mPager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(adapter);

        pager.setOnScrollListener(new RecyclerCirclePageIndicator.OnScrollListener());
        pager.addOnPageChangedListener(new RecyclerViewPager.OnPageChangedListener() {
            @Override
            public void OnPageChanged(int oldPosition, int newPosition) {
                            getSupportActionBar()
                    .setTitle("from arguments:" + newPosition);
            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation (LinearLayoutManager.HORIZONTAL);
        pager.setLayoutManager(manager);

//        mIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(pager);
    }

    class FragmentsAdapter extends FragmentStatePagerAdapter /*implements TabLayoutSupport.ViewPagerTabLayoutAdapter*/ {
        LinkedHashMap<Integer, Fragment> mFragmentCache = new LinkedHashMap<>();

        public FragmentsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position, Fragment.SavedState savedState) {
            Fragment f = mFragmentCache.containsKey(position) ? mFragmentCache.get(position)
                    : new CheeseListFragment();
            Log.e("test", "getItem:" + position + " from cache" + mFragmentCache.containsKey
                    (position));
            if (savedState == null || f.getArguments() == null) {
                Bundle bundle = new Bundle();
                bundle.putInt("index", position);
                f.setArguments(bundle);
                Log.e("test", "setArguments:" + position);
            } else if (mFragmentCache.containsKey(position)) {
                f.setInitialSavedState(savedState);
                Log.e("test", "setInitialSavedState:" + position);
            }
            mFragmentCache.put(position, f);
            return f;
        }

        @Override
        public void onDestroyItem(int position, Fragment fragment) {
            // onDestroyItem
            while (mFragmentCache.size() > 5) {
                Object[] keys = mFragmentCache.keySet().toArray();
                mFragmentCache.remove(keys[0]);
            }
        }

//        @Override
//        public String getPageTitle(int position) {
//            return "item-" + position;
//        }
//
        @Override
        public int getItemCount() {
            return 10;
        }
    }
}