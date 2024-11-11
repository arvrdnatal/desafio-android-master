package com.picpay.desafio.android.domain.di

import android.content.Context
import com.picpay.desafio.android.data.repository.MainActivityRepositoryImpl
import com.picpay.desafio.android.data.service.PicPayService
import com.picpay.desafio.android.data.sources.MainActivityDataSource
import com.picpay.desafio.android.data.sources.MainActivityLocalDataSourceImpl
import com.picpay.desafio.android.data.sources.MainActivityRemoteDataSourceImpl
import com.picpay.desafio.android.domain.repository.MainActivityRepository
import com.picpay.desafio.android.domain.useCase.MainActivityUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
abstract class MainActivityModule {

    @Binds
    abstract fun bindsRemoteDataSource(remoteDataSource: MainActivityRemoteDataSourceImpl): MainActivityDataSource.Remote

    @Binds
    abstract fun bindsLocalDataSource(localDataSource: MainActivityLocalDataSourceImpl): MainActivityDataSource.Local

    @Binds
    abstract fun bindsRepository(repository: MainActivityRepositoryImpl): MainActivityRepository

    companion object {
        @Provides
        fun providesService(retrofit: Retrofit): PicPayService = retrofit.create(PicPayService::class.java)

        @Provides
        fun providesUseCase(
            @ApplicationContext context: Context,
            mainRepository: MainActivityRepository
        ) = MainActivityUseCase(context, mainRepository)
    }
}