package top.goluck.aspectj_2017_6_11;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 作者：luck on 2017/6/12 09:13
 * 邮箱：fc_dream@163.com
 * AspectJ_2017_6_11
 */

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD})
public @interface Aop {
}
