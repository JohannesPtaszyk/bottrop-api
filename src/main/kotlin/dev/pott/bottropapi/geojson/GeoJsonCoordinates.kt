package dev.pott.bottropapi.geojson

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = GeoJsonCoordinatesSerializer::class)
data class GeoJsonCoordinates(val longitude: Double, val latitude: Double)

@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = GeoJsonCoordinates::class)
class GeoJsonCoordinatesSerializer: KSerializer<GeoJsonCoordinates> {

    private val serializer = ListSerializer(Double.serializer())
    override val descriptor: SerialDescriptor = serializer.descriptor

    override fun serialize(encoder: Encoder, value: GeoJsonCoordinates) {
        serializer.serialize(encoder, listOf(value.longitude, value.latitude))
    }

    override fun deserialize(decoder: Decoder): GeoJsonCoordinates {
        val geoJsonCoordinatesList = decoder.decodeSerializableValue(serializer)
        return GeoJsonCoordinates(geoJsonCoordinatesList[0], geoJsonCoordinatesList[1])
    }
}