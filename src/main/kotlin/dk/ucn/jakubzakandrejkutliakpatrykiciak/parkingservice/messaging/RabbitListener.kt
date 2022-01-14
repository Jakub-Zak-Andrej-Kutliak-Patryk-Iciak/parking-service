package dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.messaging

import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.dto.FindParkingRequest
import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.dto.RefreshDataRequest
import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.dto.RefreshDataResponse
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class RabbitListener(
    val messageProcessor: MessageProcessor
) {

    @RabbitListener(queues = ["\${broker.findParkingRequest}"])
    fun findParkingRequest(findParkingRequest: FindParkingRequest) {
        messageProcessor.process(findParkingRequest)
    }

    @RabbitListener(queues = ["\${broker.refreshDataRequest}"])
    fun refreshDataRequest(refreshDataRequest: RefreshDataRequest) {
        messageProcessor.process(refreshDataRequest)
    }

    @RabbitListener(queues = ["\${broker.refreshDataResponse}"])
    fun refreshDataResponse(refreshDataResponse: RefreshDataResponse) {
        messageProcessor.process(refreshDataResponse)
    }
}
