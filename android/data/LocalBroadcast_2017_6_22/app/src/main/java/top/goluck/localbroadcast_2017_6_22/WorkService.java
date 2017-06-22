package top.goluck.localbroadcast_2017_6_22;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.util.Date;

/**
 * 作者：luck on 2017/6/22 17:08
 * 邮箱：fc_dream@163.com
 * LocalBroadcast_2017_6_22
 */
public class WorkService extends IntentService {

    public static final String WORK_ACTION = "WorkService_ACTION";

    public WorkService() {
        super("WorkService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i("log","发送了广播"+intent.getStringExtra("DATA"));
        Intent broadcastIntent = new Intent(WORK_ACTION);
        broadcastIntent.putExtra("DATA", intent.getStringExtra("DATA"));
        broadcastIntent.putExtra("TIME", new Date().toString());
        // 发送本地广播
        LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent);
    }

}
