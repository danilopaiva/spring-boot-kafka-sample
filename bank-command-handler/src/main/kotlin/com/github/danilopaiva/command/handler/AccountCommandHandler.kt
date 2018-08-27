package com.github.danilopaiva.command.handler

import com.github.danilopaiva.command.CreateCustomerAccount
import com.github.danilopaiva.command.DoDeposit
import com.github.danilopaiva.command.RegisterDeposit
import com.github.danilopaiva.domain.Account
import com.github.danilopaiva.domain.Deposit
import com.github.danilopaiva.domain.repository.AccountRepository
import com.github.danilopaiva.domain.repository.MockAccountRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class AccountCommandHandler(private val accountRepository: AccountRepository = MockAccountRepository()) {

    fun handler(command: CreateCustomerAccount): Account {
        val account = Account(
            id = Account.Id(),
            document = command.document
        )

        account.create(accountRepository)

        return account
    }

    fun handler(command: RegisterDeposit): Deposit {
        val account = findAccount(command.accountId)

        val deposit = Deposit(
            id = Deposit.Id(),
            accountId = account.id,
            amount = command.amount
        )

        account.registerDeposit(deposit, accountRepository)

        return deposit
    }

    fun handler(command: DoDeposit): Deposit {
        val account = findAccount(command.deposit.accountId)

        return account.doDeposit(command.deposit, accountRepository)
    }

    private fun findAccount(accountId: Account.Id) =
        Optional.ofNullable(accountRepository.find(accountId))
            .orElseThrow {
                Exception()
            }

}