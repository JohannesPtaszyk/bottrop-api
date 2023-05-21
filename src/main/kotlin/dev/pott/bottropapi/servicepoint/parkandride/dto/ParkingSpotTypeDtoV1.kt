package dev.pott.bottropapi.servicepoint.parkandride.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ParkingSpotTypeDtoV1 {
    @SerialName("MARKED_SPOTS")
    MARKED_SPOTS,

    @SerialName("CAR_PARK")
    CAR_PARK,

    @SerialName("PARKING_GARAGE")
    PARKING_GARAGE,

    @SerialName("GRAVEL_PLACE")
    GRAVEL_PLACE,

    @SerialName("UNKNOWN")
    UNKNOWN,
}
