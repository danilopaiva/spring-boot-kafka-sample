package com.github.danilopaiva.controller

import com.github.danilopaiva.api.CustomerAccountApi
import com.github.danilopaiva.command.AccountCommandHandler
import com.github.danilopaiva.helper.toCommand
import com.github.danilopaiva.helper.toResponse
import com.github.danilopaiva.request.CustomerAccountRequest
import com.github.danilopaiva.response.CustomerAccountResponse
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 * Customer account controller
 */
@RestController
class CustomerAccountController constructor(
    private val accountCommandHandler: AccountCommandHandler
) : CustomerAccountApi {

    override fun create(@RequestBody @Valid request: CustomerAccountRequest): CustomerAccountResponse {
        val command = request.toCommand()
        return accountCommandHandler.handler(command).toResponse()
    }
}
