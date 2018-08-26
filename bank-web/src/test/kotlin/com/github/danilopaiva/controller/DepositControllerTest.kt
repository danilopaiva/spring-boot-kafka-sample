package com.github.danilopaiva.controller

import com.github.danilopaiva.config.ControllerBaseTest
import com.github.danilopaiva.domain.Deposit
import com.github.danilopaiva.extension.jsonToObject
import com.github.danilopaiva.extension.objectToJson
import com.github.danilopaiva.response.DepositResponse
import org.junit.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


class DepositControllerTest : ControllerBaseTest() {

    @Test
    fun `should register a deposit`() {
        val accountId = createAccountCustomer()
        val deposit = createADeposit(accountId)

        this.mockMvc.perform(
            post("/deposits")
                .content(deposit.objectToJson())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect {
                it.response.contentAsString.jsonToObject(DepositResponse::class.java).run {
                    assertNotNull(transactionId)
                    assertNotNull(status)
                    assertNotNull(createdAt)
                    assertEquals(Deposit.Status.PROCESSING.name, status)
                }
            }
    }
}
