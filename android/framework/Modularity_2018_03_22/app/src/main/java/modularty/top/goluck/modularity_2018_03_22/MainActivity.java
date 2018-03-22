package modularty.top.goluck.modularity_2018_03_22;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import gdt.Config;
import modularty.top.goluck.model1.Model1_MainActivity;
import modularty.top.goluck.resource.LogUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent mIntent = new Intent(this,Model1_MainActivity.class);
        startActivity(mIntent);
        Intent mIntent1 = new Intent(this,modularty.top.goluck.model2.Model2_MainActivity.class);
        startActivity(mIntent1);
        Log.i(LogUtil.TAG,"-------------------1111=="+BuildConfig.DEBUG);
        Log.i(LogUtil.TAG,"-------------------2222=="+ modularty.top.goluck.resource.BuildConfig.DEBUG);

        LogUtil.i("------------------3333=="+BuildConfig.DEBUG);
        Log.i(LogUtil.TAG,"-------------------4444=="+ modularty.top.goluck.model1.BuildConfig.DEBUG);

        Config.initGDT(getApplication());
        Config.logActionGDT();
        Config.logActionGDTstartApp();

    }
}
