package com.github.danilopaiva.web.controller

import com.github.danilopaiva.api.CustomerAccountApi
import com.github.danilopaiva.command.handler.AccountCommandHandler
import com.github.danilopaiva.domain.Account
import com.github.danilopaiva.domain.repository.AccountRepository
import com.github.danilopaiva.domain.repository.MockAccountRepository
import com.github.danilopaiva.web.helper.toCommand
import com.github.danilopaiva.web.helper.toResponse
import com.github.danilopaiva.api.request.CustomerAccountRequest
import com.github.danilopaiva.api.response.CustomerAccountResponse
import org.springframework.web.bind.annotation.PathVariable
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

    private val accountRepository: AccountRepository = MockAccountRepository()

    override fun create(@RequestBody @Valid request: CustomerAccountRequest): CustomerAccountResponse {
        val command = request.toCommand()
        return accountCommandHandler.handler(command).toResponse()
    }

    override fun find(@PathVariable("accountId") accountId: String): CustomerAccountResponse {
        return accountRepository.find(Account.Id(accountId)).toResponse()
    }
}
