package dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.messaging

import com.fasterxml.jackson.databind.ObjectMapper
import com.rabbitmq.client.Connection
import com.rabbitmq.client.DeliverCallback
import com.rabbitmq.client.Delivery
import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.dto.FindParkingRequest
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
    @Value("\${broker.findParkingRequest}") private val findParkingRequestQueue: String,
    private val messageProcessor: MessageProcessor,
    private val connection: Connection
) {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @Async
    @EventListener(ApplicationStartedEvent::class)
    fun consumeFindParkingRequest() {
        val deliverCallback = DeliverCallback { consumerTag: String?, delivery: Delivery ->
            val deserialized = ObjectMapper().readValue(delivery.body, FindParkingRequest::class.java)
            logger.info("Received request for a parking spot at ${deserialized.longitude}, ${deserialized.latitude}")
            messageProcessor.process(deserialized)
        }
        val channel = connection.createChannel()
        channel.queueDeclare(findParkingRequestQueue, false, false, false, null)
        logger.info("Starting consuming from $findParkingRequestQueue")
        channel.basicConsume(findParkingRequestQueue, true, deliverCallback) { consumerTag: String -> }
    }

    @Async
    @EventListener(ApplicationStartedEvent::class)
    fun consumeRefreshDataResponseQueue() {
        val deliverCallback = DeliverCallback { consumerTag: String?, delivery: Delivery ->
            val deserialized = ObjectMapper().readValue(delivery.body, RefreshDataResponse::class.java)
            logger.info("Received response from ${refreshDataResponseQueue}. Elements: ${deserialized.parkingData.size}")
            messageProcessor.process(deserialized)
        }
        val channel = connection.createChannel()
        channel.queueDeclare(refreshDataResponseQueue, true, false, false, null)
        logger.info("Starting consuming from $refreshDataResponseQueue")
        channel.basicConsume(refreshDataResponseQueue, true, deliverCallback) { consumerTag: String -> }
    }
}
