package com.viewpagerindicator.as.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;

import com.viewpagerindicator.as.library.indicator.RecyclerCirclePageIndicator;
import com.viewpagerindicator.as.library.pageview.RecyclerViewPager;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SampleCirclesInitialPageRecycler extends BaseSampleActivity {

    @Bind(R.id.viewpager)
    RecyclerViewPager pager;
    @Bind(R.id.indicator)
    RecyclerCirclePageIndicator indicator;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.simple_circles_recycler);
        ButterKnife.bind(this);

        // config toolbar
        toolbar.setTitle(this.getTitle().subSequence(((String) this.getTitle()).indexOf('/') + 1, this.getTitle().length()));
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));

        FragmentsAdapter adapter = new FragmentsAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        pager.setLayoutManager(manager);
        pager.scrollToPosition(pager.getLayoutManager().getItemCount() - 1);

        indicator.setViewPager(pager);
        indicator.setFillColor(Color.parseColor("#FF33B5E5"));
    }
}