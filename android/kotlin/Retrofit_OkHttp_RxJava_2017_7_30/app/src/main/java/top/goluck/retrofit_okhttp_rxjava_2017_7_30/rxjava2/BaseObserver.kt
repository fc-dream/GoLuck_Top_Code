package top.goluck.retrofit_okhttp_rxjava_2017_7_30.rxjava2

import android.support.annotation.NonNull
import io.reactivex.Observer
import io.reactivex.disposables.Disposable



/**
 * 作者：luck on 2017/7/28 15:05
 * 邮箱：fc_dream@163.com
 * Tuhu_Android
 */
abstract class BaseObserver<T> : Observer<T> {
    private var mDisposable: Disposable? = null
    abstract fun onResponse(isSuccess: Boolean, t: T?)
    override fun onSubscribe(@NonNull d: Disposable) {
        mDisposable = d
    }
    override fun onNext(@NonNull t: T) {
        onResponse(true, t)
    }
    override fun onError(@NonNull e: Throwable) {
        onResponse(false, null)
        disposed()
    }
    private fun disposed() {
        if (mDisposable != null && !mDisposable!!.isDisposed) {
            mDisposable!!.dispose()
        }
    }
    /**
     * 如需该回调，可手动重写该方法
     */
    override fun onComplete() {
        disposed()
    }
}
