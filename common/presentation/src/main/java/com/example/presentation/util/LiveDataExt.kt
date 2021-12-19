package com.example.presentation.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

@Suppress("FunctionName")
fun <T> MediatorLiveData(value: T): MediatorLiveData<T> {
    return MediatorLiveData<T>().also { it.value = value }
}

fun <T, S> MediatorLiveData<T>.source(
    source: LiveData<S>,
    onChanged: MediatorLiveData<T>.(S) -> Unit,
) = apply {
    addSource(source) {
        this.onChanged(it)
    }
}
fun <A, B, C> combineLatest(
    a: LiveData<A>,
    b: LiveData<B>,
    f: (A?, B?) -> C,
): MutableLiveData<C> {
    return MediatorLiveData<C>().apply {
        addSource(a) { value = f(it, b.value) }
        addSource(b) { value = f(a.value, it) }
    }
}