package dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.messaging

import com.fasterxml.jackson.databind.ObjectMapper
import com.rabbitmq.client.Connection
import com.rabbitmq.client.DeliverCallback
import com.rabbitmq.client.Delivery
import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.dto.RefreshDataResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component


@Component
class MessageConsumer(
    @Value("\${broker.refreshDataResponse}") private val refreshDataResponseQueue: String,
    private val messageProcessor: MessageProcessor,
    private val connection: Connection
) {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @Async
    @EventListener(ApplicationStartedEvent::class)
    fun consumeRefreshDataResponseQueue() {
        val deliverCallback = DeliverCallback { consumerTag: String?, delivery: Delivery ->
            val deserialized = ObjectMapper().readValue(delivery.body, RefreshDataResponse::class.java)
            logger.info("Received response from ${refreshDataResponseQueue}. Elements: ${deserialized.parkingData.size}")
            try {
                messageProcessor.process(deserialized)
            } catch (e: RuntimeException) {
                logger.error(e.message)
            }
        }
        val channel = connection.createChannel()
        channel.queueDeclare(refreshDataResponseQueue, true, false, false, null)
        logger.info("Starting consuming from $refreshDataResponseQueue")
        channel.basicConsume(refreshDataResponseQueue, true, deliverCallback) { consumerTag: String -> }
    }
}
