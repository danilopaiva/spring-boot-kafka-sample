package com.github.danilopaiva.domain.repository

import com.github.danilopaiva.domain.Account
import com.github.danilopaiva.domain.Deposit
import java.util.*

class MockAccountRepository : AccountRepository {

    companion object {
        private val accounts = hashMapOf<Account.Id, Account>()
    }

    override fun save(account: Account): Account {
        accounts[account.id] = account

        return account
    }

    override fun deposit(account: Account, deposit: Deposit): Account {
        account.deposits[deposit.id] = deposit
        accounts[account.id] = account
        return account
    }

    override fun find(accountId: Account.Id) =
        Optional.ofNullable(accounts[accountId])
            .orElseThrow {
                Exception("AccountId $accountId not found!")
            }

    override fun findDeposit(depositId: Deposit.Id, accountId: Account.Id): Deposit {
        val account = find(accountId)
        return Optional.ofNullable(account.deposits[depositId])
            .orElseThrow {
                Exception("AccountId $accountId not found!")
            }
    }
}