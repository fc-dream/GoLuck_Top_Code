package top.goluck.compiler;

import java.util.Arrays;
import java.util.List;

import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;

/**
 * 作者：luck on 2017/4/11 12:04
 * 邮箱：fc_dream@163.com
 * Annotations_2017_4_10
 */
public class Records {
    public String[] args;
    public String author;
    public String annotatedClassName;//class name
    public TypeElement typeElement;
    public List<String> variableNames;//用于获取字段属性
    public TypeMirror getType() {//class的type
        return typeElement==null?null:typeElement.asType();
    }

    @Override
    public String toString() {
        return "Records{" +
                "args=" + Arrays.toString(args) +
                ", author='" + author + '\'' +
                ", annotatedClassName='" + annotatedClassName + '\'' +
                ", typeElement=" + typeElement +
                ", variableNames=" + variableNames +
                '}';
    }
}
