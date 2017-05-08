package top.goluck.reflection_2017_5_5;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import top.goluck.reflection_2017_5_5.model.BaseReflectionInfo;
import top.goluck.reflection_2017_5_5.model.ReflectionInfo;

public class MainActivity extends AppCompatActivity {

    private TextView text;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);
        //利用Java反射技术阻止通过按钮关闭对话框
        alertDialog = new AlertDialog.Builder(this)
                .setTitle("abc")
                .setMessage("Content")
                .setIcon(R.mipmap.ic_launcher)
                .setPositiveButton("确定", new PositiveClickListener())
                .setNegativeButton("取消", new NegativeClickListener())
                .create();
    }

    public void onAction(View view){
        String msg="";
        switch (view.getId()){
            case R.id.button1:
                msg+="构造方法：\n";
                msg+="BaseReflectionInfo：\n"+get_Reflection_Constructors(BaseReflectionInfo.class);
                try {
                    msg+="\nReflectionInfo：\n"+get_Reflection_Constructors(Class.forName("top.goluck.reflection_2017_5_5.model.ReflectionInfo"));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.button2:
                msg+="设置属性：\n";
                msg+=get_Reflection_Field(ReflectionInfo.class.getSuperclass(),100);
                break;
            case R.id.button3:
                msg+="调用方法：\n";
                msg+=get_Reflection_Method(new ReflectionInfo().getClass());
                break;
            case R.id.button4:
                modify_mHandler();
                alertDialog.show();
                break;
        }
        text.setText(msg);
    }

    private String get_Reflection_Method(Class<? extends ReflectionInfo> cls) {
        String msg = "";
        LoadMethodEx ex = new LoadMethodEx();
        msg+=ex.Load(cls.getName(),"sendThis",null)+"\n";
        return msg;
    }

    private String get_Reflection_Field(Class cls, int x) {
        String msg = "";
        Class temp = cls;
        try {
            msg+=temp.getName()+"\n";
            Field[] fa = temp.getDeclaredFields();
            for (int i = 0; i < fa.length; i++) {
                Class cl = fa[i].getType();    // 属性的类型
                int md = fa[i].getModifiers();    // 属性的修饰域
                Field f = temp.getDeclaredField(fa[i].getName());    // 属性的值
                f.setAccessible(true);    // Very Important
                Object value = f.get(cls.newInstance());
                if (value == null) {
                    msg+=Modifier.toString(md) + " " + cl + " : " + fa[i].getName()+"\n";
                }
                else {
                    msg+=Modifier.toString(md) + " " + cl + " : " + fa[i].getName() + " = " + value.toString()+"\n";;
                }
            }

            Object obj = cls.newInstance();
            Field f = temp.getDeclaredField("str");
            f.setAccessible(true);
            f.set(obj,"反射设置的值："+x);
            msg += f.get(obj).toString();

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            return msg;
        }
    }

    public static String get_Reflection_Constructors(Class cls) {
        String msg = "";
        Class temp = cls;
        String className = temp.getName();// 获取指定类的类名
        try {
            //getDeclaredConstructors 方式获取所有构造
            Constructor[] theConstructors = temp.getDeclaredConstructors();
            for (int i = 0; i < theConstructors.length; i++) {
                int mod = theConstructors[i].getModifiers();    // 修饰域和方法名称
                msg += Modifier.toString(mod) + " " + className + "(";
                Class[] parameterTypes = theConstructors[i].getParameterTypes();    // 获取指定构造方法的参数的集合
                for (int j = 0; j < parameterTypes.length; j++) {    // 输出打印参数列表
                    msg += parameterTypes[j].getName();
                    if (parameterTypes.length > j + 1) {
                        msg += ", ";
                    }
                }
                msg += ")\n";
            }
            //getConstructor方法获取构造
            Constructor atheConstructors = temp.getConstructor(int.class);
            if(atheConstructors!=null) {
                int mod = atheConstructors.getModifiers();    // 输出修饰域和方法名称
                msg += Modifier.toString(mod) + " " + className + "(";
                Class[] parameterTypes = atheConstructors.getParameterTypes();    // 获取指定构造方法的参数的集合
                for (int j = 0; j < parameterTypes.length; j++) {    // 输出打印参数列表
                    msg += parameterTypes[j].getName();
                    if (parameterTypes.length > j + 1) {
                        msg += ", ";
                    }
                }
                msg += ")\n";
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return msg;
        }
    }

    private class PositiveClickListener implements android.content.DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            // 方法二时启用
            modify_dismissDialog(false);
        }
    }

    private class NegativeClickListener implements android.content.DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            // 方法一时启用
            //dialog.dismiss();

            // 方法二时启用
            modify_dismissDialog(true);
        }
    }

    /*
     * 第一种方法：修改AlertController类的private成员变量mHandler的值
     */
    public void modify_mHandler() {
        try {
            Field field = alertDialog.getClass().getDeclaredField("mAlert");
            field.setAccessible(true);
            // 获取mAlert变量的值
            Object obj = field.get(alertDialog);
            field = obj.getClass().getDeclaredField("mHandler");
            field.setAccessible(true);
            // 修改mHandler变量的值，使用新的ButtonHandler类
            field.set(obj, new MyButtonHandler(alertDialog));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * 第二种方法：修改dismissDialog()方法
     */
    public void modify_dismissDialog(boolean flag) {
        try {
            Field field = alertDialog.getClass().getSuperclass().getDeclaredField("mShowing");
            field.setAccessible(true);
            // 将mShowing变量设为false，表示对话框已经关闭
            field.set(alertDialog, flag);
            alertDialog.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
