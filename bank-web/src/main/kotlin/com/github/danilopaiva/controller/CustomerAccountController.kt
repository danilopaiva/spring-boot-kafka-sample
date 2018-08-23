package com.github.danilopaiva.controller

import com.github.danilopaiva.api.CustomerAccountApi
import com.github.danilopaiva.representation.CustomerAccountRepresentation
import com.github.danilopaiva.request.CustomerAccountRequest
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.util.*
import javax.validation.Valid

/**
 * Customer account controller
 */
@RestController
class CustomerAccountController : CustomerAccountApi {

    companion object {
        private val accounts = hashMapOf<String, CustomerAccountRepresentation>()
    }

    override fun create(@RequestBody @Valid request: CustomerAccountRequest): CustomerAccountRepresentation {
        return createAccount(request)
    }

    private fun createAccount(request: CustomerAccountRequest): CustomerAccountRepresentation {
        val id = UUID.randomUUID().toString()
        accounts[id] = CustomerAccountRepresentation(
            accountId = id,
            name = request.name,
            document = CustomerAccountRepresentation.DocumentRepresentation(
                id = request.document?.id,
                type = request.document?.type
            ),
            status = "ACTIVATED!",
            createAt = LocalDateTime.now()
        )
        return Optional.ofNullable(accounts[id])
            .orElseThrow {
                Exception()
            }
    }
}
