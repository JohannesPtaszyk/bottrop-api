package dev.pott.bottropapi.servicepoint.parkandride.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Currency

data class Price(
    val value: Double,
    val formatted: String,
    val currency: Currency
)