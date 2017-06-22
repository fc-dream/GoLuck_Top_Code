package top.goluck.localbroadcast_2017_6_22;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 注册本地广播
        IntentFilter filter = new IntentFilter(WorkService.WORK_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 解绑本地广播
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
    }

    public void onAction(View view){
        switch (view.getId()){
            case R.id.button:
                Log.i("msg","启动了服务");
                // 启动服务发送广播
                Intent intent = new Intent(this, WorkService.class);
                intent.putExtra("DATA",text.getText().toString().length()>800?"Hello World":text.getText().toString());
                startService(intent);

                sendBroadcast(new Intent("top.goluck.receiver.action"));
                break;
        }
    }

    /**
     * 广播接收器
     */
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("msg","接收到了广播");
            String date = intent.getStringExtra("DATA");
            String time = intent.getStringExtra("TIME");
            text.setText(date + "\n" + time);
        }
    };

}
