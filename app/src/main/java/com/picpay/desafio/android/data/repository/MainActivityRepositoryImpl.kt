package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.model.MainResult
import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.data.sources.MainActivityDataSource
import com.picpay.desafio.android.domain.repository.MainActivityRepository
import javax.inject.Inject

class MainActivityRepositoryImpl @Inject constructor(
    private val remoteDataSource: MainActivityDataSource.Remote
) : MainActivityRepository {

    override suspend fun getUsersList(): MainResult<List<User>, Int> = remoteDataSource.getUsersList()
}