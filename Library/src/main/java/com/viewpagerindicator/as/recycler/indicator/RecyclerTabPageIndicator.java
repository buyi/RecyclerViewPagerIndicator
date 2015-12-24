/*
 * Copyright (C) 2011 The Android Open Source Project
 * Copyright (C) 2011 Jake Wharton
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.viewpagerindicator.as.recycler.indicator;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.viewpagerindicator.as.R;
import com.viewpagerindicator.as.recycler.pageview.RecyclerViewPager;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * This widget implements the dynamic action bar tab behavior that can change
 * across different configurations or circumstances.
 */
public class RecyclerTabPageIndicator extends HorizontalScrollView implements RecyclerPageIndicator {
    /** Title text used when no title is provided by the adapter. */
    private static final CharSequence EMPTY_TITLE = "";

    /**
     * Interface for a callback when the selected tab has been reselected.
     */
    public interface OnTabReselectedListener {
        /**
         * Callback when the selected tab has been reselected.
         *
         * @param position Position of the current center item.
         */
        void onTabReselected(int position);
    }

    private Runnable mTabSelector;

    private final OnClickListener mTabClickListener = new OnClickListener() {
        public void onClick(View view) {
            TabView tabView = (TabView)view;
            final int oldSelected = wrapViewPager(mRecyclerView).getCurrentPosition();
            final int newSelected = tabView.getIndex();
            mRecyclerView.scrollToPosition(newSelected);
            if (oldSelected == newSelected && mTabReselectedListener != null) {
                mTabReselectedListener.onTabReselected(newSelected);
            }
        }
    };

    private final com.viewpagerindicator.as.recycler.indicator.IcsLinearLayout mTabLayout;

    private RecyclerView mRecyclerView;
    private ViewPager.OnPageChangeListener mListener;

    private int mMaxTabWidth;
    private int mSelectedTabIndex;

    private OnTabReselectedListener mTabReselectedListener;

    public RecyclerTabPageIndicator(Context context) {
        this(context, null);
    }

    public RecyclerTabPageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        setHorizontalScrollBarEnabled(false);

        mTabLayout = new com.viewpagerindicator.as.recycler.indicator.IcsLinearLayout(context, R.attr.vpiTabPageIndicatorStyle);
        addView(mTabLayout, new ViewGroup.LayoutParams(WRAP_CONTENT, MATCH_PARENT));
    }

    public void setOnTabReselectedListener(OnTabReselectedListener listener) {
        mTabReselectedListener = listener;
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        final boolean lockedExpanded = widthMode == MeasureSpec.EXACTLY;
        setFillViewport(lockedExpanded);

        final int childCount = mTabLayout.getChildCount();
        if (childCount > 1 && (widthMode == MeasureSpec.EXACTLY || widthMode == MeasureSpec.AT_MOST)) {
            if (childCount > 2) {
                mMaxTabWidth = (int)(MeasureSpec.getSize(widthMeasureSpec) * 0.4f);
            } else {
                mMaxTabWidth = MeasureSpec.getSize(widthMeasureSpec) / 2;
            }
        } else {
            mMaxTabWidth = -1;
        }

        final int oldWidth = getMeasuredWidth();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int newWidth = getMeasuredWidth();

        if (lockedExpanded && oldWidth != newWidth) {
            // Recenter the tab display if we're at a new (scrollable) size.
            setCurrentItem(mSelectedTabIndex);
        }
    }

    private void animateToTab(final int position) {
        final View tabView = mTabLayout.getChildAt(position);
        if (mTabSelector != null) {
            removeCallbacks(mTabSelector);
        }
        mTabSelector = new Runnable() {
            public void run() {
                final int scrollPos = tabView.getLeft() - (getWidth() - tabView.getWidth()) / 2;
                smoothScrollTo(scrollPos, 0);
                mTabSelector = null;
            }
        };
        post(mTabSelector);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mTabSelector != null) {
            // Re-post the selector we saved
            post(mTabSelector);
        }
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mTabSelector != null) {
            removeCallbacks(mTabSelector);
        }
    }

    private void addTab(int index, CharSequence text, int iconResId) {
        final TabView tabView = new TabView(getContext());
        tabView.mIndex = index;
        tabView.setFocusable(true);
        tabView.setOnClickListener(mTabClickListener);
        tabView.setText(text);

        if (iconResId != 0) {
            tabView.setCompoundDrawablesWithIntrinsicBounds(iconResId, 0, 0, 0);
        }

        mTabLayout.addView(tabView, new LinearLayout.LayoutParams(0, MATCH_PARENT, 1));
    }

//    @Override
//    public void onPageScrollStateChanged(int arg0) {
//        if (mListener != null) {
//            mListener.onPageScrollStateChanged(arg0);
//        }
//    }
//
//    @Override
//    public void onPageScrolled(int arg0, float arg1, int arg2) {
//        if (mListener != null) {
//            mListener.onPageScrolled(arg0, arg1, arg2);
//        }
//    }
//
//    @Override
//    public void onPageSelected(int arg0) {
//        setCurrentItem(arg0);
//        if (mListener != null) {
//            mListener.onPageSelected(arg0);
//        }
//    }

//    @Override
//    public void setViewPager(ViewPager view) {
//
//    }

    public void notifyDataSetChanged() {
        mTabLayout.removeAllViews();
//        PagerAdapter adapter = mRecyclerView.getAdapter();
//        IconPagerAdapter iconAdapter = null;
//        if (adapter instanceof IconPagerAdapter) {
//            iconAdapter = (IconPagerAdapter)adapter;
//        }
        final int count = mRecyclerView.getAdapter().getItemCount();
        for (int i = 0; i < count; i++) {
            IconPagerAdapter iconAdapter = ((IconPagerAdapter)mRecyclerView.getAdapter());
            CharSequence title = iconAdapter.getPageTitle(i);
            if (title == null) {
                title = EMPTY_TITLE;
            }
            int iconResId = 0;
            if (iconAdapter != null) {
                iconResId = iconAdapter.getIconResId(i);
            }
            addTab(i, title, iconResId);
        }
        if (mSelectedTabIndex > count) {
            mSelectedTabIndex = count - 1;
        }
        setCurrentItem(mSelectedTabIndex);
        requestLayout();
    }

//    @Override
//    public void setViewPager(ViewPager view, int initialPosition) {
//
//    }

    @Override
    public void setViewPager(RecyclerView view) {
        if (mRecyclerView == view) {
            return;
        }
        if (mRecyclerView != null) {
            wrapViewPager(mRecyclerView).clearOnPageChangedListeners();;
            wrapViewPager(mRecyclerView).clearOnScrollListeners();
        }


        if (view.getAdapter() == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
        mRecyclerView = view;
        wrapViewPager(mRecyclerView).addOnPageChangedListener(new RecyclerViewPager.OnPageChangedListener() {
            @Override
            public void OnPageChanged(int oldPosition, int newPosition) {
                setCurrentItem(newPosition);

                if (mListener != null) {
                    mListener.onPageSelected(newPosition);
                }
            }
        });

        wrapViewPager(mRecyclerView).addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (mListener != null) {
                    mListener.onPageScrollStateChanged(newState);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (mListener != null) {
                    mListener.onPageScrolled(wrapViewPager(mRecyclerView).getCurrentPosition(), 0f, dx);
                }
            }
        });
        notifyDataSetChanged();
    }

    @Override
    public void setViewPager(RecyclerView view, int initialPosition) {
        setViewPager(view);
        setCurrentItem(initialPosition);
    }

    @Override
    public void setCurrentItem(int item) {
        if (mRecyclerView == null) {
            throw new IllegalStateException("ViewPager has not been bound.");
        }
        mSelectedTabIndex = item;
//        mRecyclerView.scrollToPosition(item);//
        // .setCurrentItem(item);

        final int tabCount = mTabLayout.getChildCount();
        for (int i = 0; i < tabCount; i++) {
            final View child = mTabLayout.getChildAt(i);
            final boolean isSelected = (i == item);
            child.setSelected(isSelected);
            if (isSelected) {
                animateToTab(item);
            }
        }
    }

//    @Override
//    public void setOnPageChangeListener(OnPageChangeListener listener) {
//        mListener = listener;
//    }


    private class TabView extends TextView {
        private int mIndex;

        public TabView(Context context) {
            super(context, null, R.attr.vpiTabPageIndicatorStyle);
        }

        @Override
        public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);

            // Re-measure if we went beyond our maximum size.
            if (mMaxTabWidth > 0 && getMeasuredWidth() > mMaxTabWidth) {
                super.onMeasure(MeasureSpec.makeMeasureSpec(mMaxTabWidth, MeasureSpec.EXACTLY),
                        heightMeasureSpec);
            }
        }

        public int getIndex() {
            return mIndex;
        }
    }

    public void setmListener(ViewPager.OnPageChangeListener mListener) {
        this.mListener = mListener;
    }

    private RecyclerViewPager wrapViewPager ( RecyclerView view) {
        if (view  instanceof  RecyclerViewPager) {
            return (RecyclerViewPager) view;
        }
        throw  new IllegalArgumentException("this view is not RecyclerViewPager type");
    }
}
