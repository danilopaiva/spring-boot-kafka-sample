package com.github.danilopaiva.helper

import com.github.danilopaiva.command.CreateCustomerAccount
import com.github.danilopaiva.domain.Account
import com.github.danilopaiva.request.CustomerAccountRequest

fun CustomerAccountRequest.toCommand() =
    CreateCustomerAccount(
        document = Account.Document(
            name = Account.Document.Name(this.document!!.name!!),
            type = Account.Document.Type.valueOf(this.document!!.type!!),
            number = Account.Document.Number(this.document!!.number!!)
        )
    )