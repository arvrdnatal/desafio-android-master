package com.picpay.desafio.android.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.model.ButtonInfo
import com.picpay.desafio.android.data.model.MainResult
import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.domain.useCase.MainActivityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val mainUseCase: MainActivityUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MainActivityUiState())
    val state: StateFlow<MainActivityUiState> = _state.asStateFlow()

    init {
        searchUsers()
    }

    private fun searchUsers() {
        _state.update { it.setLoading() }

        viewModelScope.launch {
            when (val result = mainUseCase.getUsersList()) {
                is MainResult.Success -> handleSuccessResult(result.success)
                is MainResult.Fail -> handleFailResult()
            }
        }
    }

    private fun handleSuccessResult(users: List<User>) {
        _state.update { it.setSuccess(users) }
    }

    private fun handleFailResult() {
        _state.update { it.setError(ButtonInfo(text = R.string.retry, action = ::searchUsers)) }
    }
}