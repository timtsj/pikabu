package com.tsdreamdeveloper.app.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tsdreamdeveloper.app.data.network.PikabuApi
import com.tsdreamdeveloper.app.di.PerApplication
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber


@Module
class NetworkModule {

    @PerApplication
    @Provides
    fun provideGson(): Gson = GsonBuilder()
            .create()

    @PerApplication
    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
            Timber.tag("OkHttp").d(message)
        })
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }


    @PerApplication
    @Provides
    fun provideOkHttpClient(logging: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(logging)
        }.build()
    }

    @PerApplication
    @Provides
    fun provideAppApi(httpClient: OkHttpClient): PikabuApi {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(httpClient)
                .baseUrl("https://cs.pikabu.ru/")
                .build().create(PikabuApi::class.java)
    }
}
