package com.github.danilopaiva.web.controller

import com.github.danilopaiva.api.DepositApi
import com.github.danilopaiva.command.handler.AccountCommandHandler
import com.github.danilopaiva.domain.Account
import com.github.danilopaiva.domain.Deposit
import com.github.danilopaiva.domain.repository.AccountRepository
import com.github.danilopaiva.domain.repository.MockAccountRepository
import com.github.danilopaiva.web.helper.toCommand
import com.github.danilopaiva.web.helper.toResponse
import com.github.danilopaiva.web.producer.DepositProducer
import com.github.danilopaiva.api.request.DepositRequest
import com.github.danilopaiva.api.response.DepositResponse
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 * Deposit controller
 */
@RestController
class DepositController constructor(
    private val accountCommandHandler: AccountCommandHandler,
    private val depositProducer: DepositProducer
) : DepositApi {

    private val accountRepository: AccountRepository = MockAccountRepository()

    override fun create(@RequestBody @Valid request: DepositRequest): DepositResponse {
        return request.toCommand()
            .run { accountCommandHandler.handler(this) }
            .apply { depositProducer.send(this) }
            .run { this.toResponse() }
    }

    override fun find(
        @PathVariable("depositId") depositId: String,
        @PathVariable("accountId") accountId: String
    ): DepositResponse {
        return accountRepository.findDeposit(Deposit.Id(depositId), Account.Id(accountId)).toResponse()
    }
}
