package top.goluck.realm_2017_2_8.base.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import top.goluck.realm_2017_2_8.db.DbUtils;

/**
 * 作者：luck on 2017/2/8 17:48
 * 邮箱：fc_dream@163.com
 * Realm_2017_2_8
 */
public abstract class BaseActivity extends AppCompatActivity {

    // 该Activity 是否支持realm数据库操作
    private boolean isSupportRealm = false;
    protected boolean isShowBack = false;
    protected int RID = 0;
    // realm 数据库操作对象
    protected DbUtils mDbUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initConfigAndLayoutID());
        onInitRealm();
        initActionBar();
        initView();
    }

    //用于初始化配置
    //及用于指定当前Activity对应的layout
    protected abstract int initConfigAndLayoutID();
    protected abstract void initView();

    private void initActionBar() {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle(RID);
            actionBar.setHomeButtonEnabled(isShowBack);
            actionBar.setDisplayHomeAsUpEnabled(isShowBack);
        }
    }

    //初始化Realm状态
    private void onInitRealm() {
        mDbUtils = null;
        if(isSupportRealm){
            mDbUtils = new DbUtils();
        }
    }

    //设置是否支持Realm
    public void setSupportRealm(boolean supportRealm) {
        isSupportRealm = supportRealm;
        onInitRealm();
    }
    protected <T extends View> T getView(int viewId) {
        return (T) findViewById(viewId);
    }

    //检查是否支持Realm
    private boolean onCheckIsSupportRealm(){
        return mDbUtils!=null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //如果支持Realm则关闭realm
        if (onCheckIsSupportRealm())
            mDbUtils.onClose();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void lazyLoad(){

    }

}
