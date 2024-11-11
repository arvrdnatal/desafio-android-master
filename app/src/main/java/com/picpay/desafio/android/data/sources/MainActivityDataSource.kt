package com.picpay.desafio.android.data.sources

import com.picpay.desafio.android.data.model.MainResult
import com.picpay.desafio.android.data.model.User

interface MainActivityDataSource {
    interface Remote {
        suspend fun getUsersList(): MainResult<List<User>, Int>
    }
}