package com.app.movies_tmdb.api

import com.app.movies_tmdb.API_KEY
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitService {

    companion object Factory {
        private const val TIMEOUT = 30 * 1000

        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.themoviedb.org/3/")
                .client(getClient())
                .build()
            return retrofit.create(ApiService::class.java)
        }

        private fun getClient(): OkHttpClient {
            return OkHttpClient.Builder().addInterceptor(getInterceptor())
                .connectTimeout(TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
                .readTimeout(TIMEOUT.toLong(), TimeUnit.MILLISECONDS).build()
        }

        private fun getInterceptor(): Interceptor {
            return Interceptor { chain ->
                val newRequest =
                    chain.request().newBuilder()
                        .addHeader("api_key", API_KEY)
                        .build()
                chain.proceed(newRequest)
            }
        }
    }
}