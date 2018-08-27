package com.github.danilopaiva.command

import com.github.danilopaiva.domain.Account
import com.github.danilopaiva.domain.Deposit

data class CreateCustomerAccount(
    val document: Account.Document
)

data class RegisterDeposit(
    val accountId: Account.Id,
    val amount: Deposit.Amount
)

data class DoDeposit(val deposit: Deposit)