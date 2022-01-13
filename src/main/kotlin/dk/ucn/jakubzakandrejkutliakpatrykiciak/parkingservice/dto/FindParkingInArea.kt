package dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.dto

import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.model.ParkingProvider

data class FindParkingInArea(
    val longitude: Double,
    val latitude: Double
)

data class ParkingLotDto(
    var parkingProvider: ParkingProvider,
    var name: String,
    var longitude: Double,
    var latitude: Double
)
