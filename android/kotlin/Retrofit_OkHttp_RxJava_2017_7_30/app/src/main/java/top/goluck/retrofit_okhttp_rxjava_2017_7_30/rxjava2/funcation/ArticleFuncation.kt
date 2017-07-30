package top.goluck.retrofit_okhttp_rxjava_2017_7_30.rxjava2.funcation

import io.reactivex.Observable
import io.reactivex.functions.Function
import top.goluck.retrofit_okhttp_rxjava_2017_7_30.bean.Article
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by 员工 on 2017/7/30.
 */
class ArticleFuncation<T> : Function<Observable<Article<T>>, Observable<T>> {
    override fun apply(t: Observable<Article<T>>): Observable<T> {
        return t.subscribeOn(AndroidSchedulers.mainThread())
                .map(Function<Article<T>, T> { t ->
                    if (t != null) {
                        return@Function t.data
                    }
                    return@Function null!!
                })
                .subscribeOn(Schedulers.io())
                .filter { t -> t != null }
                .subscribeOn(AndroidSchedulers.mainThread())
    }

}