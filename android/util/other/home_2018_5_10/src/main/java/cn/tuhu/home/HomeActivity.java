package cn.tuhu.home;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cn.tuhu.home.adapter.HomeAdapter;
import cn.tuhu.home.adapter.LikeStaggeredGridAdapter;
import cn.tuhu.home.model.HomeModel;
import cn.tuhu.home.viewmodel.AdvertiseDataViewModel;

public class HomeActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView home_recycler;
    private HomeAdapter mHomeAdapter;
    private LikeStaggeredGridAdapter mFoundStaggeredGridAdapter;
    private List<DelegateAdapter.Adapter> adapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            int i = 0;

            @Override
            public void onRefresh() {
                i++;
                if (i % 2 == 0) {
                    initData();
                } else {
                    initData2();
                }
            }
        });
        home_recycler = findViewById(R.id.home_recycler);

        //设置回收复用池大小，（如果一屏内相同类型的 View 个数比较多，需要设置一个合适的大小，防止来回滚动时重新创建 View）
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        viewPool.setMaxRecycledViews(0, 20);
        home_recycler.setRecycledViewPool(viewPool);

        //创建VirtualLayoutManager对象
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        home_recycler.setLayoutManager(layoutManager);

        //构造 layoutHelper 列表
        List<LayoutHelper> helpers = new LinkedList<>();
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        linearLayoutHelper.setItemCount(4);
        helpers.add(linearLayoutHelper);


//        mHomeAdapter = new HomeAdapter(layoutManager,true);
//        home_recycler.setAdapter(mHomeAdapter);
        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, true);
        home_recycler.setAdapter(delegateAdapter);

        adapters = new ArrayList<>();
        mHomeAdapter = new HomeAdapter();
        mFoundStaggeredGridAdapter = new LikeStaggeredGridAdapter(this);
        adapters.add(mHomeAdapter);
        adapters.add(mFoundStaggeredGridAdapter);
        delegateAdapter.addAdapters(adapters);

        model = ViewModelProviders.of(this).get(AdvertiseDataViewModel.class);
        initData();
    }

    private AdvertiseDataViewModel model;

    private void initData() {
        model.setData();
        model.getData().getLists().observe(this, new Observer<List<HomeModel>>() {
            @Override
            public void onChanged(@Nullable List<HomeModel> homeModels) {
                mHomeAdapter.addDatas(homeModels);
                mHomeAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }


    private void initData2() {
        model.getLists2().observe(this, new Observer<List<HomeModel>>() {
            @Override
            public void onChanged(@Nullable List<HomeModel> homeModels) {
                mFoundStaggeredGridAdapter.addDatas(homeModels);
                mFoundStaggeredGridAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);

            }
        });
    }
}
