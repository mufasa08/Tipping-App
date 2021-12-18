package com.example.db.dao

import TipHistory
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.db.entity.TipHistoryEntity

@Dao
interface TipHistoryDao {
    @Query("SELECT * FROM tip_history WHERE id = :id")
    suspend fun get(id: String): TipHistoryEntity?

    @Query("SELECT * FROM tip_history")
    fun getTipHistoryList(): LiveData<List<TipHistory>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(tipHistory: TipHistoryEntity)

    @Update
    suspend fun update(tipHistory: TipHistoryEntity): Int
}
