package com.compose.koinapp.di

import com.compose.koinapp.ApiConstants
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor :Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().
        addHeader(ApiConstants.CONNECTION,ApiConstants.CLOSE).build()

        return chain.proceed(request)
    }
}