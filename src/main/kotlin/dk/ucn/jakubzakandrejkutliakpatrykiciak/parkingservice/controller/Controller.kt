package dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.controller

import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.dto.FindParkingResponse
import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.dto.RefreshDataRequest
import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.messaging.MessageProcessor
import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.service.ParkingService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class Controller(
    private val messageProcessor: MessageProcessor,
    private val parkingService: ParkingService
) {

    @GetMapping("/refresh")
    fun refreshDataRequest() {
        messageProcessor.process(RefreshDataRequest())
    }

    @GetMapping("/parking/{longitude}/{latitude}")
    fun findParking(@PathVariable longitude: Double, @PathVariable latitude: Double): FindParkingResponse {
        return parkingService.findParkingLotsInArea(longitude, latitude)
    }
}
