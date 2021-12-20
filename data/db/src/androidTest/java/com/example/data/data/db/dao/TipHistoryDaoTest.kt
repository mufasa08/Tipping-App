package com.example.data.data.db.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.db.AppDatabase
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TipHistoryDaoTest {
    @get:Rule
    val roomRule = RoomRule(AppDatabase::class.java)

    private val tipHistoryDao by lazy { roomRule.database.tipHistoryDao }

    @Test
    fun insert_new_record() = runBlocking {
        val tipHistory = testTipHistoryEntity()
        val id = tipHistory.id

        /* When */
        tipHistoryDao.insertAll(tipHistory)

        /* Then */
        val fetched = tipHistoryDao.getAll()
        Truth.assertThat(fetched.first()).isEqualTo(tipHistory)
    }
}
