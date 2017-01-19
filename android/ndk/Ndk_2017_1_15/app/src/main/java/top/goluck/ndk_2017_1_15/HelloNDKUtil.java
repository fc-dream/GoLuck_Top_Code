package top.goluck.ndk_2017_1_15;

/**
 * 作者：luck on 2017/1/15 13:02
 * 邮箱：fc_dream@163.com
 * ndk
 */
public class HelloNDKUtil {
    static {
        System.loadLibrary("NdkTest");
    }
    public native String getHelloNDK(String str);
}
