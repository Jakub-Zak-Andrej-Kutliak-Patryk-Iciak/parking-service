package dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.controller

import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.dto.FindParkingResponse
import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.dto.RefreshDataRequest
import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.messaging.MessageProducer
import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.service.ParkingService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class Controller(
    private val parkingService: ParkingService,
    private val messageProducer: MessageProducer
) {

    @GetMapping("/refresh")
    fun refreshDataRequest() {
        messageProducer.publish(RefreshDataRequest())
    }

    @GetMapping("/parking/{longitude}/{latitude}")
    fun findParking(@PathVariable longitude: Double, @PathVariable latitude: Double): FindParkingResponse {
        return parkingService.findParkingLotsInArea(longitude, latitude)
    }
}
