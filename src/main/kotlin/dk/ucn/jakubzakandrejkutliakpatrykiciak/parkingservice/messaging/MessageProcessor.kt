package dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.messaging

import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.dto.RefreshDataRequest
import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.dto.RefreshDataResponse
import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.service.ParkingService
import org.springframework.stereotype.Component

@Component
class MessageProcessor(
    val parkingService: ParkingService,
    val messageProducer: MessageProducer
) {

    fun process(refreshDataRequest: RefreshDataRequest) {
        messageProducer.publish(refreshDataRequest)
    }

    fun process(refreshDataResponse: RefreshDataResponse) {
        parkingService.updateParkingData(refreshDataResponse)
    }
}
