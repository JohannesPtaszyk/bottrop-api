package dev.pott.bottropapi.geojson

import kotlinx.serialization.*
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonPrimitive

@Serializable(with = GeoJsonPropertiesSerializer::class)
class GeoJsonProperties: LinkedHashMap<String, JsonPrimitive>() {

    inline fun <reified T> convertTo(): T {
        val json = Json {
            coerceInputValues = true
            ignoreUnknownKeys = true
        }
        val jsonString = json.encodeToString(this as LinkedHashMap<String, JsonPrimitive>)
        return json.decodeFromString<T>(jsonString)
    }

    inline fun <reified T> convertFrom(from: T): Map<String, JsonPrimitive> {
        val json = Json {
            coerceInputValues = true
            ignoreUnknownKeys = true
        }
        val jsonString = json.encodeToString(from)
        return GeoJsonProperties().also {
            it.putAll(json.decodeFromString<Map<String, JsonPrimitive>>(jsonString))
        }
    }
}

@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = GeoJsonProperties::class)
class GeoJsonPropertiesSerializer: KSerializer<GeoJsonProperties> {

    private val serializer = MapSerializer(String.serializer(), JsonPrimitive.serializer())
    override val descriptor: SerialDescriptor = serializer.descriptor

    override fun serialize(encoder: Encoder, value: GeoJsonProperties) {
        serializer.serialize(encoder, value as Map<String, JsonPrimitive>)
    }

    override fun deserialize(decoder: Decoder): GeoJsonProperties {
        return GeoJsonProperties().also {
            it.putAll(serializer.deserialize(decoder))
        }
    }
}