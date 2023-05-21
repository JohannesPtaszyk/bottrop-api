package dev.pott.bottropapi.servicepoint.parkandride.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PriceDtoV1(
    @SerialName("value")
    val value: Double,

    @SerialName("formatted")
    val formatted: String,
)