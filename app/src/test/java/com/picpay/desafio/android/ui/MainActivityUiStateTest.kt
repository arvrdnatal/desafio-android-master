package com.picpay.desafio.android.ui

import app.cash.turbine.test
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

class MainActivityUiStateTest {

    private val state = MutableStateFlow(MainActivityUiState())

    @Test
    fun `state must set loading as not null`() = runTest {
        state.test {
            assertNull(awaitItem().loading)
        }

        state.update { it.setLoading() }

        state.test {
            assertNotNull(awaitItem().loading)
        }
    }

    @Test
    fun `state must set error as not null`() = runTest {
        state.test {
            assertNull(awaitItem().error)
        }

        state.update { it.setError(MainActivityTestVariable.ERROR.button) }

        state.test {
            assertNotNull(awaitItem().error)
        }
    }

    @Test
    fun `state must set success as not null`() = runTest {
        state.test {
            assertNull(awaitItem().success)
        }

        state.update { it.setSuccess(listOf()) }

        state.test {
            assertNotNull(awaitItem().success)
        }
    }

}