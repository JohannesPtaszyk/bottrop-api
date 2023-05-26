package dev.pott.bottropapi.geojson

import dev.pott.bottropapi.servicepoint.parkandride.importer.ParkAndRideGeoJsonProperties
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonObject

@Serializable
sealed interface GeoJsonObject {

    @Serializable
    @SerialName("FeatureCollection")
    data class FeatureCollection(
        @SerialName("features")
        val features: List<Feature>
    ): GeoJsonObject

    @Serializable
    @SerialName("Feature")
    data class Feature(
        @SerialName("id")
        val id: Long,
        @SerialName("geometry")
        val geometry: GeoJsonGeometry,
        @SerialName("properties")
        val properties: GeoJsonProperties,
    ): GeoJsonObject
}