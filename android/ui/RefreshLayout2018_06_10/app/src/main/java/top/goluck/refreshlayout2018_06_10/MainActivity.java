package top.goluck.refreshlayout2018_06_10;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class MainActivity extends AppCompatActivity {

    private HappyDrawable gifview;
    private SmartRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gifview = findViewById(R.id.gifview);
        refreshLayout = findViewById(R.id.refreshLayout);
        refreshLayout.autoRefresh();
        finishRefresh();
    }

    private Handler mHandler;

    private void finishRefresh() {
        if (mHandler == null) {
            mHandler = new Handler();
        }
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.finishRefresh();
            }
        }, 5000);
    }

}
