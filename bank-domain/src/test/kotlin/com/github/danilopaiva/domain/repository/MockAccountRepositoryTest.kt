package com.github.danilopaiva.domain.repository

import com.github.danilopaiva.domain.config.BaseTest
import org.junit.Test
import kotlin.test.assertEquals

class MockAccountRepositoryTest : BaseTest() {

    private val accountRepository: AccountRepository =
        MockAccountRepository()

    @Test
    fun `should save an account`() {
        val account = dummyAccount()
        val accountCreated = accountRepository.save(account)
        assertEquals(account.id, accountCreated.id)
    }
}