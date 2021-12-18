package com.example.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tip_history")
data class TipHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val paymentDate: Long,
    val payment: Double,
    val tipAmount: Double,
    val receiptImageUriPath: String?,
)
