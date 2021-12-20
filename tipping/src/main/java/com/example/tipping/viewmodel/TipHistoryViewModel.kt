package com.example.tipping.viewmodel

import TipHistory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.TipHistoryRepository
import kotlinx.coroutines.launch
import timber.log.Timber

interface TipHistoryViewModel {
    val tipHistoryList: MutableLiveData<List<TipHistory>>

    fun getTipHistory()
}

class TipHistoryViewModelImpl(
    private val tipHistoryRepository: TipHistoryRepository
) : TipHistoryViewModel, ViewModel() {
    override val tipHistoryList = MutableLiveData<List<TipHistory>>()

    init {
        getTipHistory()
    }
    override fun getTipHistory() {
        viewModelScope.launch {
            try {
                val list = tipHistoryRepository.getTipHistoryList()
                tipHistoryList.postValue(list)
            } catch (e: Throwable) {
                Timber.e(e)
            }
        }
    }
}
