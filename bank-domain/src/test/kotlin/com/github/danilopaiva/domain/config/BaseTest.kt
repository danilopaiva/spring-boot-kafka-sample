package com.github.danilopaiva.domain.config

import com.github.danilopaiva.domain.Account
import com.github.danilopaiva.domain.Deposit
import com.github.danilopaiva.domain.repository.AccountRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify

abstract class BaseTest {

    protected val repository: AccountRepository = mock()

    fun dummyAccount() =
        Account(
            id = Account.Id(),
            document = Account.Document(
                name = Account.Document.Name("Danilo"),
                number = Account.Document.Number("11122233344"),
                type = enumValueOf("CPF")
            )
        )

    fun createAnAccount(): Account {
        val account = dummyAccount()
        account.create(repository)
        verify(repository, times(1)).save(account)

        return account
    }

    fun dummyDeposit(amount: Deposit.Amount = Deposit.Amount(100.0)) =
        Deposit(
            amount = amount
        )

}