package com.picpay.desafio.android.domain.di

import com.picpay.desafio.android.data.repository.MainActivityRepositoryImpl
import com.picpay.desafio.android.data.service.PicPayService
import com.picpay.desafio.android.data.sources.MainActivityDataSource
import com.picpay.desafio.android.data.sources.MainActivityRemoteDataSourceImpl
import com.picpay.desafio.android.domain.repository.MainActivityRepository
import com.picpay.desafio.android.domain.useCase.MainActivityUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
abstract class MainActivityModule {

    @Binds
    abstract fun bindsRemoteDataSource(remoteDataSource: MainActivityRemoteDataSourceImpl): MainActivityDataSource.Remote

    @Binds
    abstract fun bindsRepository(repository: MainActivityRepositoryImpl): MainActivityRepository

    companion object {
        @Provides
        fun providesService(retrofit: Retrofit): PicPayService = retrofit.create(PicPayService::class.java)

        @Provides
        fun providesUseCase(
            mainRepository: MainActivityRepository
        ) = MainActivityUseCase(mainRepository)
    }
}