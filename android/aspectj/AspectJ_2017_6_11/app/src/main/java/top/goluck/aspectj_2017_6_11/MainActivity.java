package top.goluck.aspectj_2017_6_11;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtil.i(false,"onCreate 我的channel值 = "+BuildConfig.channel);
        onTest("我是第一个满足条件的参数",1,true);
        ApplicationInfo appInfo = null;
        try {
            appInfo = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String UMENG_CHANNEL = appInfo.metaData.getString("UMENG_CHANNEL");
        LogUtil.i(false,"读取AndroidManifest.xml的UMENG_CHANNEL="+UMENG_CHANNEL);
    }

    public String onTest(String arg0,int arg1,boolean arg2){
        LogUtil.i(false,"test arg0="+arg0 + "arg1 = "+arg1+" arg2= "+ arg2);
        return "我是onTest返回的值";
    }

    @Aop
    @Override
    protected void onResume() {
        super.onResume();
        if(BuildConfig.DEBUG) {
            LogUtil.i(false, "我是DeBug模式下在被注入自定义Pointcuts中的方法执行的onResume");
        }else{
            LogUtil.i(false, "我是Release模式下在被注入自定义Pointcuts中的方法执行的onResume");
        }
    }

    @Aop
    @Override
    protected void onStop() {
        super.onStop();
        if(BuildConfig.isTest) {
            LogUtil.i(false, "我是isTest=true的模式下在被注入自定义Pointcuts中的方法执行的onStop");
        }else{
            LogUtil.i(false, "我是isTest=false的模式下在被注入自定义Pointcuts中的方法执行的onStop");
        }
    }
}
