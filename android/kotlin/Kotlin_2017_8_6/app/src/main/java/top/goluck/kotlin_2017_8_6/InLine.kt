package top.goluck.kotlin_2017_8_6

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Created by 员工 on 2017/8/6.
 */
//单例
object MyExecutor {
    val instance: ExecutorService = Executors.newCachedThreadPool()
}
//内嵌函数 传递方法
inline fun doSync(crossinline block: () -> Unit) {
    MyExecutor.instance.execute { block() }
}
//内嵌函数 传递属性
inline fun <reified T> getClassName(any: Any): String {
    return T::class.java.name + "\n" + any.toString()
}

//String的扩展方法
fun String.toTop10String() :String{
    if (this.length > 10)
        return this.substring(0, 10)
    else
        return this
}