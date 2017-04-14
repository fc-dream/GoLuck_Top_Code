package top.goluck.velocitytracker_2017_4_14;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends SwipeBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
        start();
    }

    public void start(){
        Intent m = new Intent(MainActivity.this,Main2Activity.class);
        startActivity(m);
    }
}
