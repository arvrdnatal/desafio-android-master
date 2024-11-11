package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.data.model.MainResult
import com.picpay.desafio.android.data.model.User
import java.io.File

interface MainActivityRepository {
    suspend fun getUsersList(): MainResult<List<User>, Int>

    fun saveData(
        cacheDir: File,
        fileName: String,
        data: String,
    ): MainResult<Unit, Int>
}