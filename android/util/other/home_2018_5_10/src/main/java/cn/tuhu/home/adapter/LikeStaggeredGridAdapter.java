package cn.tuhu.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper;

import java.util.ArrayList;
import java.util.List;

import cn.tuhu.home.R;
import cn.tuhu.home.model.HomeModel;
import cn.tuhu.home.view.ScreenUtil;
import cn.tuhu.home.viewholder.GroupBuyViewHolder;
import cn.tuhu.home.viewholder.LikeBannerViewHolder;
import cn.tuhu.home.viewholder.LikeCommodityViewHolder;
import cn.tuhu.home.viewholder.LikeQAViewHolder;

/**
 * 作者：luck on 2018/4/26 19:20
 * 邮箱：fc_dream@163.com
 * Tuhu_Android
 */
public class LikeStaggeredGridAdapter extends DelegateAdapter.Adapter<RecyclerView.ViewHolder>{

    private List<HomeModel> datas = new ArrayList<>();
    public void addDatas(List<HomeModel> datas){
        this.datas.addAll(datas);
    }

    private Context context;
    public LikeStaggeredGridAdapter(Context context){
        this.context = context;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new StaggeredGridLayoutHelper(2, ScreenUtil.dip2px(context,5));
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder=null;
        if(viewType==3){
            viewHolder = new LikeCommodityViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_like_commodity, parent, false));
        }else if(viewType==4){
            viewHolder = new LikeQAViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_like_qa, parent, false));
        }else if(viewType==5){
            viewHolder = new LikeBannerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_like_banner, parent, false));
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if(viewHolder instanceof GroupBuyViewHolder){
            GroupBuyViewHolder viewHolder1 = (GroupBuyViewHolder) viewHolder;
            ((TextView)viewHolder1.itemView).setText(datas.get(position).data);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return datas.get(position).type;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}
