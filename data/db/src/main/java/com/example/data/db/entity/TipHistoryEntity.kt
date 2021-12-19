package com.example.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "tip_history")
data class TipHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "payment_date") val paymentDate: Date,
    @ColumnInfo(name = "payment") val payment: Double,
    @ColumnInfo(name = "tip_amount") val tipAmount: Double,
    @ColumnInfo(name = "receipt_image_uri_path") val receiptImageUriPath: String?,
)
