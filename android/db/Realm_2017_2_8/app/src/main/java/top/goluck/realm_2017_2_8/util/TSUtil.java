package top.goluck.realm_2017_2_8.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * 作者：luck on 2017/2/9 18:18
 * 邮箱：fc_dream@163.com
 * Realm_2017_2_8
 */
public class TSUtil {

    private static Toast mToast;
    public static void showInfo(Context context, int messageid, boolean time) {
        String msg="";
        try{
            msg=context.getString(messageid);
        }catch (Exception e){
            msg=messageid+"";
        }
        showInfo(context,msg,time);
    }
    public static void showInfo(Context context, String message,boolean time) {
        if(mToast!=null){
            mToast.cancel();
        }
        if (time) {
            mToast=Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_LONG);
        }else{
            mToast=Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public static void I(String msg){
        Log.i("Realm","----------"+msg);
    }

}
