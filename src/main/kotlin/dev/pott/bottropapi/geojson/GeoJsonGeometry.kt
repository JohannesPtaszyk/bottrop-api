package dev.pott.bottropapi.geojson

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface GeoJsonGeometry {

    @Serializable
    @SerialName("Point")
    data class Point(val coordinates: GeoJsonCoordinates): GeoJsonGeometry

    //TODO Add additional types from spec
}