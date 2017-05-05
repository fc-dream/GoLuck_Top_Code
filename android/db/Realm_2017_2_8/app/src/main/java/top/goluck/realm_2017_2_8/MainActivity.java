package top.goluck.realm_2017_2_8;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import top.goluck.realm_2017_2_8.ui.AddDataActivity;
import top.goluck.realm_2017_2_8.ui.DelDataActivity;
import top.goluck.realm_2017_2_8.ui.JsonToRealmObjectActivity;
import top.goluck.realm_2017_2_8.ui.SelectDataActivity;
import top.goluck.realm_2017_2_8.ui.UpdateDataActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        Intent mIntent=new Intent();
        switch (view.getId()) {
            case R.id.btn1:
                mIntent.setClass(this, AddDataActivity.class);
                break;
            case R.id.btn2:
                mIntent.setClass(this, DelDataActivity.class);
                break;
            case R.id.btn3:
                mIntent.setClass(this, UpdateDataActivity.class);
                break;
            case R.id.btn4:
                mIntent.setClass(this, SelectDataActivity.class);
                break;
            case R.id.btn5:
                mIntent.setClass(this, JsonToRealmObjectActivity.class);
                break;
        }
        startActivity(mIntent);
    }

}
