package com.chintansoni.buddies.di

import com.chintansoni.buddies.model.UserRepository
import com.chintansoni.buddies.viewmodel.ListViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {

    // Dependency: ListViewModel
    viewModel {
        ListViewModel(get())
    }

    // Dependency: UserRepository
    single {
        UserRepository(get(), get())
    }
}