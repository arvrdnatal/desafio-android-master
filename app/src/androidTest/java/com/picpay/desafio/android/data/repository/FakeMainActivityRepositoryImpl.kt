package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.model.MainResult
import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.domain.repository.MainActivityRepository
import java.io.File
import javax.inject.Inject

class FakeMainActivityRepositoryImpl @Inject constructor() : MainActivityRepository {

    override suspend fun getUsersList(): MainResult<List<User>, Int> = MainResult.Success(
        listOf()
    )

    override fun saveData(
        cacheDir: File,
        fileName: String,
        data: String
    ): MainResult<Unit, Int> = MainResult.Success(Unit)
}