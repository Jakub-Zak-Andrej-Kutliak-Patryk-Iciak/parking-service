package dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.messaging

import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.dto.FindParkingRequest
import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.dto.RefreshDataRequest
import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.dto.RefreshDataResponse
import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.service.ParkingService
import org.springframework.stereotype.Component

@Component
class MessageProcessor(
    val parkingService: ParkingService,
    val messagePublisher: MessagePublisher
) {

    fun process(findParkingRequest: FindParkingRequest) {
        val parkingLots = parkingService.findParkingLotsInArea(findParkingRequest)
        messagePublisher.publish(parkingLots)
    }

    fun process(refreshDataRequest: RefreshDataRequest) {
        messagePublisher.publish(refreshDataRequest)
    }

    fun process(refreshDataResponse: RefreshDataResponse) {
        parkingService.updateParkingData(refreshDataResponse)
    }
}
