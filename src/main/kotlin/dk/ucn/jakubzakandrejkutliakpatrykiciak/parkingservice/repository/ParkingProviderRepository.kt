package dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.repository

import dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.model.ParkingProvider
import org.springframework.data.jpa.repository.JpaRepository

interface ParkingProviderRepository : JpaRepository<ParkingProvider, Long>
