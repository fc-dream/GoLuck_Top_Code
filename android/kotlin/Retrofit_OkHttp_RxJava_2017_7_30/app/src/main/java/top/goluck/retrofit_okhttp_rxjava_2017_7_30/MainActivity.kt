package top.goluck.retrofit_okhttp_rxjava_2017_7_30

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import top.goluck.retrofit_okhttp_rxjava_2017_7_30.bean.Data
import top.goluck.retrofit_okhttp_rxjava_2017_7_30.retrofit.RetrofitManager
import top.goluck.retrofit_okhttp_rxjava_2017_7_30.rxjava2.BaseObserver
import top.goluck.retrofit_okhttp_rxjava_2017_7_30.rxjava2.funcation.ArticleFuncation
import top.goluck.retrofit_okhttp_rxjava_2017_7_30.service.ArticleService

class MainActivity : AppCompatActivity() {

    private val baseUrl = "https://interface.meiriyiwen.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        class myObserver : BaseObserver<Data>() {
            override fun onResponse(isSuccess: Boolean, t: Data?) {
                if (isSuccess) {
                    var content = t?.content
                    if (content != null)
                        txt.text = Html.fromHtml(content)
                    else
                        txt.text = "获取数据为空"
                }
                else
                txt.text = "获取数据失败"
            }
        }

        RetrofitManager(baseUrl)
                .createService(ArticleService::class.java)
                .getArticle("1")
                .subscribeOn(Schedulers.io())
                .replay(ArticleFuncation<Data>())
                .bindToLifecycle(txt)
                .subscribe( myObserver())
//                .subscribe({t ->  txt.text = Html.fromHtml(t.content)})
//                .subscribe({ t: Article<Data> -> txt.text = t.data.content },
//                        {t: Throwable -> txt.text = "msg->$t"})
    }

}