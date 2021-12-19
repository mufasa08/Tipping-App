@file:Suppress("IllegalIdentifier")

package com.example.data.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.daoHistoryItem
import com.example.data.daoHistoryItemTwo
import com.example.data.db.AppDatabase
import com.example.data.db.dao.TipHistoryDao
import com.example.data.repository.TipHistoryRepositoryImpl
import com.example.data.tipHistoryItem
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

class TipHistoryRepositoryImplTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: TipHistoryRepositoryImpl

    private val daoItem = daoHistoryItem
    private val daoItemTwo = daoHistoryItemTwo

    private val tipHistoryDao: TipHistoryDao = mockk()

    @Before
    fun setUp() {
        repository = TipHistoryRepositoryImpl(tipHistoryDao, FakedDbTransaction())
    }

    @Test
    fun `tipHistory is added successfully`(): Unit = runBlocking {
        // given
        val tipHistory = tipHistoryItem
        coEvery { tipHistoryDao.insertAll(any()) }.returns(Unit)

        // when
        repository.addTipHistory(tipHistory)

        // then
        coVerify { tipHistoryDao.insertAll(any()) }
    }

    @Test
    fun `if tipHistoryIsAdded list is not empty and in order`(): Unit = runBlocking {
        // given
        coEvery { tipHistoryDao.getAll() }.returns(listOf(daoItem, daoItemTwo))

        // when
        val list = repository.getTipHistoryList()

        // then
        coVerify { tipHistoryDao.getAll() }
        assertThat(list).hasSize(2)
        assertThat(list).isEqualTo(listOf(tipHistoryItem, tipHistoryItem))
    }
}
