package com.github.danilopaiva.web.controller

import com.github.danilopaiva.web.startup.ControllerBaseTest
import com.github.danilopaiva.domain.Deposit
import com.github.danilopaiva.domain.extension.jsonToObject
import com.github.danilopaiva.domain.extension.objectToJson
import com.github.danilopaiva.api.response.DepositResponse
import org.junit.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


class DepositControllerTest : ControllerBaseTest() {

    @Test
    fun `should register a deposit`() {
        val accountId = createAnAccountCustomer()
        val deposit = getDepositRequest(accountId)

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

    @Test
    fun `should find a deposit`() {
        val accountId = createAnAccountCustomer()
        val depositId = createADeposit(accountId)

        this.mockMvc.perform(get("/deposits/{depositId}/accounts/{accountId}", depositId, accountId))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect {
                it.response.contentAsString.jsonToObject(DepositResponse::class.java).run {
                    assertEquals(depositId, transactionId)
                    assertNotNull(status)
                    assertNotNull(createdAt)
                    assertEquals(Deposit.Status.PROCESSING.name, status)
                }
            }
    }
}
