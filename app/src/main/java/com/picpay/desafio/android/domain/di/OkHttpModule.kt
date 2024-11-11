package com.picpay.desafio.android.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OkHttpModule {
    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()
}