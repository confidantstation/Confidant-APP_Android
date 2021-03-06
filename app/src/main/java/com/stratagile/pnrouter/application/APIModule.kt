package com.stratagile.pnrouter.application

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import com.google.gson.Gson

import com.stratagile.pnrouter.data.api.API
import com.stratagile.pnrouter.data.api.HttpAPIWrapper
import com.stratagile.pnrouter.data.api.HttpApi
import com.stratagile.pnrouter.data.api.HttpInfoInterceptor
import com.stratagile.pnrouter.data.api.RequestBodyInterceptor
import com.stratagile.pnrouter.data.qualifier.Remote
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.socks.library.KLog
import com.stratagile.pnrouter.constant.ConstantValue
import com.stratagile.pnrouter.data.web.*
import com.stratagile.pnrouter.entity.events.ConnectStatus
import com.stratagile.pnrouter.entity.events.ReminderUpdateEvent

import java.util.concurrent.TimeUnit

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.apache.http.conn.ssl.AllowAllHostnameVerifier
import org.greenrobot.eventbus.EventBus
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * @author hu
 * @desc 功能描述
 * @date 2017/5/31 10:04
 */
@Module
class APIModule(private val application: Application) {
    val TAG = APIModule::class.java.simpleName
    @SuppressLint("AllowAllHostnameVerifier")
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(HttpInfoInterceptor())
        builder.hostnameVerifier(AllowAllHostnameVerifier())
        builder.connectTimeout(API.CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(API.IO_TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(RequestBodyInterceptor())
        builder.addInterceptor(Interceptor {
            val request = it.request()
                    .newBuilder()
                    .removeHeader("User-Agent")//移除旧的
                    .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:44.0) Gecko/20100101 Firefox/44.0")//添加真正的头部
                    .build()
            it.proceed(request)
        })
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val builder = Retrofit.Builder()
        //线上环境
        builder.client(okHttpClient)
                .baseUrl(API.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideHttpAPI(restAdapter: Retrofit): HttpApi {
        return restAdapter.create(HttpApi::class.java)
    }

    //这里是对外输出部分
    @Provides
    @Singleton
    @Remote
    fun provideHttpAPIWrapper(httpAPI: HttpApi): HttpAPIWrapper {
        return HttpAPIWrapper(httpAPI)
    }

    open class PipeConnectivityListener : ConnectivityListener {

        override fun onConnected() {
            KLog.i("onConnected()")
            ConstantValue.isWebsocketConnected = true
            EventBus.getDefault().post(ConnectStatus(0))
        }

        override fun onConnecting() {
            KLog.i("onConnecting()")
            ConstantValue.isWebsocketConnected = false
            EventBus.getDefault().post(ConnectStatus(1))
        }

        override fun onDisconnected() {
            ConstantValue.isWebsocketConnected = false
            KLog.i("onDisconnected()")
            EventBus.getDefault().post(ConnectStatus(2))
        }
        override fun onConnectFail() {
            ConstantValue.isWebsocketConnected = false
            KLog.i("onConnectFail()")
            EventBus.getDefault().post(ConnectStatus(3))
        }
        override fun onAuthenticationFailure() {
            KLog.i("onAuthenticationFailure()")
//            TextSecurePreferences.setUnauthorizedReceived(application, true)
            EventBus.getDefault().post(ReminderUpdateEvent())
        }

    }

    open class DynamicCredentialsProvider constructor(context: Context) : CredentialsProvider {

        private val context: Context

        override val user: String
            get() = TextSecurePreferences.getLocalNumber(context)!!

        override val password: String
            get() = TextSecurePreferences.getPushServerPassword(context)!!

        override val signalingKey: String
            get() = TextSecurePreferences.getSignalingKey(context)!!

        init {
            this.context = context
        }
    }
}
