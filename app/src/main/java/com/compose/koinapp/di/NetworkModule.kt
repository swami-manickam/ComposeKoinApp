package com.compose.koinapp.di

import com.compose.koinapp.ApiConstants.BASE_URL
import com.compose.koinapp.BuildConfig
import com.compose.koinapp.data.remote.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


fun provideHttpInterceptor(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
    return logging
}

fun provideHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().addInterceptor(provideHttpInterceptor())
        .addInterceptor(RequestInterceptor())
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS).build()
}

fun provideConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()


fun provideRetrofit(
    okHttpClient: OkHttpClient,
    gsonConverterFactory: GsonConverterFactory
): Retrofit {
    return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
        .addConverterFactory(gsonConverterFactory).build()
}

fun provideService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)


val networkModule = module {
    single { provideHttpClient() }
    single { provideConverterFactory() }
    single { provideRetrofit(get(), get()) }
    single { provideService(get()) }
}