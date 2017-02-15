package top.goluck.realm_2017_2_8.ui.fragment;

import android.app.Activity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import top.goluck.realm_2017_2_8.R;
import top.goluck.realm_2017_2_8.base.ui.BaseActivity;
import top.goluck.realm_2017_2_8.base.ui.BaseFragmet;
import top.goluck.realm_2017_2_8.db.DbUtils;
import top.goluck.realm_2017_2_8.models.db.User;
import top.goluck.realm_2017_2_8.ui.adpter.UserAdpater;
import top.goluck.realm_2017_2_8.util.TSUtil;

/**
 * 作者：luck on 2017/2/10 16:39
 * 邮箱：fc_dream@163.com
 * Realm_2017_2_8
 */
public class ShowDataFragment extends BaseFragmet {

    private RecyclerView showdata_recycler;
    private UserAdpater mUserAdpater;
    private Activity mActivity;

    @Override
    protected int initConfigAndLayoutID() {
        setSupportRealm(true);
        return R.layout.fragment_showdata;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity=activity;
    }

    @Override
    protected void lazyLoad() {
        TSUtil.I("------------------------lazyLoad");
        if(mActivity instanceof BaseActivity){
            ((BaseActivity)mActivity).lazyLoad();
        }
    }

    @Override
    protected void initView() {
        showdata_recycler = getView(R.id.showdata_recycler);
    }

    //暂未做分页
    public void setData(List<User> datas, DbUtils mDbUtils){
        TSUtil.I("------------------------setData"+(datas==null)+"--------"+(mActivity==null));
        if (datas == null || mActivity==null) {
            return;
        }
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext) {
            @Override
            public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
                try {
                    super.onLayoutChildren(recycler, state);
                } catch (IndexOutOfBoundsException e) {
                }
            }
        };
        mLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        showdata_recycler.setLayoutManager(mLayoutManager);
        showdata_recycler.setItemAnimator(new DefaultItemAnimator());
        mUserAdpater = new UserAdpater(mDbUtils);
        mUserAdpater.setData(datas);
        showdata_recycler.setAdapter(mUserAdpater);
        mUserAdpater.notifyDataSetChanged();
    }

}
