package com.example.tipping.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.data.repository.TipHistoryRepository
import com.example.presentation.event.Event
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class TipJarViewModelImplTest {

    @get:Rule
    val instantTestExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private var paymentAmountObserver: Observer<String> = mockk(relaxed = true)
    private var tipPercentageObserver: Observer<String> = mockk(relaxed = true)
    private var imagePathObserver: Observer<String> = mockk(relaxed = true)
    private var receiptPhotoIsCheckedObserver: Observer<Boolean> = mockk(relaxed = true)
    private var savePaymentButtonEnabledObserver: Observer<Boolean> = mockk(relaxed = true)
    private var peopleCountObserver: Observer<Int> = mockk(relaxed = true)
    private var totalTipObserver: Observer<String> = mockk(relaxed = true)
    private var totalTipPerPersonObserver: Observer<String> = mockk(relaxed = true)

    private var navigateToReceiptListObserver: Observer<Event<Unit>> = mockk(relaxed = true)
    private var navigateToCameraObserver: Observer<Event<Unit>> = mockk(relaxed = true)

    private var repository: TipHistoryRepository = mockk(relaxed = true)
    private lateinit var viewModel: TipJarViewModelImpl

    @Before
    fun setUp() {
        viewModel = TipJarViewModelImpl(tipHistoryRepository = repository)
        viewModel.paymentAmount.observeForever(paymentAmountObserver)
        viewModel.tipPercentage.observeForever(tipPercentageObserver)
        viewModel.imagePath.observeForever(imagePathObserver)
        viewModel.receiptPhotoIsChecked.observeForever(receiptPhotoIsCheckedObserver)
        viewModel.savePaymentButtonEnabled.observeForever(savePaymentButtonEnabledObserver)
        viewModel.peopleCount.observeForever(peopleCountObserver)
        viewModel.totalTip.observeForever(totalTipObserver)
        viewModel.totalTipPerPerson.observeForever(totalTipPerPersonObserver)
        viewModel.navigateToReceiptList.observeForever(navigateToReceiptListObserver)
        viewModel.navigateToCamera.observeForever(navigateToCameraObserver)
    }

    @Test
    fun historyButtonClicked() {
        viewModel.historyButtonClicked()
        verify(exactly = 1) { navigateToReceiptListObserver.onChanged(any()) }
    }

    @Test
    fun incrementPeople() {
        /* Given */
        verify(exactly = 1) { peopleCountObserver.onChanged(1) }

        /* When */
        viewModel.incrementPeople()

        /* Then */
        verify(exactly = 1) { peopleCountObserver.onChanged(2) }
    }

    @Test
    fun `decrementPeople after incrementing`() {
        viewModel.incrementPeople()
        verify { peopleCountObserver.onChanged(2) }
        viewModel.decrementPeople()
        verify { peopleCountObserver.onChanged(1) }
    }

    @Test
    fun `decrementPeople does not go below 1`() {
        verify(exactly = 1) { peopleCountObserver.onChanged(1) }
        viewModel.decrementPeople()
        verify(exactly = 1) { peopleCountObserver.onChanged(1) }
    }

    @Test
    fun `savePaymentClicked navigates to camera if receiptPhoto Is Checked`() {
        /* Given */
        viewModel.receiptPhotoIsChecked.value = true

        /* When */
        viewModel.savePaymentClicked()

        /* Then */
        verify(exactly = 1) { navigateToCameraObserver.onChanged(any()) }
    }

    @Test
    fun `onReturnFromCamera calls save to db with path if success is true`() {
        /* Given */
        val path = "#PATH"
        viewModel.imagePath.value = path
        viewModel.paymentAmount.value = "100.0"
        viewModel.totalTip.value = "0.0"

        /* When */
        viewModel.onReturnFromCamera(success = true)

        /* Then */
        coVerify(exactly = 1) { repository.addTipHistory(any(), 100.0, 0.0, path) }
        verify(exactly = 1) { navigateToReceiptListObserver.onChanged(any()) }
    }

    @Test
    fun `onReturnFromCamera calls save to db with NO path if success is false`() {
        /* Given */
        val path = "#PATH"
        viewModel.imagePath.value = path
        viewModel.paymentAmount.value = "100.0"
        viewModel.totalTip.value = "0.0"

        /* When */
        viewModel.onReturnFromCamera(success = false)

        /* Then */
        coVerify(exactly = 1) { repository.addTipHistory(any(), 100.0, 0.0, null) }
        verify(exactly = 1) { navigateToReceiptListObserver.onChanged(any()) }
    }

    @Test
    fun calculateTips() {
        // Normal case
        /* Given */
        viewModel.paymentAmount.value = "200.0"
        viewModel.tipPercentage.value = "10"
        viewModel.peopleCount.value = 2

        /* When */
        viewModel.calculateTips()

        /* Then */
        verify { totalTipObserver.onChanged("20.0") }
        verify { totalTipPerPersonObserver.onChanged("10.0") }

        // Percentage is 0
        /* Given */
        viewModel.tipPercentage.value = "0"

        /* When */
        viewModel.calculateTips()

        /* Then */
        verify { totalTipObserver.onChanged("0.0") }
        verify { totalTipPerPersonObserver.onChanged("0.0") }

        // Percentage is empty
        /* Given */
        viewModel.tipPercentage.value = ""

        /* When */
        viewModel.calculateTips()

        /* Then */
        verify { totalTipObserver.onChanged("0.0") }
        verify { totalTipPerPersonObserver.onChanged("0.0") }

        // Payment amount is empty
        /* Given */
        viewModel.paymentAmount.value = ""
        viewModel.tipPercentage.value = "10"

        /* When */
        viewModel.calculateTips()

        /* Then */
        verify { totalTipObserver.onChanged("0.0") }
        verify { totalTipPerPersonObserver.onChanged("0.0") }

        // Payment amount is 0
        /* Given */
        viewModel.paymentAmount.value = "0.00"
        viewModel.tipPercentage.value = "10"

        /* When */
        viewModel.calculateTips()

        /* Then */
        verify { totalTipObserver.onChanged("0.0") }
        verify { totalTipPerPersonObserver.onChanged("0.0") }
    }
}
