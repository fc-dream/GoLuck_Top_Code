package top.goluck.realm_2017_2_8.base.ui;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import top.goluck.realm_2017_2_8.db.DbUtils;
import top.goluck.realm_2017_2_8.util.TSUtil;

/**
 * 作者：luck on 2017/2/8 17:50
 * 邮箱：fc_dream@163.com
 * Realm_2017_2_8
 */
public abstract class BaseFragmet extends Fragment {

    // 该Activity 是否支持realm数据库操作
    private boolean isSupportRealm = false;
    // realm 数据库操作对象
    protected DbUtils mDbUtils;

    /** Fragment当前状态是否可见 */
    protected boolean isVisible;
    /** Fragment当前状态是否创建 */
    protected Boolean isCreated=false;
    protected Context mContext;
    protected View mView;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mContext!=null){
            mContext=null;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView=inflater.inflate(initConfigAndLayoutID(), container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onInitRealm();
        initView();
        TSUtil.I("------------------------onViewCreated");
        isCreated=true;
//        if(isVisible) {
            lazyLoad();
//        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        TSUtil.I("------------------------onHiddenChanged"+hidden);
        if (hidden) {
            isVisible = true;
            if(isCreated) {
                lazyLoad();
            }
        } else {
            isVisible = false;
        }
        super.onHiddenChanged(hidden);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        TSUtil.I("------------------------setUserVisibleHint"+isVisibleToUser);
        if (isVisibleToUser) {
            isVisible = true;
            if(isCreated) {
                lazyLoad();
            }
        } else {
            isVisible = false;
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    /**
     * 延迟加载 子类必须重写此方法 可见
     */
    protected abstract void lazyLoad();

    //用于初始化配置
    //及用于指定当前Activity对应的layout
    protected abstract int initConfigAndLayoutID();
    protected abstract void initView();

    //初始化Realm状态
    private void onInitRealm() {
        mDbUtils = null;
        if(isSupportRealm){
            mDbUtils = new DbUtils();
        }
    }

    protected <T extends View> T getView(int viewId) {
        if(mView==null){
            return null;
        }
        return (T) (mView.findViewById(viewId));
    }

    //设置是否支持Realm
    public void setSupportRealm(boolean supportRealm) {
        isSupportRealm = supportRealm;
        onInitRealm();
    }

    //检查是否支持Realm
    private boolean onCheckIsSupportRealm(){
        return mDbUtils!=null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //如果支持Realm则关闭realm
        if(onCheckIsSupportRealm())
            mDbUtils.onClose();
    }

}
