package dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.messaging

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectWriter
import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.dto.FindParkingResponse
import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.dto.RefreshDataRequest
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class MessageProducer(
    @Value("\${broker.refreshDataRequest}") private val refreshDataRequestQueue: String,
    connection: Connection
) {
    @Scheduled(fixedRate = 30000)
    fun sendRefresh() {
        publish(RefreshDataRequest())
    }

    private val channel: Channel
    private val logger = LoggerFactory.getLogger(this.javaClass)

    fun publish(message: RefreshDataRequest) {
        val ow: ObjectWriter = ObjectMapper().writer().withDefaultPrettyPrinter()
        val json: String = ow.writeValueAsString(message)
        channel.basicPublish("", refreshDataRequestQueue, null, json.toByteArray())
        logger.info("Published RefreshDataRequest")
    }

    init {
        channel = connection.createChannel()
    }
}
