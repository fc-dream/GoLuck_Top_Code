package top.goluck.aspectj_2017_6_11;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * 作者：luck on 2017/6/11 12:39
 * 邮箱：fc_dream@163.com
 * AspectJ_2017_6_11
 */
@Aspect
public class AspectJTest {
    /**
     * 示例1：注入到所有满足android.app.Activity.on**(..)匹配的方法
     *
     * @param joinPoint 该方法的反射操作类
     * @throws Throwable
     */
    @Before("execution(* android.app.Activity.on**(..))")
    public void onActivityMethodBefore(JoinPoint joinPoint) throws Throwable {
        String key = joinPoint.getSignature().toString();
        Object[] args = joinPoint.getArgs();
        LogUtil.i(true, "示例1：onActivityMethodBefore: key=" + key + (args != null ? ("参数" + args.length + "个") : "无参数"));
    }

    /**
     * 示例2：注入指定方法top.goluck.aspectj_2017_6_11.MainActivity.onCreate
     * @param joinPoint 该方法的反射操作类
     * @throws Throwable
     */
    @Around("execution(* top.goluck.aspectj_2017_6_11.MainActivity.onTest(..))")
    public Object onTestMethodBeforeToMainActivity(ProceedingJoinPoint joinPoint) throws Throwable {
        final MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        final String methodName = signature.getName();
        final String[] parameterNames = signature.getParameterNames();
        final Object[] arguments = joinPoint.getArgs();
        String arg1 = "null";
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0)
            for (Object o : args) {
                if (o instanceof String) {
                    arg1 = o.toString();
                    break;
                }
            }
        LogUtil.i(true, "示例2：onTestMethodBeforeToMainActivity: 我是在方法调用之前执行的");
        Object result = joinPoint.proceed();
        LogUtil.i(true, "onTestMethodBeforeToMainActivity:  我是在方法调用之后执行的 methodName=" + methodName + "\n parameterNames = " + returnArr(parameterNames) + "\n arguments=" + returnArr(arguments) + (args != null ? ("参数" + args.length + "个。") : "无参数。") + "第一个满足条件的String参数值=" + arg1 + "result=" + result);
        return result;
    }


    /**
     * 示例3：自定义Pointcuts
     */
    @Pointcut("execution(@top.goluck.aspectj_2017_6_11.Aop * *(..))")
    public void AopMethod() {
    }
    @Before("AopMethod()")
    public void AopMethodBefore(JoinPoint joinPoint) throws Throwable {
        final MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        final String methodName = signature.getName();
        LogUtil.i(true, "示例3：AopMethodBefore:  我是在方法调用之前执行的 methodName=" + methodName);
    }


    private String returnArr(Object[] arr){
        String arrs="";
        if(arr!=null) {
            for (int i = 0; i < arr.length; i++) {
                arrs += arr[i].toString();
            }
        }
        return arrs;
    }

}
