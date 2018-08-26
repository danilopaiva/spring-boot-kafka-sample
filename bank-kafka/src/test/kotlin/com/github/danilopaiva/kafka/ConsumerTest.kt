package com.github.danilopaiva.kafka

import com.github.danilopaiva.domain.Deposit
import org.junit.Test
import java.util.concurrent.TimeUnit
import kotlin.test.assertEquals

open class ConsumerTest : KafkaBaseTest() {

    @Test
    fun testReceive() {
        val deposit = Deposit(
            id = Deposit.Id(),
            amount = Deposit.Amount(100.0)
        )
        depositProducer.send(deposit)

        TimeUnit.SECONDS.sleep(2L)

        assertEquals(0, consumer.getLatch().count)
    }
}
