package com.picpay.desafio.android.data.model

sealed class MainResult<out S, out F> {
    data class Success<out S>(val success: S) : MainResult<S, Nothing>()
    data class Fail<F>(val fail: F) : MainResult<Nothing, F>()
}