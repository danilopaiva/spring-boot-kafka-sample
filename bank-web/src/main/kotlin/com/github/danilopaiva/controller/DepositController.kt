package com.github.danilopaiva.controller

import com.github.danilopaiva.api.DepositApi
import com.github.danilopaiva.command.handler.AccountCommandHandler
import com.github.danilopaiva.helper.toCommand
import com.github.danilopaiva.helper.toResponse
import com.github.danilopaiva.request.DepositRequest
import com.github.danilopaiva.response.DepositResponse
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 * Deposit controller
 */
@RestController
class DepositController constructor(
    private val accountCommandHandler: AccountCommandHandler
) : DepositApi {

    override fun create(@RequestBody @Valid request: DepositRequest): DepositResponse {
        return request.toCommand()
            .run { accountCommandHandler.handler(this) }
            //.apply { depositProducer.send(this) }
            .run { this.toResponse() }

    }
}
