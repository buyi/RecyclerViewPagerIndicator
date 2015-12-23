package com.viewpagerindicator.as.sample.recycler;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.viewpagerindicator.as.recycler.indicator.RecyclerCirclePageIndicator;
import com.viewpagerindicator.as.recycler.pageview.RecyclerViewPager;
import com.viewpagerindicator.as.sample.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SampleCirclesDefaultRecycler extends com.viewpagerindicator.as.sample.BaseSampleActivity {

    @Bind(R.id.viewpager) RecyclerViewPager pager;
    @Bind(R.id.indicator) RecyclerCirclePageIndicator indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_circles_recycler);

        ButterKnife.bind(this);


        FragmentsAdapter adapter = new FragmentsAdapter(getSupportFragmentManager());
//        RecyclerView.Adapter adapter = new TestFragmentAdapter(getSupportFragmentManager());

//        mPager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(adapter);

//        pager.setOnScrollListener(new RecyclerCirclePageIndicator.OnScrollListener());
//        pager.addOnPageChangedListener(new RecyclerViewPager.OnPageChangedListener() {
//            @Override
//            public void OnPageChanged(int oldPosition, int newPosition) {
//                            getSupportActionBar()
//                    .setTitle("from arguments:" + newPosition);
//            }
//        });

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation (LinearLayoutManager.HORIZONTAL);
        pager.setLayoutManager(manager);

//        mIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(pager);
    }


}