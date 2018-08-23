package com.github.danilopaiva.command

import com.github.danilopaiva.domain.Account

data class CreateCustomerAccount(
    val document: Account.Document
)