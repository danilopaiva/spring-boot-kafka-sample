package com.github.danilopaiva.controller

import com.github.danilopaiva.api.DepositApi
import com.github.danilopaiva.request.DepositRequest
import com.github.danilopaiva.response.DepositResponse
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.validation.Valid

/**
 * Deposit controller
 */
@RestController
class DepositController : DepositApi {

    override fun create(@RequestBody @Valid request: DepositRequest): DepositResponse =
        DepositResponse(
            transactionId = UUID.randomUUID().toString(),
            status = "PROCESSING"
        )
}
