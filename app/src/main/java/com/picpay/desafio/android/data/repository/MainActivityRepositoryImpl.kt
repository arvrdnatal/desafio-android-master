package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.model.MainResult
import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.data.sources.MainActivityDataSource
import com.picpay.desafio.android.domain.repository.MainActivityRepository
import java.io.File
import javax.inject.Inject

class MainActivityRepositoryImpl @Inject constructor(
    private val remoteDataSource: MainActivityDataSource.Remote,
    private val localDataSource: MainActivityDataSource.Local
) : MainActivityRepository {

    override suspend fun getUsersList(): MainResult<List<User>, Int> = remoteDataSource.getUsersList()

    override fun saveData(
        cacheDir: File,
        fileName: String,
        data: String
    ): MainResult<Unit, Int> = localDataSource.cacheInformation(
        cacheDir = cacheDir,
        fileName = fileName,
        data = data
    )
}