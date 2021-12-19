package com.example.data.repository

import TipHistory
import com.example.data.db.DbTransaction
import com.example.data.db.dao.TipHistoryDao
import com.example.data.db.entity.TipHistoryEntity
import java.util.*

interface TipHistoryRepository {

    suspend fun getTipHistoryList(): List<TipHistory>

    suspend fun addTipHistory(
        paymentDate: Date,
        payment: Double,
        tipAmount: Double,
        receiptImageUriPath: String?
    )
}

class TipHistoryRepositoryImpl constructor(
    private val tipHistoryDao: TipHistoryDao,
    private val dbTransaction: DbTransaction,
) : TipHistoryRepository {
    override suspend fun getTipHistoryList(): List<TipHistory> {
        return tipHistoryDao.getAll().map { it.toTipHistory() }
    }

    override suspend fun addTipHistory(
        paymentDate: Date,
        payment: Double,
        tipAmount: Double,
        receiptImageUriPath: String?
    ) {
        val tipHistoryEntity = TipHistoryEntity(
            paymentDate = paymentDate,
            payment = payment,
            tipAmount = tipAmount,
            receiptImageUriPath = receiptImageUriPath
        )
        dbTransaction.exec {
            tipHistoryDao.insertAll(tipHistoryEntity)
        }
    }
}

fun TipHistoryEntity.toTipHistory(): TipHistory {
    return TipHistory(
        id = id,
        paymentDate = paymentDate,
        payment = payment,
        tipAmount = tipAmount,
        receiptImageUriPath = receiptImageUriPath
    )
}
