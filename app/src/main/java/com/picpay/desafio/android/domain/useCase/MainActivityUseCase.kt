package com.picpay.desafio.android.domain.useCase

import android.content.Context
import com.picpay.desafio.android.data.model.MainResult
import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.domain.repository.MainActivityRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MainActivityUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
    private val mainRepository: MainActivityRepository
) {
    suspend fun getUsersList(): MainResult<List<User>, Int> {
        return when (val result = mainRepository.getUsersList()) {
            is MainResult.Success -> saveInformation(result.success)
            is MainResult.Fail -> result
        }
    }

    private fun saveInformation(
        data: List<User>
    ): MainResult<List<User>, Int> {
        return when (val result = mainRepository.saveData(
            cacheDir = context.cacheDir,
            fileName = "information.txt",
            data = data.toStringData()
        )) {
            is MainResult.Success -> MainResult.Success(data)
            is MainResult.Fail -> result
        }
    }

    private fun List<User>.toStringData(): String {
        var data = ""

        forEachIndexed { index, user ->
            data += user.toString()

            if (index != lastIndex) data += "\n"
        }

        return data
    }
}