package com.viewpagerindicator.as.sample;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;


// just for nothing
public abstract class BaseSampleActivity extends AppCompatActivity {
//    private static final Random RANDOM = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
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


    }

    public String configTitle () {
        return "";
    }


//        @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return true;
//    }
//
//     RecyclerViewPagerAdapter acquireAdapter () {
//         return null;
//     }
//     RecyclerViewPager acquireViewPager () {
//         return null;
//     }
//     RecyclerPageIndicator acquireIndicator () {
//         return null;
//     }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.random:
//                final int page = RANDOM.nextInt(acquireAdapter().getItemCount());
//                Toast.makeText(this, "Changing to page " + page, Toast.LENGTH_SHORT);
//                acquireViewPager ().scrollToPosition(page);
//                return true;
//
////            case R.id.add_page:
////                if (acquireAdapter().getItemCount() < 10) {
////                    acquireAdapter().setCount(acquireAdapter().getItemCount() + 1);
////                    acquireIndicator().notifyDataSetChanged();
////                }
////                return true;
////
////            case R.id.remove_page:
////                if (acquireAdapter().getItemCount() > 1) {
////                    acquireAdapter().set(acquireAdapter().getItemCount()- 1);
////                    acquireIndicator().notifyDataSetChanged();
////                }
////                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
