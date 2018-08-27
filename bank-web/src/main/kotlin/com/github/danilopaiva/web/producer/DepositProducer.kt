package com.github.danilopaiva.web.producer

import com.github.danilopaiva.domain.Deposit
import com.github.danilopaiva.domain.extension.objectToJson
import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
data class DepositProducer(
    @Value("\${kafka.bank.deposits.topic}") private val topic: String,
    private val callback: ProducerCallback
) {

    companion object {
        private val LOG = LogManager.getLogger(DepositProducer::class)
        private const val MESSAGE_KEY = "DEPOSIT"
    }

    @Autowired
    lateinit var kafkaTemplate: KafkaTemplate<String, String>

    fun send(deposit: Deposit) {
        LOG.info("Sending deposit $deposit to topic $topic")
        kafkaTemplate
            .send(topic, MESSAGE_KEY, deposit.objectToJson())
            .addCallback(callback)
    }
}