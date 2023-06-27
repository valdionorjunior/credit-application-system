package com.juniorrodrigues.credit.application.system.dto

import com.juniorrodrigues.credit.application.system.entity.Credit
import com.juniorrodrigues.credit.application.system.entity.Customer
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(
    @field:NotNull(message = "Credit value name cannot be Null")
    val creditValue: BigDecimal,

    @field:Future(message = "Day first installment cannot be present or past")
    val dayFirstInstallment: LocalDate,

    @field:NotNull(message = "Number of installments name cannot be Null")
    @field:Min(1)
    @field:Max(24)
    val numberOfInstallments: Int,

    @field:NotNull(message = "Customer Id name cannot be Null")
    val customerId: Long
) {
    fun toEntity(): Credit = Credit(
        creditValue = this.creditValue,
        dayFirstInstallment = this.dayFirstInstallment,
        numberOfInstallments = this.numberOfInstallments,
        customer = Customer(id = this.customerId)
    )
}
