package com.viewpagerindicator.as.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.viewpagerindicator.as.library.indicator.IconPagerAdapter;
import com.viewpagerindicator.as.library.pageview.CheeseListFragment;
import com.viewpagerindicator.as.library.pageview.FragmentStatePagerAdapter;
import com.viewpagerindicator.as.sample.R;

import java.util.LinkedHashMap;

/**
 * Created by buyi on 15/12/23.
 */
class FragmentsAdapter extends FragmentStatePagerAdapter implements IconPagerAdapter {
    LinkedHashMap<Integer, Fragment> mFragmentCache = new LinkedHashMap<>();

    protected static final int[] ICONS = new int[] {
            R.drawable.perm_group_calendar,
            R.drawable.perm_group_camera,
            R.drawable.perm_group_device_alarms,
            R.drawable.perm_group_location
    };

    public FragmentsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position, Fragment.SavedState savedState) {
        Fragment f = mFragmentCache.containsKey(position) ? mFragmentCache.get(position)
                : new CheeseListFragment();
//        Log.e("test", "getItem:" + position + " from cache" + mFragmentCache.containsKey
//                (position));
        if (savedState == null || f.getArguments() == null) {
            Bundle bundle = new Bundle();
            bundle.putInt("index", position);
            f.setArguments(bundle);
//            Log.e("test", "setArguments:" + position);
        } else if (mFragmentCache.containsKey(position)) {
            f.setInitialSavedState(savedState);
//            Log.e("test", "setInitialSavedState:" + position);
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

    @Override
    public String getPageTitle(int position) {
        return "item-" + position;
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public int getIconResId(int index) {
        return ICONS[index % ICONS.length];
    }

    @Override
    public int getCount() {
        return 10;
    }
}
