package com.github.danilopaiva.domain.repository

import com.github.danilopaiva.domain.Account
import com.github.danilopaiva.domain.Deposit

interface AccountRepository {

    fun save(account: Account): Account

    fun deposit(account: Account, deposit: Deposit): Account

    fun find(accountId: Account.Id): Account

    fun findDeposit(depositId: Deposit.Id, accountId: Account.Id): Deposit
}