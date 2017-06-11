package top.goluck.aspectj_2017_6_11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtil.i(false,"onCreate");
        onTest("我是第一个满足条件的参数",1,true);
    }

    public String onTest(String arg0,int arg1,boolean arg2){
        LogUtil.i(false,"test arg0="+arg0 + "arg1 = "+arg1+" arg2= "+ arg2);
        return "我是onTest返回的值";
    }

}
