package com.zinoview.tzmovieapp.service_locator

import android.content.Context
import com.zinoview.tzmovieapp.core.ResourceProvider
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CoreModule {

    private lateinit var retrofit: Retrofit
    lateinit var resourceProvider: ResourceProvider

    fun init(context: Context) {

        initNetwork()
        resourceProvider = ResourceProvider.Base(context)
    }

    private fun initNetwork() {
        val client =
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    fun <T> networkService(clazz: Class<T>) = retrofit.create(clazz)

    private companion object {
        private const val BASE_URL = "https://api.nytimes.com/svc/movies/v2/reviews/"
    }
}