package com.example.presentation.widget

import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.databinding.BindingAdapter

// Inspired from https://github.com/JakeWharton/butterknife/blob/b6268e6c517f7c411783fb53b55f88e582877049/butterknife-runtime/src/main/java/butterknife/internal/DebouncingOnClickListener.java
class DebouncingClickListener(private val delegate: View.OnClickListener) : View.OnClickListener {
    override fun onClick(v: View) {
        if (enabled) {
            enabled = false
            handler.postDelayed(enableAgain, CLICK_MIN_INTERVAL)
            delegate.onClick(v)
        }
    }

    companion object {
        private var enabled = true
        private val enableAgain = Runnable { enabled = true }
        private val handler = Handler(Looper.getMainLooper())
        private const val CLICK_MIN_INTERVAL = 500L // milliseconds
    }
}

@BindingAdapter("onClick")
fun View.setDebouncingClickListener(listener: View.OnClickListener) {
    setOnClickListener(DebouncingClickListener(listener))
}

@BindingAdapter("onClick", "clickable")
fun View.setDebouncingClickListener(listener: View.OnClickListener, clickable: Boolean) {
    setOnClickListener(DebouncingClickListener(listener))
    isClickable = clickable
}
