package com.picpay.desafio.android.domain.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.util.RetrofitHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun providesMainService(
        client: OkHttpClient,
        gson: Gson
    ): Retrofit = RetrofitHelper.build(client, gson)

    @Provides
    fun providesGsonSerialization(): Gson = GsonBuilder().create()
}