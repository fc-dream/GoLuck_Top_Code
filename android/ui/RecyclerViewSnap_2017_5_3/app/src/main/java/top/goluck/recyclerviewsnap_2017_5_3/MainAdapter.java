package top.goluck.recyclerviewsnap_2017_5_3;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import top.goluck.recyclerviewsnap_2017_5_3.model.Item;
import top.goluck.recyclerviewsnap_2017_5_3.viewholder.MainViewHolder;

/**
 * 作者：luck on 2017/5/1 13:09
 * 邮箱：fc_dream@163.com
 * DiffUtil_2017_5_1
 */
public class MainAdapter extends RecyclerView.Adapter {

    private List<Item> mDatas;

    public MainAdapter(){
        mDatas = new ArrayList<>();
    }

    public void addData(List<Item> datas){
        this.mDatas.addAll(datas);
    }

    public List<Item> getDatas() {
        return mDatas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       return new MainViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
        MainViewHolder holder1 = (MainViewHolder) holder;
        holder1.bindDataandListener(mDatas.get(position),payloads);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

}
