package com.mdualeh.presentation.viewmodel

import androidx.lifecycle.LiveData

interface ErrorViewModel {
    val errorViewVisible: LiveData<Boolean>
}
