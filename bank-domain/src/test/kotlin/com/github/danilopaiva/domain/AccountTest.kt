package com.github.danilopaiva.domain

import com.github.danilopaiva.domain.config.BaseTest
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class AccountTest : BaseTest() {

    @Test
    fun `should create an account`() {
        createAnAccount()
    }

    @Test
    fun `should do a deposit`() {
        val account = createAnAccount()
        val deposit = dummyDeposit(Deposit.Amount(10.0))
        assertNotNull(deposit.id)

        account.deposit(deposit, repository)
        verify(repository, times(1)).deposit(account, deposit)

        assertEquals(10.0, account.balance.value)
    }
}