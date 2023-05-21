package dev.pott.bottropapi.servicepoint.parkandride.entity

import jakarta.persistence.*

@Entity
@Table(name = "park_and_ride_service_point_price")
data class ParkAndRideServicePointPriceEntity(
    /**
     * Price ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    /**
     * Price
     */
    @Column(name = "price_value")
    val value: Double,

    /**
     * ISO 4217 currency representation
     */
    @Column(name = "currency")
    val currency: String,
)