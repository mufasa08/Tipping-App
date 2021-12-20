package com.example.tipping.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.*
import com.example.data.repository.TipHistoryRepository
import com.example.presentation.event.Event
import com.example.presentation.event.postEvent
import com.example.presentation.util.combineLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.Date

interface TipJarViewModel {
    val savePaymentButtonEnabled: LiveData<Boolean>
    val paymentAmount: MutableLiveData<String>
    val tipPercentage: MutableLiveData<String>
    val peopleCount: MutableLiveData<Int>
    val receiptPhotoIsChecked: MutableLiveData<Boolean>
    val navigateToCamera: MutableLiveData<Event<Unit>>
    val navigateToReceiptList: MutableLiveData<Event<Unit>>
    val totalTip: MutableLiveData<String>
    val totalTipPerPerson: MutableLiveData<String>
    val imagePath: MutableLiveData<String>

    fun historyButtonClicked()
    fun incrementPeople()
    fun decrementPeople()
    fun savePaymentClicked()
    fun onReturnFromCamera(success: Boolean)
    fun calculateTips()
}

class TipJarViewModelImpl(
    private val tipHistoryRepository: TipHistoryRepository
) : TipJarViewModel, ViewModel(), LifecycleObserver {

    override val paymentAmount = MutableLiveData("")
    override val tipPercentage = MutableLiveData("")
    override val imagePath = MutableLiveData("")

    override val receiptPhotoIsChecked = MutableLiveData(false)
    override val savePaymentButtonEnabled =
        combineLatest(paymentAmount, tipPercentage) { amt, percent ->
            !amt.isNullOrEmpty() && !percent.isNullOrEmpty()
        }

    override val peopleCount = MutableLiveData(MINIUMUM_PERSON_COUNT)

    override val navigateToCamera = MutableLiveData<Event<Unit>>()
    override val navigateToReceiptList = MutableLiveData<Event<Unit>>()
    override val totalTip = MutableLiveData<String>("")
    override val totalTipPerPerson = MutableLiveData<String>("")

    @VisibleForTesting
    fun calculateTotalTip(amt: String?, pct: String?): Double {
        return try {
            if (amt != null && pct != null) {
                (amt.toDouble() * pct.toInt() / 100)
            } else {
                0.00
            }
        } catch (exception: NumberFormatException) {
            Timber.e(exception)
            0.00
        }
    }

    @VisibleForTesting
    fun calculateTipPerPerson(tip: String?, peopleCount: Int): Double {
        return tip?.toDouble()?.div(peopleCount) ?: 0.00
    }

    override fun historyButtonClicked() {
        navigateToReceiptList.postEvent(Unit)
    }

    override fun incrementPeople() {
        var currentCount = peopleCount.value
        if (currentCount != null) {
            peopleCount.postValue(++currentCount)
        }
    }

    override fun decrementPeople() {
        var currentCount = peopleCount.value
        if (currentCount != null) {
            if (currentCount > MINIUMUM_PERSON_COUNT)
                peopleCount.postValue(--currentCount)
        }
    }

    override fun savePaymentClicked() {
        val shouldTakePhoto = receiptPhotoIsChecked.value == true
        if (shouldTakePhoto) {
            navigateToCamera.postEvent(Unit)
        } else {
            savePaymentToDB(null)
        }
    }

    override fun onReturnFromCamera(success: Boolean) {
        val imagePath = if (success) imagePath.value else null
        savePaymentToDB(imagePath)
    }

    override fun calculateTips() {
        try {
            totalTip.value = calculateTotalTip(paymentAmount.value, tipPercentage.value).toString()
            totalTipPerPerson.value =
                calculateTipPerPerson(totalTip.value, peopleCount.value ?: 1).toString()
        } catch (ex: NumberFormatException) {
            Timber.e(ex)
        }
    }

    @VisibleForTesting
    fun savePaymentToDB(imagePath: String?) {
        val paymentAmt = paymentAmount.value
        val tipAmount = totalTip.value?.toDouble() ?: 0.00
        totalTip.value = ""
        paymentAmount.value = ""
        totalTip.value = ""
        totalTipPerPerson.value = ""
        if (paymentAmt != null) {
            viewModelScope.launch {
                try {
                    tipHistoryRepository.addTipHistory(
                        paymentDate = Date(),
                        payment = paymentAmt.toDouble(),
                        tipAmount = tipAmount,
                        receiptImageUriPath = imagePath
                    )
                    navigateToReceiptList.postEvent(Unit)
                } catch (e: Throwable) {
                    Timber.w(e)
                }
            }
        }
    }

    companion object {
        const val MINIUMUM_PERSON_COUNT = 1
    }
}
