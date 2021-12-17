import org.threeten.bp.LocalDate

data class TipHistory(
    val paymentDate: LocalDate,
    val payment: Double,
    val tipAmount: Double,
    val receiptImageUriPath: String?,
)
