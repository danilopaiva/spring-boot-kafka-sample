package com.github.danilopaiva.command.handler

import com.github.danilopaiva.command.CreateCustomerAccount
import com.github.danilopaiva.command.CreateDeposit
import com.github.danilopaiva.domain.Account
import com.github.danilopaiva.domain.Deposit
import com.github.danilopaiva.domain.repository.AccountRepository
import com.github.danilopaiva.domain.repository.MockAccountRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class AccountCommandHandler {

    private val accountRepository: AccountRepository = MockAccountRepository()

    fun handler(command: CreateCustomerAccount): Account {
        val account = Account(
            id = Account.Id(),
            document = command.document
        )

        account.create(accountRepository)

        return account
    }

    fun handler(command: CreateDeposit): Deposit {
        val account = findAccount(command.accountId)

        val deposit = Deposit(
            id = Deposit.Id(),
            amount = command.amount
        )

        account.deposit(deposit, accountRepository)

        return deposit
    }

    private fun findAccount(accountId: Account.Id) =
        Optional.ofNullable(accountRepository.find(accountId))
            .orElseThrow {
                Exception()
            }

}