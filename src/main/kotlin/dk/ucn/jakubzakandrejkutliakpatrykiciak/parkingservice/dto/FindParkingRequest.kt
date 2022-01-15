package dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.dto

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable

@Serializable
data class FindParkingRequest(
    @JsonProperty("longitude") val longitude: Double,
    @JsonProperty("latitude") val latitude: Double,
    @JsonProperty("correlationId") val correlationId: String
)
