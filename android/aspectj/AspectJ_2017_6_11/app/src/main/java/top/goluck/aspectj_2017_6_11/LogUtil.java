package top.goluck.aspectj_2017_6_11;

import android.util.Log;

/**
 * 作者：luck on 2017/6/11 12:40
 * 邮箱：fc_dream@163.com
 * AspectJ_2017_6_11
 */
public class LogUtil {

    private static final String Tag = "AspectJ";

    public  static void i(boolean isAspect,String msg){
        Log.i(Tag,"测试log--> "+(!isAspect?"非注入":"AspectJ注入执行调用")+"-->"+msg);
    }

}
