package top.goluck.realm_2017_2_8.ui;

import java.util.List;

import top.goluck.realm_2017_2_8.R;
import top.goluck.realm_2017_2_8.base.ui.BaseActivity;
import top.goluck.realm_2017_2_8.models.db.User;
import top.goluck.realm_2017_2_8.ui.fragment.ShowDataFragment;
import top.goluck.realm_2017_2_8.util.TSUtil;

public class DelDataActivity extends BaseActivity {

    private ShowDataFragment activity_select_data_showdatafragment;

    @Override
    protected int initConfigAndLayoutID() {
        setSupportRealm(true);
        isShowBack = true;
        RID = R.string.title_activity_del_data;
        return R.layout.activity_del_data;
    }

    @Override
    protected void initView() {

        activity_select_data_showdatafragment = (ShowDataFragment) getFragmentManager().findFragmentById(R.id.activity_select_data_showdatafragment);
        List<User> datas = mDbUtils.queryAllT(User.class);
        TSUtil.I(datas!=null?""+datas.size():"0");
        activity_select_data_showdatafragment.setData(datas,mDbUtils);
    }

}
