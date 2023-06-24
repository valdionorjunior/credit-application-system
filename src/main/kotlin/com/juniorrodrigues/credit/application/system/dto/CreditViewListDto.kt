package com.juniorrodrigues.credit.application.system.dto

import com.juniorrodrigues.credit.application.system.entity.Credit
import java.math.BigDecimal
import java.util.*

data class CreditViewListDto(
    val creditCode: UUID,
    val creditValue: BigDecimal,
    val numberOfInstallments: Int
) {

    constructor(credit: Credit): this(
        creditCode = credit.creditCode,
        creditValue = credit.creditValue,
        numberOfInstallments = credit.numberOfInstallments
    )

}
