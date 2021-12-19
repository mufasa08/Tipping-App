package com.example.data.data.db.dao

import com.example.data.db.entity.TipHistoryEntity
import java.util.Date

internal fun testTipHistoryEntity(
    index: Int = 1,
): TipHistoryEntity {
    return TipHistoryEntity(
        id = index,
        paymentDate = Date(),
        payment = 10.00,
        tipAmount = 2.00,
        receiptImageUriPath = null
    )
}
