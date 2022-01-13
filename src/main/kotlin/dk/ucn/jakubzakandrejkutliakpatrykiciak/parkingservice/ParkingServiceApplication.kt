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
		parkingLot.name = ""
		parkingLot.longitude = 30.0
		parkingLot.latitude = 39.0

		parkingLotRepository.saveAll(listOf(
			parkingLot
		))

		parkingLotRepository
			.findByCoordinates(30.0, 30.0, 39.0, 39.0)
			.map { lot: ParkingLot -> "" + lot.latitude + " " + lot.longitude }
			.forEach(System.out::println)
	}
}

fun main(args: Array<String>) {
	runApplication<ParkingServiceApplication>(*args)
}
