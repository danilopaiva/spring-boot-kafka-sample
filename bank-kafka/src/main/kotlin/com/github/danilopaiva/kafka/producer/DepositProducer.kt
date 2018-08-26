package com.github.danilopaiva.kafka.producer

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.danilopaiva.domain.Deposit
import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
data class DepositProducer(
    @Value("\${kafka.bank.deposit.topic.events}") private val topic: String,
    private val callback: ProducerCallback
) {

    companion object {
        private val LOG = LogManager.getLogger(DepositProducer::class)
        private const val MESSAGE_KEY = "DEPOSIT"
    }

    @Autowired
    lateinit var kafkaTemplate: KafkaTemplate<String, String>

    private val objectMapper: ObjectMapper = ObjectMapper()

    fun send(deposit: Deposit) {
        LOG.info("sending deposit $deposit to topic $topic")
        kafkaTemplate
            .send(topic, MESSAGE_KEY, objectMapper.writeValueAsString(deposit))
            .addCallback(callback)
    }
}