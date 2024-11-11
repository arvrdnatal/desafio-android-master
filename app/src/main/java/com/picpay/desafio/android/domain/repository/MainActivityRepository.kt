package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.data.model.MainResult
import com.picpay.desafio.android.data.model.User

interface MainActivityRepository {
    suspend fun getUsersList(): MainResult<List<User>, Int>
}