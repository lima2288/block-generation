package com.test.app.pin

import com.test.app.pin.home.HomeRepo
import com.test.app.pin.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { HomeRepo() }

    viewModel { HomeViewModel(get()) }

}