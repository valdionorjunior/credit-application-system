package com.juniorrodrigues.credit.application.system.controller

import com.juniorrodrigues.credit.application.system.dto.CustomerDto
import com.juniorrodrigues.credit.application.system.dto.CustomerUpdateDto
import com.juniorrodrigues.credit.application.system.dto.CustomerViewDto
import com.juniorrodrigues.credit.application.system.entity.Customer
import com.juniorrodrigues.credit.application.system.service.impl.CustomerService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/customers")
class CustomerController(private val customerService: CustomerService) {

    @PostMapping
    fun saveCustomer(@RequestBody customerDto: CustomerDto):String {

        val savedCustomer = this.customerService.save(customerDto.toEntity())

        return "Customer ${savedCustomer.email} saved!"
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long) : CustomerViewDto {
        val customer: Customer = this.customerService.findById(id)
        return CustomerViewDto(customer)
    }

    @DeleteMapping("/{id}")
    fun deleteCustomer(@PathVariable id: Long) = this.customerService.delete(id)

    @PatchMapping
    fun updateCustomer(@RequestParam(value = "customerId") id: Long,
                       @RequestBody customerUpdateDto: CustomerUpdateDto): CustomerViewDto{

        val customer: Customer = this.customerService.findById(id)
        val customerToUPdate = customerUpdateDto.toEntity(customer)

        val costumerUpdateD = this.customerService.save(customerToUPdate)
        return CustomerViewDto(costumerUpdateD)
    }
}