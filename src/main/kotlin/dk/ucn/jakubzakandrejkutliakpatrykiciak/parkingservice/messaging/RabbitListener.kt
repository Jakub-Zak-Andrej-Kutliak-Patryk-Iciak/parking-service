package dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.messaging

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class RabbitListener(
    val parkingDataProcessor: ParkingDataProcessor
) {

    @RabbitListener(queues = [])
    fun receiveNewParkingData() {
        parkingDataProcessor.process()
    }
}
