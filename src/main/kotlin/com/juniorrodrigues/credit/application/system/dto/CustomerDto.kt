package com.juniorrodrigues.credit.application.system.dto

import com.juniorrodrigues.credit.application.system.entity.Address
import com.juniorrodrigues.credit.application.system.entity.Customer
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal

data class CustomerDto(
    @field:NotEmpty(message = "Fist name cannot be empty")
    val firstName: String,

    @field:NotEmpty(message = "Last name cannot be empty")
    val lastName: String,

    @field:NotEmpty(message = "CPF name cannot be empty")
    @field:CPF(message = "Invalid CPF")
    val cpf: String,

    @field:NotNull(message = "CPF name cannot be Null")
    val income: BigDecimal,

    @field:NotEmpty(message = "Email name cannot be empty")
    @field:Email(message = "Invalid Email")
    val email: String,

    @field:NotEmpty(message = "Password name cannot be empty")
    val password: String,

    @field:NotEmpty(message = "Zip Code name cannot be empty")
    val zipCode: String,

    @field:NotEmpty(message = "Street name cannot be empty")
    val street: String
) {

    fun toEntity(): Customer = Customer(
        firstName = this.firstName,
        lastName = this.lastName,
        cpf = this.cpf,
        income = this.income,
        email = this.email,
        password = this.password,
        address = Address(
            zipCode = this.zipCode,
            street = this.street,
        )
    )
}
