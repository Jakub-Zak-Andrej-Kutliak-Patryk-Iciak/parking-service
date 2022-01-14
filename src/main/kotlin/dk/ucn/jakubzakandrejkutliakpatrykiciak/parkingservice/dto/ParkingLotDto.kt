package dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.dto

data class ParkingLotDto(
    val parkingProvider: String?,
    val name: String,
    val longitude: Double,
    val latitude: Double
)
