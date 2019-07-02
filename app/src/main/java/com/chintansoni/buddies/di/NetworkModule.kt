package com.chintansoni.buddies.di

import com.chintansoni.buddies.BuildConfig
import com.chintansoni.buddies.model.remote.ApiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

val networkModule = module {

    // Dependency: HttpLoggingInterceptor
    single<Interceptor> {
        HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Timber.tag("OkHttp").d(message) }).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    // Dependency: OkHttpClient
    single {
        OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(get())
                .build()
    }

    // Dependency: Retrofit
    single {
        Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(get())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    // Dependency: ApiService
    single {
        val retrofit: Retrofit = get()
        retrofit.create(ApiService::class.java)
    }
}