package dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.messaging

import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.dto.FindParkingResponse
import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.dto.RefreshDataRequest
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class MessagePublisher(
    private val template: RabbitTemplate,
    @Value("\${broker.findParkingResponse}") private val findParkingResponseQueue: String,
    @Value("\${broker.refreshDataRequest}") private val refreshDataRequestQueue: String
) {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    fun publish(message: FindParkingResponse) {
        template.convertAndSend(findParkingResponseQueue, message)
        logger.info("Published FindParkingResponse, ${message.parkingData.size} element(s)")
    }

    fun publish(message: RefreshDataRequest) {
        template.convertAndSend(refreshDataRequestQueue, message)
        logger.info("Published RefreshDataRequest")
    }
}
