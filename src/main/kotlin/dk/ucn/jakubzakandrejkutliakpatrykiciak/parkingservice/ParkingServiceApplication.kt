package dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice

import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.model.ParkingLot
import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.repository.ParkingLotRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.runApplication
import org.springframework.context.event.EventListener


@SpringBootApplication
class ParkingServiceApplication(
	val parkingLotRepository: ParkingLotRepository
) {

	@EventListener(ApplicationReadyEvent::class)
	fun doSomethingAfterStartup() {
		val parkingLot = ParkingLot()
		parkingLot.id = 0
		parkingLot.name = "Hardcoded Parking"
		parkingLot.capacity = 22
		parkingLot.busy = 19
		parkingLot.parkingProvider = "Hardcoded from Parking Service"
		parkingLot.longitude = 9.935932
		parkingLot.latitude = 57.046707

		parkingLotRepository.saveAll(listOf(
			parkingLot
		))
	}
}

fun main(args: Array<String>) {
	runApplication<ParkingServiceApplication>(*args)
}
