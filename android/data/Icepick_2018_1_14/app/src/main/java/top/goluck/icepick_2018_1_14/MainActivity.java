package top.goluck.icepick_2018_1_14;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import icepick.Icepick;
import icepick.State;

public class MainActivity extends AppCompatActivity {

    @State int num;

    private TextView txt_lable;
    private Button btn_add;
    private TextView txt_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num = getIntent().getIntExtra("NUM", 0);
        if (num == 0) {
            txt_lable = findViewById(R.id.txt_lable);
            txt_lable.setText("我是上个界面传递过来的数据，和上一个界面的区别就是num起始值不一样");
            if (savedInstanceState != null) {
                num = savedInstanceState.getInt("NUM", num);
            }
        } else {
            Icepick.restoreInstanceState(this, savedInstanceState);
        }
        txt_num = findViewById(R.id.txt_num);
        txt_num.setText("" + num);
        btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                num++;
                txt_num.setText("" + num);
            }
        });

        findViewById(R.id.btn_new).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                if (getIntent().getIntExtra("NUM", 0) == 0) {
                    Intent mIntent = new Intent(MainActivity.this, MainActivity.class);
                    mIntent.putExtra("NUM", num);
                    startActivity(mIntent);
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (getIntent().getIntExtra("NUM", 0) != 0){
            Icepick.saveInstanceState(this, outState);
        }else{
            outState.putInt("NUM", num);
        }
        super.onSaveInstanceState(outState);
    }
}
