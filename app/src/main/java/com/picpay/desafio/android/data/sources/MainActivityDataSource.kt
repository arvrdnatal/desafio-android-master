package com.picpay.desafio.android.data.sources

import com.picpay.desafio.android.data.model.MainResult
import com.picpay.desafio.android.data.model.User
import java.io.File

interface MainActivityDataSource {
    interface Remote {
        suspend fun getUsersList(): MainResult<List<User>, Int>
    }

    interface Local {
        fun cacheInformation(
            cacheDir: File,
            fileName: String,
            data: String
        ): MainResult<Unit, Int>
    }
}