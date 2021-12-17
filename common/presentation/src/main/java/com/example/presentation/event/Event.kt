package com.mdualeh.presentation.event

import androidx.lifecycle.MutableLiveData

class Event<out T : Any>(private val content: T) {
    private var handled = false

    fun handle(): T? {
        return if (handled) {
            null
        } else {
            handled = true
            content
        }
    }
}

fun <T : Any> MutableLiveData<Event<T>>.postEvent(content: T) {
    postValue(Event(content))
}
