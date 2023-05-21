package dev.pott.bottropapi.servicepoint.parkandride.importer

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ParkAndRideOpenDataResponseDto(
    @SerialName("type")
    val type: String,
    @SerialName("crs")
    val crs: CrsDto,
    @SerialName("features")
    val features: List<FeatureDto>,
)

@Serializable
data class CrsDto(
    @SerialName("type")
    val type: String,
    @SerialName("properties")
    val crsProperties: CrsPropertiesDto,
)

@Serializable
data class CrsPropertiesDto(
    @SerialName("name")
    val name: String,
)

@Serializable
data class FeatureDto(
    @SerialName("type")
    val type: String,
    @SerialName("id")
    val id: Long,
    @SerialName("geometry")
    val geometry: GeometryDto,
    @SerialName("properties")
    val properties: FeaturePropertiesDto,
)
@Serializable
data class GeometryDto(
    @SerialName("type")
    val type: String,
    @SerialName("coordinates")
    val coordinates: List<Double>,
)
@Serializable
data class FeaturePropertiesDto(
    @SerialName("FID")
    val fid: Long,
    @SerialName("NUMMER")
    val number: Long,
    @SerialName("ANLAGE")
    val name: String,
    @SerialName("NAME_HALTE")
    val stationName: String,
    @SerialName("X_KOORDINA")
    val longitude: Double,
    @SerialName("Y_KOORDINA")
    val latitude: Double,
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
