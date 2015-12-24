package com.viewpagerindicator.as.sample.recycler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


// just for nothing
public abstract class BaseSampleActivity extends AppCompatActivity {
//    private static final Random RANDOM = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
