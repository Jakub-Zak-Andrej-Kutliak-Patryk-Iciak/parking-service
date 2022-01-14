package dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.controller

import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.dto.RefreshDataRequest
import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.messaging.MessageProcessor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class Controller(
    private val messageProcessor: MessageProcessor
) {

    @GetMapping("/refresh")
    fun refreshDataRequest() {
        messageProcessor.process(RefreshDataRequest())
    }
}
