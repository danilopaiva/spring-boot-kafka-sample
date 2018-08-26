package com.github.danilopaiva.kafka.consumer

import org.apache.logging.log4j.LogManager
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component
import java.util.concurrent.CountDownLatch

@Component
class Consumer {

    companion object {
        private val LOG = LogManager.getLogger(Consumer::class)
    }

    private val latch = CountDownLatch(1)

    fun getLatch() = latch

    @KafkaListener(topics = ["\${kafka.bank.deposit.topic.events}"], groupId = "deposit")
    fun onReceive(
        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) key: String,
        @Payload payload: String
    ) {
        LOG.info("\n\nKey received = $key \n\n")
        LOG.info("received payload= $payload")
        latch.countDown()
    }
}