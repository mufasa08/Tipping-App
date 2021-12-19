package com.example.data

import com.example.data.repository.TipHistoryRepository
import com.example.data.repository.TipHistoryRepositoryImpl
import com.example.data.db.AppDatabase
import com.example.data.db.DbTransaction
import com.example.data.db.DbTransactionImpl
import org.koin.dsl.module

@Suppress("MemberVisibilityCanBePrivate")
object DataDI {

    val dataModule = module {
        single { AppDatabase.createAppDatabase(get()) }
        single { get<AppDatabase>().tipHistoryDao }
        single<DbTransaction> { DbTransactionImpl(get<AppDatabase>()) }
    }

    val repositoryModule = module {
        single<TipHistoryRepository> { TipHistoryRepositoryImpl(get(), get()) }
    }
}
