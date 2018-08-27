package com.github.danilopaiva.web.helper

import com.github.danilopaiva.command.CreateCustomerAccount
import com.github.danilopaiva.command.RegisterDeposit
import com.github.danilopaiva.domain.Account
import com.github.danilopaiva.domain.Deposit
import com.github.danilopaiva.api.request.CustomerAccountRequest
import com.github.danilopaiva.api.request.DepositRequest

fun CustomerAccountRequest.toCommand() =
    CreateCustomerAccount(
        document = Account.Document(
            name = Account.Document.Name(this.document!!.name!!),
            type = Account.Document.Type.valueOf(this.document!!.type!!),
            number = Account.Document.Number(this.document!!.number!!)
        )
    )

fun DepositRequest.toCommand() =
    RegisterDeposit(
        accountId = Account.Id(this.accountId!!),
        amount = Deposit.Amount(this.amount!!)
    )