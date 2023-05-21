package dev.pott.bottropapi.servicepoint.parkandride.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddressDtoV1(
    @SerialName("streetName")
    val streetName: String,

    @SerialName("zipCode")
    val zipCode: String,

    @SerialName("city")
    val city: String,
)