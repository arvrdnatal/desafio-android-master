package com.picpay.desafio.android.data.model

import androidx.annotation.StringRes

data class ButtonInfo(
    @StringRes val text: Int,
    val action: () -> Unit
)