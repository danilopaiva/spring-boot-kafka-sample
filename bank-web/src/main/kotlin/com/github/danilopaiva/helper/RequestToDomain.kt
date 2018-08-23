package com.github.danilopaiva.helper

import com.github.danilopaiva.command.CreateCustomerAccount
import com.github.danilopaiva.command.CreateDeposit
import com.github.danilopaiva.domain.Account
import com.github.danilopaiva.domain.Deposit
import com.github.danilopaiva.request.CustomerAccountRequest
import com.github.danilopaiva.request.DepositRequest

fun CustomerAccountRequest.toCommand() =
    CreateCustomerAccount(
        document = Account.Document(
            name = Account.Document.Name(this.document!!.name!!),
            type = Account.Document.Type.valueOf(this.document!!.type!!),
            number = Account.Document.Number(this.document!!.number!!)
        )
    )

fun DepositRequest.toCommand() =
    CreateDeposit(
        accountId = Account.Id(this.accountId!!),
        amount = Deposit.Amount(this.amount!!)
    )