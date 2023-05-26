package dev.pott.bottropapi.servicepoint.parkandride.importer

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class ParkAndRideGeoJsonProperties(
    @SerialName("FID")
    val fid: Long,
    @SerialName("NUMMER")
    val number: Long,
    @SerialName("ANLAGE")
    val name: String,
    @SerialName("NAME_HALTE")
    val stationName: String,
    @SerialName("STRAßE")
    val streetName: String,
    @SerialName("POSTLEITZA")
    val zipCode: String,
    @SerialName("STADT")
    val city: String,
    @SerialName("ART")
    val type: String,
    @SerialName("EINFAHRTSH")
    val entrance: Int,
    @SerialName("STELLPLAET")
    val capacity: Int,
    @SerialName("BARRIEREFR")
    val barrierFree: Int,
    @SerialName("ANBINDUNG_")
    val publicTransportLines: String,
    @SerialName("OEFFNUNGSZ")
    val openingHours: String,
    @SerialName("GEBUEHREN_")
    val price: String,
)

const val TYPE_MARKED_SPOTS = "markierte Stellplaetze"
const val TYPE_CAR_PARK = "Parkplatz"
const val TYPE_PARKING_GARAGE = "Parkhaus"
const val TYPE_GRAVEL_PLACE = "Schotterflaeche"
