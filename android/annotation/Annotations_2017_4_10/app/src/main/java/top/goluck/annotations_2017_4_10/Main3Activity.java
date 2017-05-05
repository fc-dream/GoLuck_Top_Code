package top.goluck.annotations_2017_4_10;

import android.os.Bundle;
import android.widget.TextView;

import top.goluck.annotation.Record;

@Record(value = {"测试Main3Activity"},author = "测试Main3Activity-辣克")
public class Main3Activity extends BaseActivity {

    private boolean arg1 = false;
    private double arg4 = 0.888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView text = (TextView) findViewById(R.id.text);
        ClasssUtil classsUtil = new ClasssUtil();
        text.setText(classsUtil.toMain3Activity());
    }
}
