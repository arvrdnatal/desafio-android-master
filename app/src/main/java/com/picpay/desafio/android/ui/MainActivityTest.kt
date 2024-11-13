package com.picpay.desafio.android.ui

import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.model.ButtonInfo
import com.picpay.desafio.android.data.model.User

object MainActivityTestTag {
    const val TITLE_TAG = "title"
    const val LOADING_TAG = "loading"
    const val ERROR_TAG = "error"
    const val SUCCESS_NOT_EMPTY_LIST_TAG = "success_not_empty_list"
    const val SUCCESS_EMPTY_LIST_TAG = "success_empty_list"
}

object MainActivityTestVariable {
    val LOADING_STATE = MainActivityUiState(loading = MainActivityUiLoading())
    val LOADING_NULL_STATE = MainActivityUiState(loading = null)

    val ERROR = MainActivityUiError(
        message = R.string.error,
        button = ButtonInfo(text = R.string.retry, action = {})
    )
    val ERROR_STATE = MainActivityUiState(error = ERROR)
    val ERROR_NULL_STATE = MainActivityUiState(error = null)

    val SUCCESS_STATE = MainActivityUiState(
        success = MainActivityUiSuccess(
            usersList = listOf(
                User(id = 1, name = "First User", img = "", username = "first_user")
            )
        )
    )
    val SUCCESS_EMPTY_LIST_STATE = MainActivityUiState(
        success = MainActivityUiSuccess(
            usersList = listOf()
        )
    )
    val SUCCESS_NULL_STATE = MainActivityUiState(success = null)
}