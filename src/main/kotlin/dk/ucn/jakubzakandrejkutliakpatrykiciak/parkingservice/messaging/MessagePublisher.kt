package dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.messaging

import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.dto.FindParkingResponse
import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.dto.RefreshDataRequest
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class MessagePublisher(
    private val template: RabbitTemplate,
    @Value("\${broker.findParkingResponse}") private val findParkingResponseQueue: String,
    @Value("\${broker.refreshDataRequest}") private val refreshDataRequestQueue: String
) {
    fun publish(message: FindParkingResponse) {
        template.convertAndSend(findParkingResponseQueue, message)
    }

    fun publish(message: RefreshDataRequest) {
        template.convertAndSend(refreshDataRequestQueue, message)
    }
}
