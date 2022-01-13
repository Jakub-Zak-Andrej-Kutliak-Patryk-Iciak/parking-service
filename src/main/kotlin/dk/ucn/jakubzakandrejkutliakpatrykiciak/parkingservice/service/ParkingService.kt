package dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.service

import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.model.ParkingLot
import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.repository.ParkingLotRepository
import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.repository.ParkingProviderRepository
import org.springframework.stereotype.Service

@Service
class ParkingService(
    val parkingLotRepository: ParkingLotRepository,
    val parkingProviderRepository: ParkingProviderRepository
) {
    fun findParkingLotsInArea(minLon: Double, maxLon: Double, minLat: Double, maxLat: Double): List<ParkingLot> {
        return parkingLotRepository.findByCoordinates(minLon, maxLon, minLat, maxLat)
    }
}
