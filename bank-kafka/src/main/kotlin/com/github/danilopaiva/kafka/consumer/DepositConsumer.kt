package com.github.danilopaiva.kafka.consumer

import com.github.danilopaiva.command.DoDeposit
import com.github.danilopaiva.command.handler.AccountCommandHandler
import com.github.danilopaiva.domain.Deposit
import com.github.danilopaiva.domain.extension.jsonToObject
import org.apache.logging.log4j.LogManager
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class DepositConsumer(private val accountCommandHandler: AccountCommandHandler) {

    companion object {
        private val LOG = LogManager.getLogger(DepositConsumer::class)
    }

    @KafkaListener(topics = ["\${kafka.bank.deposits.topic}"], groupId = "deposit")
    fun onReceive(
        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) key: String,
        @Payload payload: String
    ) {
        val deposit = payload.jsonToObject(Deposit::class.java)
        deposit.run { DoDeposit(this) }
            .apply { accountCommandHandler.handler(this) }
            .also { LOG.info("Event to deposit processed! $deposit") }
    }
}