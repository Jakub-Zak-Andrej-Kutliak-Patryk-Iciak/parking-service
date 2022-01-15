package dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.repository

import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.model.ParkingLot
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ParkingLotRepository : JpaRepository<ParkingLot, Long> {
    @Query(value = """ select p from ParkingLot p
        where p.latitude <= :maxLat and p.latitude >= :minLat
        and p.longitude <= :maxLon and p.longitude >= :minLon """)
    fun findByCoordinates(
        @Param("minLon") minLon: Double,
        @Param("maxLon") maxLon: Double,
        @Param("minLat") minLat: Double,
        @Param("maxLat") maxLat: Double,
    ): List<ParkingLot>

    fun findByParkingProvider(parkingProvider: String): List<ParkingLot>
    fun deleteAllByParkingProvider(parkingProvider: String)
}
