package com.juniorrodrigues.credit.application.system.dto

import com.juniorrodrigues.credit.application.system.entity.Credit
import com.juniorrodrigues.credit.application.system.entity.Customer
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(
    val creditValue: BigDecimal,
    val dayFirstInstallment: LocalDate,
    val numberOfInstallments: Int,
    val customerId: Long
) {
    fun toEntity(): Credit = Credit(
        creditValue = this.creditValue,
        dayFirstInstallment = this.dayFirstInstallment,
        numberOfInstalments = this.numberOfInstallments,
        customer = Customer(id = this.customerId)
    )
}
