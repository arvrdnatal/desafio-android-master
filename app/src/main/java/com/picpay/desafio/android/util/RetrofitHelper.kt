package com.picpay.desafio.android.util

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private const val URL = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"

    fun build(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}