package com.test.app.pin.home

import com.test.app.pin.appModule
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.test.KoinTestRule


@KoinApiExtension
@RunWith(JUnit4::class)
class HomeViewModelTest : KoinComponent{


    @get:Rule
    val koinTestRule = KoinTestRule.create {
        // Your KoinApplication instance here
        modules(appModule)
    }

    private val homeViewModel by inject<HomeViewModel>()

    @Test
    fun validate_Correct_Length_ReturnsTrue() {
        assertTrue(homeViewModel.validate("1124"))
    }

    @Test
    fun validate_Incorrect_Length_ReturnsFalse() {
        assertFalse(homeViewModel.validate("112"))
    }

}