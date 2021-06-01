package com.test.app.pin.home

import com.test.app.pin.appModule
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.test.KoinTestRule


@KoinApiExtension
@RunWith(JUnit4::class)
class HomeRepoTest : KoinComponent{

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        // KoinApplication instance here
        modules(appModule)
    }
    private val repo by inject<HomeRepo>()

    @Test
    fun validate_Format3_First_Char_With_Correct_Input_Returns_true(){
        val response = repo.generateFormat3("31245").toCharArray()
        Assert.assertTrue(response[0] == '3')
    }

    @Test
    fun validate_Format3_First_Char_With_InCorrect_Input_Returns_false(){
        val response = repo.generateFormat3("31245").toCharArray()
        Assert.assertFalse(response[0] == '4')
    }

    @Test
    fun validate_Format3_Second_Char_With_Correct_Input_Returns_true(){
        val response = repo.generateFormat3("31245").toCharArray()
        Assert.assertFalse(response[0] == '5')
    }

    @Test
    fun validate_Format3_Second_Char_With_InCorrect_Input_Returns_false(){
        val response = repo.generateFormat3("31245").toCharArray()
        Assert.assertFalse(response[0] == '4')
    }

}