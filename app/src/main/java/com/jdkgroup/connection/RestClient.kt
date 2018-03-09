package com.jdkgroup.connection

import android.content.Context
import com.google.gson.GsonBuilder
import com.jdkgroup.constant.RestConstant
import com.jdkgroup.utils.logInfo
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class RestClient (context: Context) : RestConstant {

    val service: RestService
    private val DEFAULT_TIMEOUT = 10
    private val cacheSizeInMbs = 50
    private val maxStale = 7 //1 WEEK

    private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)

    private var httpClient = OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS) //SET CONNECTION TIMEOUT
            .readTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS) //SET READ TIMEOUT
            .writeTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS) //SET WRITE TIMEOUT
            .addInterceptor(HeaderInterceptor())
            .addInterceptor(logging)
            .build()


    init {
        service = Retrofit.Builder().baseUrl(RestConstant.Companion.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build().create(RestService::class.java)
    }

    inner class HeaderInterceptor : Interceptor {
        //TokenManager tokenManager = new TokenManagerImpl(context);
        override fun intercept(chain: Interceptor.Chain): Response {
            val original = chain.request()
            val requestBuilder = original.newBuilder()
            requestBuilder.header("Accept", "application/json")
            requestBuilder.method(original.method(), original.body())
/*

            if (tokenManager.hasToken()) {
                Logging.INSTANCE.i(tokenManager.getToken());
                requestBuilder.header("Authorization", tokenManager.getToken());
            }
*/

            val request = requestBuilder.build()
            val response = chain.proceed(request)

            logInfo("----------------- API CALL -----------------")
            logInfo("Response " + response)
            logInfo("--------------------------------------------")

            val data = response.body()!!.string()

            return response.newBuilder().body(ResponseBody.create(response.body()!!.contentType(), data)).build()
        }
    }
}