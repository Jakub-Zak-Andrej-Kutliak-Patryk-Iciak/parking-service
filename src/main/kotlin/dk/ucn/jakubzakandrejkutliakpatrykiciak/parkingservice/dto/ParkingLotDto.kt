package dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.dto

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable

@Serializable
data class ParkingLotDto(
    @JsonProperty("parkingProvider") val parkingProvider: String?,
    @JsonProperty("name") val name: String,
    @JsonProperty("longitude") val longitude: Double,
    @JsonProperty("latitude") val latitude: Double
)
