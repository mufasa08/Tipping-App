package com.example.data

import TipHistory
import com.example.data.db.entity.TipHistoryEntity
import java.util.Date

val now = Date()
val daoHistoryItem = TipHistoryEntity(
    id = 1,
    paymentDate = now,
    payment = 10.00,
    tipAmount = 2.00,
    receiptImageUriPath = null
)

val daoHistoryItemTwo = TipHistoryEntity(
    id = 2,
    paymentDate = now,
    payment = 10.00,
    tipAmount = 2.00,
    receiptImageUriPath = null
)

val tipHistoryItem = TipHistory(
    paymentDate = now,
    payment = 10.00,
    tipAmount = 2.00,
    receiptImageUriPath = null
)