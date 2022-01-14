package dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.dto

import java.util.*

data class FindParkingResponse(
    val correlationId: UUID,
    val parkingData: List<ParkingLotDto>
)
