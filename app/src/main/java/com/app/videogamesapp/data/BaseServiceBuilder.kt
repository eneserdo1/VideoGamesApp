package com.app.videogamesapp.data

import com.app.videogamesapp.utils.Constants.Companion.baseUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object BaseServiceBuilder {


    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl) // change this IP for testing by your actual machine IP
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder().also {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            it.readTimeout(30,TimeUnit.SECONDS)
            it.writeTimeout(30,TimeUnit.SECONDS)
            it.addInterceptor(loggingInterceptor)
        }.build())
        .build()

    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }
}