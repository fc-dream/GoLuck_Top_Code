package top.goluck.realm_2017_2_8.ui.adpter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import top.goluck.realm_2017_2_8.db.DbUtils;
import top.goluck.realm_2017_2_8.models.db.User;
import top.goluck.realm_2017_2_8.ui.adpter.viewholder.UserDataViewHolder;
import top.goluck.realm_2017_2_8.util.TSUtil;

/**
 * 作者：luck on 2017/2/10 14:08
 * 邮箱：fc_dream@163.com
 * Realm_2017_2_8
 */
public class UserAdpater extends RecyclerView.Adapter {

    private List<User> datas;
    private DbUtils mDbUtils;

    public UserAdpater(DbUtils dbUtils){
        datas = new ArrayList<>();
        mDbUtils = dbUtils;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserDataViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof UserDataViewHolder){
            TSUtil.I("ddddd"+datas.get(position).getUserName());
            ((UserDataViewHolder) holder).bindDataandListener(datas.get(position),mDbUtils);
        }
    }

    @Override
    public int getItemCount() {
        TSUtil.I("getItemCount"+datas.size());
        return datas.size();
    }

    public void setData(List<User> data) {
        TSUtil.I("------------------------setData"+(data==null));
        if(data!=null){
            TSUtil.I("------------------------setData"+data.size());
            this.datas = data;
        }
    }
}
