package dev.pott.bottropapi.servicepoint.parkandride.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ParkAndRideServicePointDtoV1(
    @SerialName("id")
    val id: String,

    @SerialName("name")
    val name: String,

    @SerialName("capacity")
    val capacity: Int,

    @SerialName("type")
    val type: ParkingSpotTypeDtoV1,

    @SerialName("stationName")
    val stationName: String,

    @SerialName("price")
    val price: PriceDtoV1,

    @SerialName("publicTransportLines")
    val publicTransportLines: List<String>,

    @SerialName("coordinates")
    val coordinates: CoordinatesDtoV1,

    @SerialName("address")
    val address: AddressDtoV1,
)