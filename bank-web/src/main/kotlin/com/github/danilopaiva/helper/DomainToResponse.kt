package com.github.danilopaiva.helper

import com.github.danilopaiva.domain.Account
import com.github.danilopaiva.domain.Deposit
import com.github.danilopaiva.response.CustomerAccountResponse
import com.github.danilopaiva.response.DepositResponse

fun Account.toResponse() =
    CustomerAccountResponse(
        accountId = this.id.value,
        amount = this.balance.value,
        document = this.document.toDocumentResponse(),
        status = this.status.name,
        createdAt = this.createdAt
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