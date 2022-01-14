package dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.dto

data class RefreshDataResponse (
    val parkingProviderId: Long,
    val parkingData: List<ParkingLotDto>
)
