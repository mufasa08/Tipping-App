package com.example.tipping.viewmodel

import TipHistory
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.data.repository.TipHistoryRepository
import com.example.presentation.event.Event
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class TipHistoryViewModelImplTest {

    @get:Rule
    val instantTestExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private var tipHistoryListObserver: Observer<List<TipHistory>> = mockk()

    private var repository: TipHistoryRepository = mockk()

    private lateinit var viewModel: TipHistoryViewModelImpl
    @Before
    fun setup() {
        viewModel = TipHistoryViewModelImpl(tipHistoryRepository = repository)
        viewModel.tipHistoryList.observeForever(tipHistoryListObserver)
    }

    @Test
    fun `getTipHistory when normal case`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        // given
        val list = listOf(tipHistoryItem, tipHistorySecondItem)
        coEvery { repository.getTipHistoryList() }.returns(list)

        // when
        viewModel.getTipHistory()

        // then
        coVerify { tipHistoryListObserver.onChanged(list) }
    }
}
