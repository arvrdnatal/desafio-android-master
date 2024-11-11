package com.picpay.desafio.android.data.sources

import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.model.MainResult
import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.data.service.PicPayService
import javax.inject.Inject

class MainActivityRemoteDataSourceImpl @Inject constructor(
    private val service: PicPayService
) : MainActivityDataSource.Remote {

    override suspend fun getUsersList(): MainResult<List<User>, Int> {
        return try {
            val response = service.getUsers()
            when {
                response.isSuccessful -> response.body()?.let {
                    MainResult.Success(it)
                } ?: MainResult.Fail(R.string.error)
                else -> MainResult.Fail(R.string.error)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            MainResult.Fail(R.string.error)
        }
    }
}