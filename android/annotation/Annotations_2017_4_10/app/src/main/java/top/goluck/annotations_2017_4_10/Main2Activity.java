package top.goluck.annotations_2017_4_10;

import android.os.Bundle;
import android.widget.TextView;

import top.goluck.annotation.Record;

@Record(value = {"测试Main2Activity"},author = "测试Main2Activity-辣克")
public class Main2Activity extends BaseActivity {

    private boolean arg1 = false;
    private int arg2= 1;
    private String arg3 = "arg3";
    private double arg4 = 0.888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView text = (TextView) findViewById(R.id.text);
        ClasssUtil classsUtil = new ClasssUtil();
        text.setText(classsUtil.toMain2Activity());
    }
}
