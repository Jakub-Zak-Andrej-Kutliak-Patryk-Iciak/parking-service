package dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
open class ParkingLot {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    open var id: Long? = null

    @ManyToOne
    @JoinColumn
    open var parkingProvider: ParkingProvider? = null

    @Column(nullable = false)
    open var name: String? = null

    @Column(nullable = false)
    open var longitude: Double? = null

    @Column(nullable = false)
    open var latitude: Double? = null
}
