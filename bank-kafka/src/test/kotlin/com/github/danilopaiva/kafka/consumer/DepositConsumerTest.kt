package com.github.danilopaiva.kafka.consumer

import com.github.danilopaiva.kafka.startup.KafkaBaseTest
import com.github.danilopaiva.domain.Account
import com.github.danilopaiva.domain.Deposit
import com.github.danilopaiva.domain.extension.objectToJson
import org.junit.Test
import java.util.concurrent.TimeUnit

open class DepositConsumerTest : KafkaBaseTest() {

    @Test
    fun `should consume`() {
        val key = "DEPOSIT-TEST"
        val account = Account(
            id = Account.Id(),
            document = Account.Document(
                Account.Document.Name(""),
                Account.Document.Type.CPF,
                Account.Document.Number("")
            )
        )
        val deposit = Deposit(
            accountId = account.id,
            amount = Deposit.Amount(0.0)
        )

        kafkaTemplate.send(bankDepositsTopic, key, deposit.objectToJson())

        TimeUnit.SECONDS.sleep(5L)
    }
}
