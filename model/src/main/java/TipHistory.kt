import java.util.Date

data class TipHistory(
    val id: Int,
    val paymentDate: Date,
    val payment: Double,
    val tipAmount: Double,
    val receiptImageUriPath: String?,
)
