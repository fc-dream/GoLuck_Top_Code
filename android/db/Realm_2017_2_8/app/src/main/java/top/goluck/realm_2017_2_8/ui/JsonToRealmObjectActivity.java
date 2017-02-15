package top.goluck.realm_2017_2_8.ui;

import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import top.goluck.realm_2017_2_8.R;
import top.goluck.realm_2017_2_8.base.ui.BaseActivity;
import top.goluck.realm_2017_2_8.models.db.User;

/**
 * 作者：luck on 2017/2/14 10:51
 * 邮箱：fc_dream@163.com
 * Realm_2017_2_8
 */
public class JsonToRealmObjectActivity extends BaseActivity {

    private TextView text2,text4;

    @Override
    protected int initConfigAndLayoutID() {
        isShowBack = true;
        RID = R.string.title_activity_json_to_realmobject;
        return R.layout.activity_json_to_realmobject;
    }

    @Override
    protected void initView() {
        text2 = getView(R.id.text2);
        text4 = getView(R.id.text4);
        JsonElement json = new JsonParser().parse(text2.getText().toString());
        Gson gson = new GsonBuilder().create();
        List<User> users = gson.fromJson(json, new TypeToken<List<User>>() {}.getType());
        if(users!=null) {
            for (int i = 0; i < users.size(); i++) {
                text4.setText(text4.getText()+String.format(getString(R.string.json_to_realmobject_initview1),i+1)+users.get(i).getUserName()+"\n");
            }
        }
    }
}
