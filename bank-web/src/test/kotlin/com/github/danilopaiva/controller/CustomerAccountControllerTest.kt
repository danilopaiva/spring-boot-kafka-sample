package com.github.danilopaiva.controller

import com.github.danilopaiva.config.ControllerBaseTest
import com.github.danilopaiva.domain.Account
import com.github.danilopaiva.extension.jsonToObject
import com.github.danilopaiva.extension.objectToJson
import com.github.danilopaiva.response.CustomerAccountResponse
import org.junit.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


class CustomerAccountControllerTest : ControllerBaseTest() {

    @Test
    fun `should create a customer account`() {
        val customerAccount = getCustomerAccountRequest()

        this.mockMvc.perform(
            post("/accounts")
                .content(customerAccount.objectToJson())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
            .andExpect(status().isCreated)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect {
                it.response.contentAsString.jsonToObject(CustomerAccountResponse::class.java).run {
                    assertNotNull(accountId)
                    assertNotNull(createdAt)
                    assertNotNull(status)
                    assertNotNull(createdAt)
                    assertEquals(customerAccount.document?.name, document.name)
                    assertEquals(customerAccount.document?.number, document.number)
                    assertEquals(customerAccount.document?.type, document.type)
                    assertEquals(Account.Status.ACTIVE.name, status)
                    assertEquals(0.0, amount)
                }
            }
    }
}
