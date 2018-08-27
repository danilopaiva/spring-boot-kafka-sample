package com.github.danilopaiva.api

import com.github.danilopaiva.api.request.CustomerAccountRequest
import com.github.danilopaiva.api.response.CustomerAccountResponse
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import javax.validation.Valid

/**
 * Account API
 */
@RequestMapping("/accounts")
interface CustomerAccountApi {

    /**
     * Create a customer account
     */
    @ResponseStatus(CREATED)
    @ResponseBody
    @PostMapping(consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
    fun create(@RequestBody @Valid request: CustomerAccountRequest): CustomerAccountResponse

    /**
     * Find an account
     * @param accountId Account Id
     */
    @ResponseStatus(OK)
    @ResponseBody
    @GetMapping("/{accountId}", produces = [APPLICATION_JSON_VALUE])
    fun find(@PathVariable("accountId") accountId: String): CustomerAccountResponse
}