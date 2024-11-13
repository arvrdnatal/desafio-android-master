package com.picpay.desafio.android.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class MainActivityInstrumentedTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun shouldDisplayTitleWhenStateIsLoading() = runTest {
        composeTestRule.setContent { MainActivityScreen(MainActivityTestVariable.LOADING_STATE) }
        composeTestRule.onNodeWithContentDescription(MainActivityTestTag.TITLE_TAG).assertIsDisplayed()
    }

    @Test
    fun shouldDisplayTitleWhenStateIsError() = runTest {
        composeTestRule.setContent { MainActivityScreen(MainActivityTestVariable.ERROR_STATE) }
        composeTestRule.onNodeWithContentDescription(MainActivityTestTag.TITLE_TAG).assertIsDisplayed()
    }

    @Test
    fun shouldDisplayTitleWhenStateIsSuccess() = runTest {
        composeTestRule.setContent { MainActivityScreen(MainActivityTestVariable.SUCCESS_STATE) }
        composeTestRule.onNodeWithContentDescription(MainActivityTestTag.TITLE_TAG).assertIsDisplayed()
    }

    @Test
    fun shouldShowLoadingScreenWhenStateIsNotNull() = runTest {
        composeTestRule.setContent { MainActivityScreen(MainActivityTestVariable.LOADING_STATE) }
        composeTestRule.onNodeWithContentDescription(MainActivityTestTag.LOADING_TAG).assertIsDisplayed()
    }

    @Test
    fun shouldNotShowLoadingScreenWhenStateIsNull() {
        composeTestRule.setContent { MainActivityScreen(MainActivityTestVariable.LOADING_NULL_STATE) }
        composeTestRule.onNodeWithContentDescription(MainActivityTestTag.LOADING_TAG).assertIsNotDisplayed()
    }

    @Test
    fun shouldShowErrorScreenWhenStateIsNotNull() {
        composeTestRule.setContent { MainActivityScreen(MainActivityTestVariable.ERROR_STATE) }
        composeTestRule.onNodeWithContentDescription(MainActivityTestTag.ERROR_TAG).assertIsDisplayed()
    }

    @Test
    fun shouldNotShowErrorScreenWhenStateIsNull() {
        composeTestRule.setContent { MainActivityScreen(MainActivityTestVariable.ERROR_NULL_STATE) }
        composeTestRule.onNodeWithContentDescription(MainActivityTestTag.ERROR_TAG).assertIsNotDisplayed()
    }

    @Test
    fun shouldShowSuccessScreenWhenStateIsNotNullAndListIsNotEmpty() {
        composeTestRule.setContent { MainActivityScreen(MainActivityTestVariable.SUCCESS_STATE) }
        composeTestRule.onNodeWithContentDescription(MainActivityTestTag.SUCCESS_NOT_EMPTY_LIST_TAG).assertIsDisplayed()
    }

    @Test
    fun shouldShowSuccessScreenWhenStateIsNotNullAndListIsEmpty() {
        composeTestRule.setContent { MainActivityScreen(MainActivityTestVariable.SUCCESS_EMPTY_LIST_STATE) }
        composeTestRule.onNodeWithContentDescription(MainActivityTestTag.SUCCESS_EMPTY_LIST_TAG).assertIsDisplayed()
    }

    @Test
    fun shouldNotShowSuccessScreenWhenStateIsNull() {
        composeTestRule.setContent { MainActivityScreen(MainActivityTestVariable.SUCCESS_NULL_STATE) }
        composeTestRule.onNodeWithContentDescription(MainActivityTestTag.SUCCESS_EMPTY_LIST_TAG).assertIsNotDisplayed()
        composeTestRule.onNodeWithContentDescription(MainActivityTestTag.SUCCESS_NOT_EMPTY_LIST_TAG).assertIsNotDisplayed()
    }
}