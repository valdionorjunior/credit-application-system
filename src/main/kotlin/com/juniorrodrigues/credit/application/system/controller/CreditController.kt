package com.juniorrodrigues.credit.application.system.controller

import com.juniorrodrigues.credit.application.system.dto.CreditDto
import com.juniorrodrigues.credit.application.system.dto.CreditViewDto
import com.juniorrodrigues.credit.application.system.dto.CreditViewListDto
import com.juniorrodrigues.credit.application.system.entity.Credit
import com.juniorrodrigues.credit.application.system.service.impl.CreditService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import java.util.stream.Collectors


@RestController
@RequestMapping("/api/credits")
class CreditController(
    private val creditService: CreditService
){

    @PostMapping
    fun savaCredt(@RequestBody creditDto: CreditDto): String{
        val credit: Credit =this.creditService.save(creditDto.toEntity())
        return "Credit  ${credit.creditCode} - Customer ${credit.customer?.firstName} saved!"
    }

    @GetMapping
    fun findAllByCsutomerId(@RequestParam(value = "customerId") customerId: Long): List<CreditViewListDto>{
        return this.creditService.findAllByCustomer(customerId).stream()
            .map { credit: Credit -> CreditViewListDto(credit)}
            .collect(Collectors.toList())
    }

    @GetMapping("/{creditCode}")
    fun findByCreditCode(@RequestParam(value = "customerId") customerId: Long,
                         @PathVariable creditCode: UUID): CreditViewDto {

        val credit: Credit = this.creditService.findByCreditCode(customerId, creditCode)
        return CreditViewDto(credit)

    }
}