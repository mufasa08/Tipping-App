package com.example.tipping.viewmodel

import TipHistory
import java.util.Date

val now = Date()

val tipHistoryItem = TipHistory(
    id = 1,
    paymentDate = now,
    payment = 10.00,
    tipAmount = 2.00,
    receiptImageUriPath = null
)

val tipHistorySecondItem = TipHistory(
    id = 2,
    paymentDate = now,
    payment = 15.00,
    tipAmount = 2.00,
    receiptImageUriPath = null
)
