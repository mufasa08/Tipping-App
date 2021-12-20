package com.example.presentation.viewmodel

import androidx.lifecycle.LiveData

interface ErrorViewModel {
    val errorViewVisible: LiveData<Boolean>
}
