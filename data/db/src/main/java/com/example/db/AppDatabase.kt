package com.example.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.withTransaction
import com.example.db.converters.Converters
import com.example.db.dao.TipHistoryDao
import com.example.db.entity.TipHistoryEntity

@Database(
    entities = [
        TipHistoryEntity::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val tipHistoryDao: TipHistoryDao

    companion object {
        fun createAppDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "com.example.tipjar.db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}

interface DbTransaction {
    suspend fun <R> exec(block: suspend () -> R): R
}

class DbTransactionImpl(private val db: RoomDatabase) : DbTransaction {
    override suspend fun <R> exec(block: suspend () -> R): R {
        return db.withTransaction(block)
    }
}
