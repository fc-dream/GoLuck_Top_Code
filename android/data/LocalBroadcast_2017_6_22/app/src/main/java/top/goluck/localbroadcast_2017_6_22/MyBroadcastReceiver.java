package top.goluck.localbroadcast_2017_6_22;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 作者：luck on 2017/6/22 19:38
 * 邮箱：fc_dream@163.com
 * LocalBroadcast_2017_6_22
 */
public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("log","接受到了发送的全局广播");
    }

}
