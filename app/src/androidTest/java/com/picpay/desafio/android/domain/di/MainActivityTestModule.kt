package com.picpay.desafio.android.domain.di

import com.picpay.desafio.android.data.repository.FakeMainActivityRepositoryImpl
import com.picpay.desafio.android.domain.repository.MainActivityRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [MainActivityModule::class]
)
abstract class MainActivityTestModule {

    @Singleton
    @Binds
    abstract fun bindsFakeRepository(fakeRepo: FakeMainActivityRepositoryImpl): MainActivityRepository
}