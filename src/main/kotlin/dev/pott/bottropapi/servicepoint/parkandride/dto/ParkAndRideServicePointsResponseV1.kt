package dev.pott.bottropapi.servicepoint.parkandride.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ParkAndRideServicePointsResponseV1(
    @SerialName("servicePoints")
    val servicePoints: List<ParkAndRideServicePointDtoV1>
)