package top.goluck.ndk_2017_1_19;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigInteger;
import java.security.MessageDigest;

import top.goluck.util.KeyUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private KeyUtil mKeyUtil;

    private Button qq_key_btn,weixin_key_btn;
    private TextView qq_key_text,weixin_key_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        qq_key_btn = (Button) findViewById(R.id.qq_key_btn);
        weixin_key_btn = (Button) findViewById(R.id.weixin_key_btn);
        qq_key_text = (TextView) findViewById(R.id.qq_key_text);
        weixin_key_text = (TextView) findViewById(R.id.weixin_key_text);
        mKeyUtil = new KeyUtil();
        qq_key_btn.setOnClickListener(this);
        weixin_key_btn.setOnClickListener(this);
        //当前打印出来的签名如果匹配KeyUtil.cpp文件的签名值，则可以调用到so正确的返回数据。
        //如不相等，可以更改KeyUtil.cpp文件的签名值为以下打印的值就可以看到调用成功的数据返回。
        String signature = getSignature(this);
//        String md5 = MD5(signature);
        Log.i("tag",signature);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.qq_key_btn:
                qq_key_text.setText(mKeyUtil.getKey(this,1));
                break;
            case  R.id.weixin_key_btn:
                weixin_key_text.setText(mKeyUtil.getKey(this,2));
                break;
        }
    }

    public static String getSignature(Context context)
    {
        try {
            /** 通过包管理器获得指定包名包含签名的包信息 **/
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            /******* 通过返回的包信息获得签名数组 *******/
            Signature[] signatures = packageInfo.signatures;
            /******* 循环遍历签名数组拼接应用签名 *******/
            return signatures[0].toCharsString();
            /************** 得到应用签名 **************/
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String MD5(String str){
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            Log.e("tag","MD5加密出现错误");
            return "";
        }
    }
}
