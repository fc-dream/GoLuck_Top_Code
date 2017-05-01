package top.goluck.diffutil_2017_5_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import top.goluck.diffutil_2017_5_1.adapter.MainAdapter;
import top.goluck.diffutil_2017_5_1.adapter.MainDecoration;
import top.goluck.diffutil_2017_5_1.adapter.callback.MainDiffCallback;
import top.goluck.diffutil_2017_5_1.model.Item;

import static top.goluck.diffutil_2017_5_1.adapter.MainDecoration.VERTICAL_LIST;

/**
 * 作者：luck on 2017/5/1 13:01
 * 邮箱：fc_dream@163.com
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button main_btn;
    private RecyclerView main_recyclerview;
    private MainAdapter mMainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_btn = (Button) findViewById(R.id.main_btn);
        main_btn.setOnClickListener(this);
        main_recyclerview = (RecyclerView) findViewById(R.id.main_recyclerview);
        mMainAdapter = new MainAdapter();
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        main_recyclerview.setLayoutManager(mLinearLayoutManager);
        main_recyclerview.addItemDecoration(new MainDecoration(this,VERTICAL_LIST));
        main_recyclerview.setAdapter(mMainAdapter);
        getData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_btn:
                // 关键代码
                // 这里主要数据比较小
                // 数据比较稍微比较多，需要异步执行
                // TODO: 2017/5/1
                DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MainDiffCallback(mMainAdapter.getDatas(), getRandomData(true)));
                diffResult.dispatchUpdatesTo(mMainAdapter);
                break;
        }
    }


    public void getData() {
        mMainAdapter.addData(getRandomData(false));
        mMainAdapter.notifyDataSetChanged();
    }


    public List<Item> getRandomData(boolean isRandom) {
        List<Item> datas = new ArrayList<>();
        if (isRandom) {
            int max = new Random().nextInt() % 50;
            int max_count = new Random().nextInt() % 50 + 50;
            for (int i = 0; i < max_count; i++) {
                int count = mMainAdapter.getDatas().size();
                Item data = new Item(i, i%3==0 && i<mMainAdapter.getDatas().size()?mMainAdapter.getDatas().get(i).getTitle():"我是修改后的随机标题" + (new Random().nextInt() % 1000), i%2==0 && i<mMainAdapter.getDatas().size()?mMainAdapter.getDatas().get(i).getContent():"我是修改后的随机描述" + (new Random().nextInt() % 1000000), i%5==0 && i<mMainAdapter.getDatas().size()?mMainAdapter.getDatas().get(i).getFooter():"我是修改后的随机footer" + (new Random().nextInt() % 1000000000));
                if (i < count && i < max) {
                    data = mMainAdapter.getDatas().get(i);
                }
                datas.add(data);

            }
        } else {
            for (int i = 0; i < 50; i++) {
                datas.add(new Item(i, "我是随机标题" + i, "我是随机描述" + i, "我是随机footer" + i));
            }
        }
        return datas;
    }
}
