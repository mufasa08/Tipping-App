package com.example.data.data.repository

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.test.core.app.ApplicationProvider
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class RoomRule<T : RoomDatabase>(private val dbClass: Class<T>) : TestWatcher() {
    lateinit var database: T
        private set

    override fun starting(description: Description?) {
        super.starting(description)
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, dbClass)
            .allowMainThreadQueries()
            .build()
    }

    override fun finished(description: Description?) {
        super.finished(description)
        database.close()
    }
}
