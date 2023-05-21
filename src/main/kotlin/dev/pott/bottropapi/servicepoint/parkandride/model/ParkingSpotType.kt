package dev.pott.bottropapi.servicepoint.parkandride.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

enum class ParkingSpotType {
    MARKED_SPOTS,
    CAR_PARK,
    PARKING_GARAGE,
    GRAVEL_PLACE,
    UNKNOWN,
}
