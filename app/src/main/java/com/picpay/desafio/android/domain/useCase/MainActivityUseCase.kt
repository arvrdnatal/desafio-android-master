package com.picpay.desafio.android.domain.useCase

import com.picpay.desafio.android.domain.repository.MainActivityRepository
import javax.inject.Inject

class MainActivityUseCase @Inject constructor(
    private val mainRepository: MainActivityRepository
) {
    suspend fun getUsersList() = mainRepository.getUsersList()
}