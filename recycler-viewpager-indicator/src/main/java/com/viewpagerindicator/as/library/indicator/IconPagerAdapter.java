package com.viewpagerindicator.as.library.indicator;

public interface IconPagerAdapter {
    /**
     * Get icon representing the page at {@code index} in the adapter.
     */
    int getIconResId(int index);

    // From PagerAdapter
    int getCount();

    CharSequence getPageTitle (int index);
}
