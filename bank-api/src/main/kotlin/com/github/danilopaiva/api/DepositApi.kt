package com.github.danilopaiva.api

import com.github.danilopaiva.api.request.DepositRequest
import com.github.danilopaiva.api.response.DepositResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import javax.validation.Valid

/**
 * Deposit API
 */
@RequestMapping("/deposits")
interface DepositApi {

    /**
     * Create a deposit to customer
     */
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @PostMapping(consumes = [(MediaType.APPLICATION_JSON_VALUE)], produces = [(MediaType.APPLICATION_JSON_VALUE)])
    fun create(@RequestBody @Valid request: DepositRequest): DepositResponse

    /**
     * Find a deposit
     * @param depositId Deposit Id
     * @param accountId Account Id
     */
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/{depositId}/accounts/{accountId}", produces = [(MediaType.APPLICATION_JSON_VALUE)])
    fun find(
        @PathVariable("depositId") depositId: String,
        @PathVariable("accountId") accountId: String
    ): DepositResponse
}