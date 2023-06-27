package com.juniorrodrigues.credit.application.system.dto

import com.juniorrodrigues.credit.application.system.entity.Customer
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class CustomerUpdateDto(

    @field:NotEmpty(message = "Fist name cannot be empty")
    val firstName: String,

    @field:NotEmpty(message = "Last name cannot be empty")
    val lastName: String,

    @field:NotNull(message = "Income name cannot be Null")
    val income: BigDecimal,

    @field:NotEmpty(message = "Zip Code name cannot be empty")
    val zipCode: String,

    @field:NotEmpty(message = "Street name cannot be empty")
    val street: String
) {

    fun toEntity(customer: Customer): Customer {
        customer.firstName = this.firstName
        customer.lastName = this.lastName
        customer.income = this.income
        customer.address.zipCode = this.zipCode
        customer.address.street = this.street
        return customer
    }

}
