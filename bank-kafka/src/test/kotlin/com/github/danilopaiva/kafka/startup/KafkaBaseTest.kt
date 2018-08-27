package com.github.danilopaiva.kafka.startup

import org.junit.After
import org.junit.Before
import org.junit.ClassRule
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.config.KafkaListenerEndpointRegistry
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.listener.MessageListenerContainer
import org.springframework.kafka.test.rule.KafkaEmbedded
import org.springframework.kafka.test.utils.ContainerTestUtils
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
@DirtiesContext
@ContextConfiguration(classes = [(ApplicationTestConfig::class)])
abstract class KafkaBaseTest {

    companion object {
        private const val BROKERS = 1
        private const val CONTROLLED_SHUTDOWN = true
        private const val PARTITIONS = 1

        @JvmField
        @ClassRule
        val embeddedKafka = KafkaEmbedded(
            BROKERS,
            CONTROLLED_SHUTDOWN,
            PARTITIONS
        )
    }

    /*protected val accountRepository: AccountRepository = mock()

    @Mock
    private val accountCommandHandler = AccountCommandHandler(accountRepository)

    @Bean
    private fun consumer(): DepositConsumer {
        return DepositConsumer(accountCommandHandler)
    }*/

    @Autowired
    lateinit var kafkaTemplate: KafkaTemplate<String, String>

    @Value("\${kafka.bank.deposits.topic}")
    protected lateinit var bankDepositsTopic: String

    @Autowired
    lateinit var kafkaListenerEndpointRegistry: KafkaListenerEndpointRegistry


    @Before
    @Throws(Exception::class)
    fun setUp() {
        startKafka()
    }

    @After
    fun tearDown() {
        stopKafka()
    }

    private fun startKafka() {
        kafkaListenerEndpointRegistry.listenerContainers?.forEach { this.startContainer(it) }
    }

    private fun stopKafka() {
        kafkaListenerEndpointRegistry.listenerContainers.forEach { it.stop() }
    }

    private fun startContainer(container: MessageListenerContainer) {
        try {
            ContainerTestUtils.waitForAssignment(container, embeddedKafka.partitionsPerTopic)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}