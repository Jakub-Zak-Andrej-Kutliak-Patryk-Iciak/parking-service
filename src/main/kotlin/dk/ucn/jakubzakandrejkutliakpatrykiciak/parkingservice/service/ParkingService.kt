package dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.service

import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.dto.FindParkingResponse
import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.dto.ParkingLotDto
import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.dto.RefreshDataResponse
import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.model.ParkingLot
import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.repository.ParkingLotRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class ParkingService(
    private val parkingLotRepository: ParkingLotRepository
) {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    fun findParkingLotsInArea(longitude: Double, latitude: Double): FindParkingResponse {
        val parkingLots = parkingLotRepository.findByCoordinates(
            longitude - 0.1,
            longitude + 0.1,
            latitude - 0.1,
            latitude + 0.1
        ).stream().map { parking -> ParkingLotDto(
            parking.parkingProvider,
            parking.name!!,
            parking.longitude!!,
            parking.latitude!!,
            parking.capacity!!,
            parking.busy!!
        ) }.collect(Collectors.toList())
        return FindParkingResponse(parkingLots)
    }

    fun updateParkingData(refreshDataResponse: RefreshDataResponse) {
        val list = mutableListOf<ParkingLot>()
        for (parkingDto in refreshDataResponse.parkingData) {
            val parking = ParkingLot()
            parking.name = parkingDto.name
            parking.parkingProvider = parkingDto.parkingProvider
            parking.longitude = parkingDto.longitude
            parking.latitude = parkingDto.latitude
            parking.capacity = parkingDto.capacity
            parking.busy = parkingDto.busy
            list.add(parking)
        }

        parkingLotRepository.deleteAllByParkingProvider(refreshDataResponse.parkingProvider)
        parkingLotRepository.saveAll(list)
        logger.info("Data updated for ${refreshDataResponse.parkingProvider}")
    }
}
