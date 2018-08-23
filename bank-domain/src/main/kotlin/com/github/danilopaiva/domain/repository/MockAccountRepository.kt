package com.github.danilopaiva.domain.repository

import com.github.danilopaiva.domain.Account
import com.github.danilopaiva.domain.Deposit
import java.util.*

class MockAccountRepository : AccountRepository {

    companion object {
        private val accounts = hashMapOf<String, Account>()
    }

    override fun save(account: Account): Account {
        val id = account.id.value
        accounts[id] = account

        return account
    }

    override fun deposit(account: Account, deposit: Deposit): Account {
        account.deposits.add(deposit)
        accounts[account.id.value] = account
        return account
    }

    override fun find(accountId: Account.Id) =
        Optional.ofNullable(accounts[accountId.value])
            .orElseThrow {
                Exception()
            }

}