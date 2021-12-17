package com.example.tipjar

import org.koin.dsl.module

@Suppress("MemberVisibilityCanBePrivate")
object DI {

    val viewModelModule = module {
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
