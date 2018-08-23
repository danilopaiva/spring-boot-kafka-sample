package com.github.danilopaiva.helper

import com.github.danilopaiva.domain.Account
import com.github.danilopaiva.response.CustomerAccountResponse

fun Account.toResponse() =
    CustomerAccountResponse(
        accountId = this.id.value,
        amount = this.amount.value,
        document = this.document.toDocumentResponse(),
        status = this.status.name,
        createdAt = this.createdAt
    )

private fun Account.Document.toDocumentResponse() =
    CustomerAccountResponse.DocumentRepresentation(
        name = this.name.value,
        number = this.number.value,
        type = this.type.name
    )