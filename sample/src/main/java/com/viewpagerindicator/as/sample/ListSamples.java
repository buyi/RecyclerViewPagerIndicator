package com.viewpagerindicator.as.sample;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//java.lang.IllegalStateException: This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.
public class ListSamples extends AppCompatActivity {

    private final String intentPath = "com.buyi.recyclerviewpager.path";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            // status change color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            // 对应   <item name="android:windowTranslucentStatus">true</item>
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        // config layout
        setContentView(R.layout.layout_sample_list);

        // config toolbar
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        System.out.println("title:" + getTitle());
        toolbar.setTitle(this.getTitle());

        // populate data
        Intent intent = getIntent();
        String path = intent.getStringExtra(intentPath);

        if (path == null) {
            path = "";
        }

        final ListView listView = (ListView) findViewById(android.R.id.list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> map = (Map<String, Object>) listView.getAdapter().getItem(position);

                Intent intent = (Intent) map.get("intent");
                startActivity(intent);
            }

//            @Override
//            public void onClick(View v) {
//
//            }
        });
        listView.setAdapter(new SimpleAdapter(this, getData(path),
                android.R.layout.simple_list_item_1, new String[]{"title"},
                new int[]{android.R.id.text1}));




        // how to acquire system attribute
//        final TypedArray styledAttributes1 = getTheme().obtainStyledAttributes(
//                new int[]{android.R.attr.colorPrimary});
//        int  color = styledAttributes1.getColor(0, 0xFFFF00);
//        System.out.println("color = " + color);


        // because there is mearsure time
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // calculate status bar's height
                Rect frame = new Rect();
                getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
                int statusBarHeight = frame.top;
                System.out.println("statusBarHeight1 = " + statusBarHeight);

                // caclute toolbar's height (1)
                Rect rect =  new Rect();
                toolbar.getGlobalVisibleRect(rect);
                System.out.println("top:" + rect.top);
                System.out.println("bottom:" + rect.bottom);
                System.out.println("left:" + rect.left);
                System.out.println("right:" + rect.right);

                // caclute toolbar's height (2)
                System.out.println("mActionBarSize2 = " + getSupportActionBar().getHeight());
                View headerView = new View(ListSamples.this);
                AbsListView.LayoutParams params = new AbsListView.LayoutParams(AbsListView.LayoutParams.FILL_PARENT, getSupportActionBar().getHeight());
                headerView.setLayoutParams(params);
//                headerView.setBackgroundColor(Color.parseColor("#77ff0000"));
                listView.addHeaderView(headerView);
            }
        }, 50);

        // caclute toolbar's height (3)
//        final TypedArray styledAttributes = getTheme().obtainStyledAttributes(
//                new int[]{android.R.attr.actionBarSize});
//        int  mActionBarSize = (int) styledAttributes.getDimension(0, 0);
//        styledAttributes.recycle();

//        setStatusBarColor(findViewById(R.id.space), Color.parseColor("#7700ff00"));
    }

    // only work for >=19
    public void setStatusBarColor(View statusBar,int color){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //status bar height
            int actionBarHeight = getActionBarHeight();
            int statusBarHeight = getStatusBarHeight();
            System.out.println("actionBarHeight:" + actionBarHeight);
            System.out.println("statusBarHeight:" + statusBarHeight);
            //action bar height
            statusBar.getLayoutParams().height = statusBarHeight;
            statusBar.setBackgroundColor(color);
        }
    }

    public int getActionBarHeight() {
        int actionBarHeight = 0;
        TypedValue tv = new TypedValue();
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
        {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        }
        return actionBarHeight;
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    protected List<Map<String, Object>> getData(String prefix) {
        List<Map<String, Object>> myData = new ArrayList<Map<String, Object>>();

        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory("com.jakewharton.android.viewpagerindicator.sample.SAMPLE");

        PackageManager pm = getPackageManager();
        List<ResolveInfo> list = pm.queryIntentActivities(mainIntent, 0);

        if (null == list)
            return myData;

        String[] prefixPath;
        String prefixWithSlash = prefix;

        if (prefix.equals("")) {
            prefixPath = null;
        } else {
            prefixPath = prefix.split("/");
            prefixWithSlash = prefix + "/";
        }

        int len = list.size();

        Map<String, Boolean> entries = new HashMap<String, Boolean>();

        for (int i = 0; i < len; i++) {
            ResolveInfo info = list.get(i);
            CharSequence labelSeq = info.loadLabel(pm);
            String label = labelSeq != null
                    ? labelSeq.toString()
                    : info.activityInfo.name;

            if (prefixWithSlash.length() == 0 || label.startsWith(prefixWithSlash)) {

                String[] labelPath = label.split("/");

                String nextLabel = prefixPath == null ? labelPath[0] : labelPath[prefixPath.length];

                if ((prefixPath != null ? prefixPath.length : 0) == labelPath.length - 1) {
                    addItem(myData, nextLabel, activityIntent(
                            info.activityInfo.applicationInfo.packageName,
                            info.activityInfo.name));
                } else {
                    if (entries.get(nextLabel) == null) {
                        addItem(myData, nextLabel, browseIntent(prefix.equals("") ? nextLabel : prefix + "/" + nextLabel));
                        entries.put(nextLabel, true);
                    }
                }
            }
        }

        Collections.sort(myData, NAME_COMPARATOR);

        return myData;
    }

    private final static Comparator<Map<String, Object>> NAME_COMPARATOR =
        new Comparator<Map<String, Object>>() {
        private final Collator   collator = Collator.getInstance();

        public int compare(Map<String, Object> map1, Map<String, Object> map2) {
            return collator.compare(map1.get("title"), map2.get("title"));
        }
    };

    protected Intent activityIntent(String pkg, String componentName) {
        Intent result = new Intent();
        result.setClassName(pkg, componentName);
        return result;
    }

    private List<Map<String, Object>> getFakeData() {

        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        for (int i = 0 ; i < 100; i++) {
            addItem(data, i + "", browseIntent ( i + ""));
        }
       return data;

    }

    protected Intent browseIntent(String path) {
        Intent result = new Intent();
        result.setClass(this, ListSamples.class);
        result.putExtra(intentPath, path);
        return result;
    }

    protected void addItem(List<Map<String, Object>> data, String name, Intent intent) {
        Map<String, Object> temp = new HashMap<String, Object>();
        temp.put("title", name);
        temp.put("intent", intent);
        data.add(temp);
    }
}