@file:Suppress("IllegalIdentifier")

package com.example.data.datasource.model

import com.example.data.daoHistoryItem
import com.example.data.repository.toTipHistory
import org.junit.Assert.assertTrue
import org.junit.Test

class TipHistoryEntityMappingTest {

    @Test
    fun `map entity to domain`() {
        // given

        // when
        val tipHistoryItem = daoHistoryItem.toTipHistory()
        // then
        assertTrue(
            tipHistoryItem.paymentDate ==
                daoHistoryItem.paymentDate
        )
        assertTrue(
            tipHistoryItem.payment ==
                daoHistoryItem.payment
        )
        assertTrue(
            tipHistoryItem.tipAmount ==
                daoHistoryItem.tipAmount
        )
        assertTrue(
            tipHistoryItem.receiptImageUriPath ==
                daoHistoryItem.receiptImageUriPath
        )
    }
}
