package top.goluck.realm_2017_2_8.ui;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import java.util.Random;

import top.goluck.realm_2017_2_8.R;
import top.goluck.realm_2017_2_8.base.ui.BaseActivity;
import top.goluck.realm_2017_2_8.models.db.User;
import top.goluck.realm_2017_2_8.util.TSUtil;

public class AddDataActivity extends BaseActivity {

    private EditText add_edit1;

    @Override
    protected int initConfigAndLayoutID() {
        setSupportRealm(true);
        isShowBack = true;
        RID = R.string.title_activity_add_data;
        return R.layout.activity_add_data;
    }

    @Override
    protected void initView() {
        add_edit1 = getView(R.id.add_edit1);
    }

    public void onClick(View view) {
        String userName = add_edit1.getText().toString();
        if(TextUtils.isEmpty(userName) || userName.trim().length()==0){
            TSUtil.showInfo(this,"请输入用户名",false);
            return;
        }
        User user=new User();
        user.setUserId(new Random().nextInt(Integer.MAX_VALUE));//这里设置的随机数，使用copyToRealm如果重复会异常
        user.setUserName(userName);
        user.setUserSex(true);
        user.setUserPwd(userName+new Random().nextInt(100000));
        switch (view.getId()) {
            case R.id.add_text1:
                 mDbUtils.copyToRealmOrUpdate(user);
                break;
            case R.id.add_text2:
                mDbUtils.copyToRealmOrUpdateByAsyn(user);
                break;
        }
        add_edit1.setText("");
        TSUtil.showInfo(this, "保存成功"+user.getUserName(), false);
    }
}
