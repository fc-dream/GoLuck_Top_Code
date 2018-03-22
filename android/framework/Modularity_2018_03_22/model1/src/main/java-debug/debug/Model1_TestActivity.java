package debug;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import modularty.top.goluck.model1.Model1_MainActivity;
import modularty.top.goluck.model1.R;

/**
 * 作者：luck on 2018/3/19 16:48
 * 邮箱：fc_dream@163.com
 * Modularity
 */
public class Model1_TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.model1_activity_test);
        startActivity(new Intent(this, Model1_MainActivity.class));
    }

}
