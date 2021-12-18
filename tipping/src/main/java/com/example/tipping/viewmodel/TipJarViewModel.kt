package com.example.tipping.viewmodel

import TipHistory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.presentation.event.Event
import com.example.presentation.event.postEvent
import com.example.presentation.util.combineLatest
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneOffset
import timber.log.Timber

interface TipJarViewModel {
    val savePaymentButtonEnabled: LiveData<Boolean>
    val paymentAmount: MutableLiveData<String>
    val tipPercentage: MutableLiveData<String>
    val peopleCount: MutableLiveData<Int>
    val receiptPhotoIsChecked: MutableLiveData<Boolean>
    val navigateToCamera: MutableLiveData<Event<Unit>>
    val navigateToReceiptList: MutableLiveData<Event<Unit>>
    val totalTip: LiveData<Double>
    val totalTipPerPerson: LiveData<Double>

    fun historyButtonClicked()
    fun incrementPeople()
    fun decrementPeople()
    fun savePaymentClicked()
    fun onReturnFromCamera(success: Boolean, imagePath: String)
}

class TipJarViewModelImpl(
    // private val useCase: useCase
) : TipJarViewModel, ViewModel() {

    override val paymentAmount = MutableLiveData<String>("")
    override val tipPercentage = MutableLiveData<String>("")

    override val receiptPhotoIsChecked = MutableLiveData(false)
    override val savePaymentButtonEnabled =
        combineLatest(paymentAmount, tipPercentage) { amt, percent ->
            !amt.isNullOrEmpty() && !percent.isNullOrEmpty()
        }

    override val peopleCount = MutableLiveData<Int>(MINIUMUM_PERSON_COUNT)

    override val navigateToCamera = MutableLiveData<Event<Unit>>()
    override val navigateToReceiptList = MutableLiveData<Event<Unit>>()
    override val totalTip = combineLatest(paymentAmount, tipPercentage) {
        amt, pct ->
        calculateTotalTip(amt, pct)
    }

    private fun calculateTotalTip(amt: String?, pct: String?): Double {
        return try {
            if (amt != null && pct != null) {
                (amt.toDouble() * Integer.parseInt(pct) / 100)
            } else {
                0.00
            }
        } catch (exception: NumberFormatException) {
            Timber.e(exception)
            0.00
        }
    }

    private fun calculateTipPerPerson(tip: Double, peopleCount: Int): Double {
        return tip / peopleCount
    }

    override val totalTipPerPerson = combineLatest(totalTip, peopleCount) {
        tip, people ->
        if (people != null && tip != null) {
            calculateTipPerPerson(tip, people)
        } else 0.00
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
            navigateToReceiptList.postEvent(Unit)
        }
    }

    override fun onReturnFromCamera(success: Boolean, imagePath: String) {
        if (success) {
            savePaymentToDB(imagePath)
        }
    }

    private fun savePaymentToDB(imagePath: String?) {
        val paymentAmount = paymentAmount.value
        val tipAmount = totalTip.value
        if (paymentAmount != null && tipAmount != null) {
            val tipHistory = TipHistory(
                paymentDate = LocalDate.now(ZoneOffset.UTC),
                payment = paymentAmount.toDouble(),
                tipAmount = tipAmount,
                receiptImageUriPath = imagePath,
            )
        }
        // TODO save to DB and on completion, navigate to history page
        // navigateToReceiptList.postEvent(Unit)
    }

    companion object {
        const val MINIUMUM_PERSON_COUNT = 1
    }
}
