package top.goluck.annotations_2017_4_10;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

/**
 * 作者：luck on 2017/4/12 10:57
 * 邮箱：fc_dream@163.com
 * Annotations_2017_4_10
 */
public class BaseActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.text1).setOnClickListener(this);
        findViewById(R.id.text2).setOnClickListener(this);
        findViewById(R.id.text3).setOnClickListener(this);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(this.getLocalClassName());
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent mIntent = new Intent();
        switch (v.getId()){
            case R.id.text1:
                mIntent.setClass(this,Main1Activity.class);
                break;
            case R.id.text2:
                mIntent.setClass(this,Main2Activity.class);
                break;
            case R.id.text3:
                mIntent.setClass(this,Main3Activity.class);
                break;
        }
        startActivity(mIntent);
    }
}
