package top.goluck.recyclerviewsnap_2017_5_3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import top.goluck.recyclerviewsnap_2017_5_3.model.Item;
import top.goluck.recyclerviewsnap_2017_5_3.snap.GravitySnapHelper;

import static top.goluck.recyclerviewsnap_2017_5_3.MainDecoration.HORIZONTAL_LIST;
import static top.goluck.recyclerviewsnap_2017_5_3.MainDecoration.VERTICAL_LIST;

public class MainActivity extends AppCompatActivity implements GravitySnapHelper.SnapListener {

    private RecyclerView main_recyclerview;
    private MainAdapter mMainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        main_recyclerview = (RecyclerView) findViewById(R.id.main_recyclerview);
        mMainAdapter = new MainAdapter();
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        main_recyclerview.setLayoutManager(mLinearLayoutManager);
        main_recyclerview.addItemDecoration(new MainDecoration(this, VERTICAL_LIST));
        main_recyclerview.setAdapter(mMainAdapter);

        getData();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //  PagerSnapHelper 实现ViewPager效果
    //  GravitySnapHelper 实现top bottom left right 对齐
    //  LinearSnapHelper 实现 center 对齐，比PagerSnapHelper多可快速滑动
    private SnapHelper mSnapHelper;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.horizontal_left:
                main_recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                main_recyclerview.setOnFlingListener(null);
                mSnapHelper = new GravitySnapHelper(Gravity.START, false, this);
                break;
            case R.id.horizontal_center:
                main_recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                main_recyclerview.setOnFlingListener(null);
                mSnapHelper = new LinearSnapHelper();
                break;
            case R.id.horizontal_right:
                main_recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                main_recyclerview.setOnFlingListener(null);
                mSnapHelper = new GravitySnapHelper(Gravity.END, false, this);
                break;
            case R.id.vertical_top:
                main_recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                main_recyclerview.setOnFlingListener(null);
                mSnapHelper = new GravitySnapHelper(Gravity.TOP, false, this);
                break;
            case R.id.vertical_center:
                main_recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                main_recyclerview.setOnFlingListener(null);
                mSnapHelper = new LinearSnapHelper();
                break;
            case R.id.vertical_bottom:
                main_recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                main_recyclerview.setOnFlingListener(null);
                mSnapHelper = new GravitySnapHelper(Gravity.BOTTOM, true, this);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        if (mSnapHelper != null) mSnapHelper.attachToRecyclerView(main_recyclerview);
        return true;
    }

    private Toast mToast;

    public void show(String msg) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        mToast.show();
    }

    public void getData() {
        mMainAdapter.addData(getRandomData());
        mMainAdapter.notifyDataSetChanged();
    }


    public List<Item> getRandomData() {
        List<Item> datas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            datas.add(new Item(i, "我是随机标题" + i, "我是随机描述" + i, "我是随机footer" + i));
        }
        return datas;
    }

    @Override
    public void onSnap(int position) {
        Log.d("Snapped: ", position + "");
    }
}
