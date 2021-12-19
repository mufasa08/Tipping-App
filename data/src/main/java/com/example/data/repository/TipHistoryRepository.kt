package com.example.data.repository

import TipHistory
import com.example.data.db.DbTransaction
import com.example.data.db.dao.TipHistoryDao
import com.example.data.db.entity.TipHistoryEntity

interface TipHistoryRepository {

    suspend fun getTipHistoryList(): List<TipHistory>

    suspend fun addTipHistory(tipHistory: TipHistory)
}

class TipHistoryRepositoryImpl constructor(
    private val tipHistoryDao: TipHistoryDao,
    private val dbTransaction: DbTransaction,
) : TipHistoryRepository {
    override suspend fun getTipHistoryList(): List<TipHistory> {
        return tipHistoryDao.getAll().map { it.toTipHistory() }
    }

    override suspend fun addTipHistory(tipHistory: TipHistory) {
        val tipHistoryEntity = tipHistory.toTipHistoryEntity()
        dbTransaction.exec {
            tipHistoryDao.insertAll(tipHistoryEntity)
        }
    }
}

fun TipHistoryEntity.toTipHistory(): TipHistory {
    return TipHistory(
        paymentDate = paymentDate, payment = payment, tipAmount = tipAmount,
        receiptImageUriPath = receiptImageUriPath
    )
}

fun TipHistory.toTipHistoryEntity(): TipHistoryEntity {
    return TipHistoryEntity(
        paymentDate = paymentDate, payment = payment, tipAmount = tipAmount,
        receiptImageUriPath = receiptImageUriPath
    )
}
