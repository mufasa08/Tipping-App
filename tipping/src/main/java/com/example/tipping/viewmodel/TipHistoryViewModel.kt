package com.example.tipping.viewmodel

import TipHistory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.TipHistoryRepository
import com.example.presentation.event.Event
import com.example.presentation.event.postEvent
import kotlinx.coroutines.launch
import timber.log.Timber

interface TipHistoryViewModel {
    val tipHistoryLoaded: MutableLiveData<Event<List<TipHistory>>>

    fun getTipHistory()
}

class TipHistoryViewModelImpl(
    private val tipHistoryRepository: TipHistoryRepository
) : TipHistoryViewModel, ViewModel() {
    override val tipHistoryLoaded = MutableLiveData< Event<List<TipHistory>>>()

    init {
        getTipHistory()
    }
    override fun getTipHistory() {
        viewModelScope.launch {
            try {
                val list = tipHistoryRepository.getTipHistoryList()
                tipHistoryLoaded.postEvent(list)
            } catch (e: Throwable) {
                Timber.e(e)
            }
        }
    }
}
