package com.github.danilopaiva.kafka

import com.github.danilopaiva.kafka.consumer.Consumer
import com.github.danilopaiva.kafka.producer.DepositProducer
import org.junit.Before
import org.junit.ClassRule
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Bean
import org.springframework.kafka.config.KafkaListenerEndpointRegistry
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.test.rule.KafkaEmbedded
import org.springframework.kafka.test.utils.ContainerTestUtils
import org.springframework.kafka.test.utils.KafkaTestUtils
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
@DirtiesContext
abstract class KafkaBaseTest {

    companion object {
        private const val BROKERS = 1
        private const val CONTROLLED_SHUTDOWN = true
        private const val PARTITIONS = 1

        @JvmField
        @ClassRule
        val embeddedKafka = KafkaEmbedded(BROKERS, CONTROLLED_SHUTDOWN, PARTITIONS)
    }

    @Autowired
    protected lateinit var depositProducer: DepositProducer

    @Autowired
    protected lateinit var consumer: Consumer

    @Autowired
    lateinit var kafkaListenerEndpointRegistry: KafkaListenerEndpointRegistry

    @Before
    @Throws(Exception::class)
    fun setUp() {
        for (messageListenerContainer in kafkaListenerEndpointRegistry.listenerContainers) {
            ContainerTestUtils.waitForAssignment(
                messageListenerContainer,
                embeddedKafka.partitionsPerTopic
            )
        }
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, String> {
        val senderProperties = KafkaTestUtils.senderProps(embeddedKafka.brokersAsString)
        val producerFactory = DefaultKafkaProducerFactory<String, String>(senderProperties)
        return KafkaTemplate(producerFactory)
    }
}