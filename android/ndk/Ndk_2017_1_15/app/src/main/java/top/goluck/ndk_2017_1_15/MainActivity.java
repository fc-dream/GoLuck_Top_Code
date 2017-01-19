package top.goluck.ndk_2017_1_15;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * 作者：luck on 2017/1/15 13:02
 * 邮箱：fc_dream@163.com
 * ndk
 */
public class MainActivity extends AppCompatActivity {

    private TextView hello_jni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hello_jni = (TextView) findViewById(R.id.hello_jni);
        HelloNDKUtil helloNDKUtil=new HelloNDKUtil();
        hello_jni.setText(helloNDKUtil.getHelloNDK("这是java传递的参数到native方法然后再返回到java的文本"));
    }
}
