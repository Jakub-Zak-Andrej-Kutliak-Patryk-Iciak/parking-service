package dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.model

import javax.persistence.*
import javax.persistence.GenerationType.IDENTITY

@Entity
open class ParkingProvider(

    @Id
    @GeneratedValue(strategy = IDENTITY)
    open var id: Long? = null,

    @Column(nullable = false)
    open var name: String? = null,

    @Column(nullable = false)
    @OneToMany(mappedBy = "parkingProvider")
    open var parkingLots: MutableSet<ParkingLot> = mutableSetOf()
)
