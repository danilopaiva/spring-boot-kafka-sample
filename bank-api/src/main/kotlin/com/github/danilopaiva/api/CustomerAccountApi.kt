package com.github.danilopaiva.api

import com.github.danilopaiva.representation.CustomerAccountRepresentation
import com.github.danilopaiva.request.CustomerAccountRequest
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
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
    fun create(@RequestBody @Valid request: CustomerAccountRequest): CustomerAccountRepresentation
}