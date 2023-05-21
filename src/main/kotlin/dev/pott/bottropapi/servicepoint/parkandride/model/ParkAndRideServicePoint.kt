package dev.pott.bottropapi.servicepoint.parkandride.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class ParkAndRideServicePoint (
    @SerialName("id")
    val id: String,

    @SerialName("name")
    val name: String,

    @SerialName("capacity")
    val capacity: Int,

    @SerialName("type")
    val type: ParkingSpotType,

    @SerialName("stationName")
    val stationName: String,

    @SerialName("price")
    val price: Price,

    @SerialName("publicTransportLines")
    val publicTransportLines: List<String>,

    @SerialName("coordinates")
    val coordinates: Coordinates,

    @SerialName("address")
    val address: Address,
)