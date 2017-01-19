package top.goluck.util;

/**
 * 作者：luck on 2017/1/19 09:05
 * 邮箱：fc_dream@163.com
 * Ndk_2017_1_19
 */
public class KeyUtil {

    static{
        System.loadLibrary("goluck");
    }

    public native String getKey(Object context, int type);

}
