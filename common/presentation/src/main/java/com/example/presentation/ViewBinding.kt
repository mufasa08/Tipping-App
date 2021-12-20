package com.example.presentation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("isEnabled")
fun View.setIsEnabled(enabled: Boolean) {
    isEnabled = enabled
}

@BindingAdapter("isActivated")
fun View.setIsActivated(activated: Boolean) {
    isActivated = activated
}

@BindingAdapter("isVisible")
fun View.setIsVisible(visible: Boolean) {
    isVisible = visible
}

@BindingAdapter("isInvisible")
fun View.setIsInvisible(invisible: Boolean) {
    isInvisible = invisible
}

@BindingAdapter("animatedVisibility")
fun View.setAnimatedVisibility(visible: Boolean) {
    if (visible) {
        isVisible = true
        animate().alpha(1.0f)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    animation.removeListener(this)
                }
            })
    } else {
        animate().alpha(0.0f)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    animation.removeListener(this)
                    isVisible = false
                }
            })
    }
}
