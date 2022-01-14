package dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.service

import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.dto.FindParkingRequest
import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.dto.FindParkingResponse
import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.dto.ParkingLotDto
import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.dto.RefreshDataResponse
import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.model.ParkingLot
import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.repository.ParkingLotRepository
import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.repository.ParkingProviderRepository
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class ParkingService(
    private val parkingLotRepository: ParkingLotRepository,
    private val parkingProviderRepository: ParkingProviderRepository
) {
    fun findParkingLotsInArea(findParkingRequest: FindParkingRequest): FindParkingResponse {
        val parkingLots = parkingLotRepository.findByCoordinates(
            findParkingRequest.longitude - 0.1,
            findParkingRequest.longitude + 0.1,
            findParkingRequest.latitude - 0.1,
            findParkingRequest.latitude + 0.1
        ).stream().map { parking -> ParkingLotDto(
            parking.parkingProvider?.name,
            parking.name!!,
            parking.longitude!!,
            parking.latitude!!
        ) }.collect(Collectors.toList())
        return FindParkingResponse(findParkingRequest.correlationId, parkingLots)
    }

    fun updateParkingData(refreshDataResponse: RefreshDataResponse) {
        val parkingProvider = parkingProviderRepository.getById(refreshDataResponse.parkingProviderId)

        val list = mutableListOf<ParkingLot>()
        for (parkingDto in refreshDataResponse.parkingData) {
            val parking = ParkingLot()
            parking.name = parkingDto.name
            parking.parkingProvider = parkingProvider
            parking.longitude = parkingDto.longitude
            parking.latitude = parkingDto.latitude
            list.add(parking)
        }

        parkingProvider.parkingLots.clear()
        parkingProvider.parkingLots.addAll(list)
    }
}
