package top.goluck.reflection_2017_5_5.model;

import java.io.Serializable;

/**
 * 作者：luck on 2017/5/4 17:07
 * 邮箱：fc_dream@163.com
 * Reflection_2017_5_5
 */
public class BaseReflectionInfo implements Serializable {

    private int id=1;
    private String str="89788979789789789789789";
    public Double dou=1.0;
    protected Float flo=1.0f;

    public BaseReflectionInfo() {
    }

    public BaseReflectionInfo(int id) {
        this.id = id;
    }

    private BaseReflectionInfo(int id,String str) {
        this.id = id;
        this.str = str;
    }

    private int getId(){
        return id;
    }

    protected String logMsg(){
        return  str;
    }

    public String sendThis(){
        return str+id+dou+flo;
    }

}
