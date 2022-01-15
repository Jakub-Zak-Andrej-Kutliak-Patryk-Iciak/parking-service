package dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.dto

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable

@Serializable
data class RefreshDataResponse (
    @JsonProperty("parkingProvider") val parkingProvider: String,
    @JsonProperty("parkingData") val parkingData: List<ParkingLotDto>
)
