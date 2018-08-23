package com.github.danilopaiva.api

import com.github.danilopaiva.request.DepositRequest
import com.github.danilopaiva.response.DepositResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
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
}