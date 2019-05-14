package com.example.moviedb.ui.di

import com.example.moviedb.BuildConfig
import com.example.moviedb.data.source.api.ApiService
import com.example.moviedb.ui.Constant
import com.example.moviedb.ui.di.Constants.CONNECTION_TIMEOUT
import com.example.moviedb.ui.di.Constants.READ_TIMEOUT
import com.example.moviedb.ui.di.Constants.WRITE_TIMEOUT
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { provideRetrofit() }
    single { provideApi(get()) }
}

object Constants {
    const val READ_TIMEOUT: Long = 10
    const val WRITE_TIMEOUT: Long = 10
    const val CONNECTION_TIMEOUT: Long = 10
}

fun provideRetrofit(): Retrofit {
    val okHttpClient = provideOkHttpClient()
    return Retrofit.Builder()
        .baseUrl(DataSourceProperties.BASE_API)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()
}

fun provideOkHttpClient(): OkHttpClient {
    val httpClientBuilder = OkHttpClient.Builder()
    httpClientBuilder.addInterceptor {
        var request = it.request()
        val url =
            request.url().newBuilder().addQueryParameter(Constant.API_KEY, BuildConfig.API_KEY)
                .build()
        request = request.newBuilder().url(url).build()
        it.proceed(request)
    }
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    httpClientBuilder.addInterceptor(logging)
    httpClientBuilder.readTimeout(
        READ_TIMEOUT, TimeUnit.SECONDS
    )
    httpClientBuilder.writeTimeout(
        WRITE_TIMEOUT, TimeUnit.SECONDS
    )
    httpClientBuilder.connectTimeout(
        CONNECTION_TIMEOUT, TimeUnit.SECONDS
    )
    return httpClientBuilder.build()
}

fun provideApi(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}

object DataSourceProperties {
    const val BASE_API = "https://api.themoviedb.org/"
}
