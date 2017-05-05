package top.goluck.rxjava_2017_3_5;

import android.content.Intent;
import android.view.View;

import top.goluck.rxjava_2017_3_5.base.BaseActivity;
import top.goluck.rxjava_2017_3_5.function.RxJavaCreateActivity;
import top.goluck.rxjava_2017_3_5.function.RxJavaFilterActivity;
import top.goluck.rxjava_2017_3_5.function.RxJavaTransformActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected int initConfigAndLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.main_create:
                Intent mIntent1 = new Intent(this, RxJavaCreateActivity.class);
                startActivity(mIntent1);
                break;
            case R.id.main_change:
                Intent mIntent2 = new Intent(this, RxJavaTransformActivity.class);
                startActivity(mIntent2);
                break;
            case R.id.main_filter:
                Intent mIntent3 = new Intent(this, RxJavaFilterActivity.class);
                startActivity(mIntent3);
                break;
        }
    }
}
