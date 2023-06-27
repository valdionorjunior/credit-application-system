package com.juniorrodrigues.credit.application.system.controller

import com.juniorrodrigues.credit.application.system.dto.CustomerDto
import com.juniorrodrigues.credit.application.system.dto.CustomerUpdateDto
import com.juniorrodrigues.credit.application.system.dto.CustomerViewDto
import com.juniorrodrigues.credit.application.system.entity.Customer
import com.juniorrodrigues.credit.application.system.service.impl.CustomerService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/customers")
class CustomerController(private val customerService: CustomerService) {

    @PostMapping
    fun saveCustomer(@RequestBody @Valid customerDto: CustomerDto): ResponseEntity<String> {

        val savedCustomer = this.customerService.save(customerDto.toEntity())

        return ResponseEntity.status(HttpStatus.CREATED).body("Customer ${savedCustomer.email} saved!")
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<CustomerViewDto> {
        val customer: Customer = this.customerService.findById(id)
        return ResponseEntity.status(HttpStatus.OK).body(CustomerViewDto(customer))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustomer(@PathVariable id: Long) = this.customerService.delete(id)

    @PatchMapping
    fun updateCustomer(
        @RequestParam(value = "customerId") id: Long,
        @RequestBody @Valid customerUpdateDto: CustomerUpdateDto
    ): ResponseEntity<CustomerViewDto> {

        val customer: Customer = this.customerService.findById(id)
        val customerToUPdate = customerUpdateDto.toEntity(customer)

        val costumerUpdateDto = this.customerService.save(customerToUPdate)
        return ResponseEntity.status(HttpStatus.OK).body(CustomerViewDto(costumerUpdateDto))
    }
}