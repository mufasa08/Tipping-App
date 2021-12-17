package com.example.tipjar

import com.example.tipping.viewmodel.TipJarViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@Suppress("MemberVisibilityCanBePrivate")
object DI {

    val viewModelModule = module {
        viewModel { TipJarViewModelImpl() }
    }

    val useCaseModule = module {
    }

    val repositoryModule = module {
    }

    val dataSourceModule = module {
    }

    val databaseModule = module {
    }

    val allModules = listOf(
        viewModelModule,
        useCaseModule,
        repositoryModule,
        dataSourceModule,
        databaseModule
    )
}
