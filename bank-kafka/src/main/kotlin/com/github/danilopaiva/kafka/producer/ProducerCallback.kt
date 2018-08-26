package com.github.danilopaiva.kafka.producer

import org.apache.logging.log4j.LogManager
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Component
import org.springframework.util.concurrent.ListenableFutureCallback

@Component
class ProducerCallback : ListenableFutureCallback<SendResult<String, String>> {

    companion object {
        private val LOG = LogManager.getLogger(ProducerCallback::class)
    }

    override fun onSuccess(result: SendResult<String, String>?) {
        LOG.info(
            """
                Success to publish:
                Topic: ${result?.recordMetadata?.topic()}
                Message: ${result?.producerRecord?.value()}
                Key: ${result?.producerRecord?.key()}
            """
        )
    }

    override fun onFailure(throwable: Throwable) {
        LOG.error("Houston we have a problem.", throwable)
    }

}