package com.mdualeh.presentation.event

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

@MainThread
inline fun <T : Any> LiveData<Event<T>>.observeEvent(
    owner: LifecycleOwner,
    crossinline eventHandler: (T) -> Unit,
): Observer<Event<T>> {
    val wrappedObserver = Observer<Event<T>> { event ->
        event?.handle()?.let { content ->
            eventHandler.invoke(content)
        }
    }
    observe(owner, wrappedObserver)
    return wrappedObserver
}
