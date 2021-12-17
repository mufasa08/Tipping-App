package com.mdualeh.presentation.viewmodel

import androidx.lifecycle.LiveData

interface ProgressViewModel {
    val progressViewVisible: LiveData<Boolean>
}
