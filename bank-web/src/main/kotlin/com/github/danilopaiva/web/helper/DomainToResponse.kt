package com.github.danilopaiva.web.helper

import com.github.danilopaiva.domain.Account
import com.github.danilopaiva.domain.Deposit
import com.github.danilopaiva.api.response.CustomerAccountResponse
import com.github.danilopaiva.api.response.DepositResponse

fun Account.toResponse() =
    CustomerAccountResponse(
        accountId = this.id.value,
        amount = this.balance.value,
        document = this.document.toDocumentResponse(),
        status = this.status.name,
        createdAt = this.createdAt,
        deposits = this.deposits.map {
            DepositResponse(
                transactionId = it.value.id.value,
                status = it.value.status.name,
                createdAt = it.value.createdAt
            )
        }
    )

fun Deposit.toResponse() =
    DepositResponse(
        transactionId = this.id.value,
        createdAt = this.createdAt,
        status = this.status.name
    )

private fun Account.Document.toDocumentResponse() =
    CustomerAccountResponse.DocumentRepresentation(
        name = this.name.value,
        number = this.number.value,
        type = this.type.name
    )