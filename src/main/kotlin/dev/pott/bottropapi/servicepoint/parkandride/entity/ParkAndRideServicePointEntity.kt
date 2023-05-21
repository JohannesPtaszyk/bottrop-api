package dev.pott.bottropapi.servicepoint.parkandride.entity

import dev.pott.bottropapi.servicepoint.parkandride.model.ParkingSpotType
import jakarta.persistence.*

@Entity
@Table(name = "park_and_ride_service_point")
data class ParkAndRideServicePointEntity(
    @Id
    @Column(name = "id")
    val id: String,

    @Column(name = "name")
    val name: String,

    @Column(name = "capacity")
    val capacity: Int,

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    val type: ParkingSpotTypeEntity,

    @Column(name = "station_name")
    val stationName: String,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "park_and_ride_service_point_price")
    val price: ParkAndRideServicePointPriceEntity,

    @Column(name = "latitude")
    val latitude: Double,

    @Column(name = "longitude")
    val longitude: Double,

    @Column(name = "street_name")
    val streetName: String,

    @Column(name = "zip_code")
    val zipCode: String,

    @Column(name = "city")
    val city: String,
)