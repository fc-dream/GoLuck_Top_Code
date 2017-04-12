package top.goluck.annotations_2017_4_10;

import android.os.Bundle;
import android.widget.TextView;

import top.goluck.annotation.Record;

@Record(value = {"测试Main1Activity"},author = "Main1Activity-辣克")
public class Main1Activity extends BaseActivity {

    private boolean arg1 = false;
    private int arg2= 1;
    private double arg4 = 0.888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView text = (TextView) findViewById(R.id.text);
        ClasssUtil classsUtil = new ClasssUtil();
        text.setText(classsUtil.toMain1Activity());
    }
}
