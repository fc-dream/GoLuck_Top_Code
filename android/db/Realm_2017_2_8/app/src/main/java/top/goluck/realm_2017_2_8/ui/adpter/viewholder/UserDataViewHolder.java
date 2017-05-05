package top.goluck.realm_2017_2_8.ui.adpter.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import top.goluck.realm_2017_2_8.R;
import top.goluck.realm_2017_2_8.base.ui.BaseViewHolder;
import top.goluck.realm_2017_2_8.db.DbUtils;
import top.goluck.realm_2017_2_8.models.db.User;
import top.goluck.realm_2017_2_8.util.TSUtil;

/**
 * 作者：luck on 2017/2/10 14:09
 * 邮箱：fc_dream@163.com
 * Realm_2017_2_8
 */
public class UserDataViewHolder extends BaseViewHolder {

    private TextView text1,text2;
    private CheckBox check1;
    private Button btn1;

    public UserDataViewHolder(ViewGroup itemView) {
        super(LayoutInflater.from(itemView.getContext()).inflate(R.layout.viewholder_user_data, itemView, false));
        btn1 = getView(R.id.btn1);
        text1 = getView(R.id.text1);
        text2 = getView(R.id.text2);
        check1 = getView(R.id.check1);
    }

    public void bindDataandListener(final User data,final DbUtils dbUtils){
        text1.setText("用户id:"+data.getUserId());
        text2.setText("用户昵称："+data.getUserName());
        check1.setChecked(data.getUserSex());
        check1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                data.setUserSex(isChecked);
                dbUtils.update(data);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbUtils.delete(data);
                TSUtil.showInfo(getActivity(),"删除成功，退出刷新",false);
            }
        });
    }

}
