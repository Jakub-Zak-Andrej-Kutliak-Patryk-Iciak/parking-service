package dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.dto

import java.util.*

data class FindParkingRequest(
    val longitude: Double,
    val latitude: Double,
    val correlationId: UUID
)
