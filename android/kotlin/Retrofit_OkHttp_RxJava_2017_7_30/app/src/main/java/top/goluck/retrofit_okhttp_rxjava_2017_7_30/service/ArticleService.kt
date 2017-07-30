package top.goluck.retrofit_okhttp_rxjava_2017_7_30.service

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import top.goluck.retrofit_okhttp_rxjava_2017_7_30.bean.Article
import top.goluck.retrofit_okhttp_rxjava_2017_7_30.bean.Data


/**
 * Created by 员工 on 2017/7/30.
 */
interface ArticleService{

    @GET("/article/today")
    fun getArticle(@Query("dev") dev: String): Observable<Article<Data>>

}