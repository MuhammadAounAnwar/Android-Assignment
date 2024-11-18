package com.android.goally.data.network.rest

import com.android.goally.util.PreferenceUtil
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val preferenceUtil: PreferenceUtil) :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = preferenceUtil.getString("auth_token")
        if (token.isNullOrEmpty()) {
            return chain.proceed(chain.request())
        }
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", token)
            .build()

        return chain.proceed(newRequest)
    }
}
