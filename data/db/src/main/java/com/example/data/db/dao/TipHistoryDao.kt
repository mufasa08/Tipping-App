package com.example.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.db.entity.TipHistoryEntity

@Dao
interface TipHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg tipHistory: TipHistoryEntity)

    @Query("SELECT * FROM tip_history")
    suspend fun getAll(): List<TipHistoryEntity>
}
