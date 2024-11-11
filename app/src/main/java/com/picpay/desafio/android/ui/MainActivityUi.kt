package com.picpay.desafio.android.ui

import androidx.annotation.StringRes
import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.model.ButtonInfo
import com.picpay.desafio.android.data.model.User

data class MainActivityUiState(
    val loading: MainActivityUiLoading? = null,
    val error: MainActivityUiError? = null,
    val success: MainActivityUiSuccess? = null
) {
    fun setLoading() = copy(
        loading = MainActivityUiLoading(),
        error = null,
        success = null
    )

    fun setError(button: ButtonInfo) = copy(
        loading = null,
        error = MainActivityUiError(
            message = R.string.error,
            button = button
        ),
        success = null
    )

    fun setSuccess(usersList: List<User>) = copy(
        loading = null,
        error = null,
        success = MainActivityUiSuccess(usersList)
    )
}

data class MainActivityUiLoading(
    @StringRes val message: Int = R.string.loading
)

data class MainActivityUiError(
    @StringRes val message: Int,
    val button: ButtonInfo
)

data class MainActivityUiSuccess(
    val usersList: List<User>
)