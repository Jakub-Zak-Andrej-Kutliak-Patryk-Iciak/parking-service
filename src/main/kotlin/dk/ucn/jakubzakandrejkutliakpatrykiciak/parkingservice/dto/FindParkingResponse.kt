package dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.dto

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable

@Serializable
data class FindParkingResponse(
    @JsonProperty("parkingData") val parkingData: List<ParkingLotDto>
)
