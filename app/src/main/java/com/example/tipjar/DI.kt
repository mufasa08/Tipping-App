package com.example.tipjar

import com.example.data.DataDI
import com.example.tipping.viewmodel.TipHistoryViewModelImpl
import com.example.tipping.viewmodel.TipJarViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@Suppress("MemberVisibilityCanBePrivate")
object DI {

    val viewModelModule = module {
        viewModel { TipJarViewModelImpl(get()) }
        viewModel { TipHistoryViewModelImpl(get()) }
    }

    val allModules = listOf(
        DataDI.dataModule,
        DataDI.repositoryModule,
        viewModelModule,
    )
}
