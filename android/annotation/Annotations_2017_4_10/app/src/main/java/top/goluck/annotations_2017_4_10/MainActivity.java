package top.goluck.annotations_2017_4_10;

import android.os.Bundle;
import android.widget.TextView;

import top.goluck.annotation.Record;

@Record(value = {"入口activity","主业务activity","测试activity"},author = "new luck")
public class MainActivity extends BaseActivity {

    private boolean arg = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView text = (TextView) findViewById(R.id.text);
        ClasssUtil classsUtil = new ClasssUtil();
        text.setText(classsUtil.toMainActivity());
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
    }
}
