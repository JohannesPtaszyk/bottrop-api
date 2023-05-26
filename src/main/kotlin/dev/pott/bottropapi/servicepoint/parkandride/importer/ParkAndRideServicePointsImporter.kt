package dev.pott.bottropapi.servicepoint.parkandride.importer

import dev.pott.bottropapi.geojson.*
import dev.pott.bottropapi.servicepoint.parkandride.ParkAndRideRepository
import dev.pott.bottropapi.servicepoint.parkandride.entity.ParkAndRideServicePointEntity
import dev.pott.bottropapi.servicepoint.parkandride.entity.ParkAndRideServicePointPriceEntity
import dev.pott.bottropapi.servicepoint.parkandride.entity.ParkingSpotTypeEntity
import kotlinx.serialization.json.Json
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.KotlinSerializationJsonHttpMessageConverter
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

import java.util.concurrent.TimeUnit

private const val jsonUrl = "https://www.bottrop.de/downloads/daten-karten/opendata/park-and-ride/Park_Ride_Bot.json"

private const val FREE_PRICE_ID = "frei"

@Component
class ParkAndRideServicePointsImporter(private val repository: ParkAndRideRepository) {

    private val log: Logger = LoggerFactory.getLogger(ParkAndRideServicePointsImporter::class.java)

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.DAYS, initialDelay = 0)
    fun importData() {
        val apiResponse = RestTemplate().apply {
            messageConverters = listOf(
                KotlinSerializationJsonHttpMessageConverter(
                    Json {
                        coerceInputValues = true
                        ignoreUnknownKeys = true
                    }
                ) as HttpMessageConverter<*>
            )
        }.getForObject(jsonUrl, GeoJsonObject::class.java) ?: return
        val apiCurrency = Currency.getInstance(Locale.GERMANY)
        val format = DecimalFormat.getCurrencyInstance().apply {
            currency = apiCurrency
        }
        val entities = if (apiResponse is GeoJsonObject.FeatureCollection) {
            apiResponse.features.mapNotNull { feature ->
                feature.getParkAndRideServicePointEntity(format, apiCurrency)
            }
        } else {
            log.error("Could not import data")
            return
        }
        repository.saveAll(entities)
    }

    private fun GeoJsonObject.Feature.getParkAndRideServicePointEntity(
        format: NumberFormat,
        apiCurrency: Currency
    ): ParkAndRideServicePointEntity? {
        val point = geometry as? GeoJsonGeometry.Point ?: return null
        val properties = properties.convertTo<ParkAndRideGeoJsonProperties>()
        return ParkAndRideServicePointEntity(
            id = properties.fid.toString(),
            name = properties.name,
            capacity = properties.capacity,
            type = when (properties.type) {
                TYPE_PARKING_GARAGE -> ParkingSpotTypeEntity.PARKING_GARAGE
                TYPE_CAR_PARK -> ParkingSpotTypeEntity.CAR_PARK
                TYPE_GRAVEL_PLACE -> ParkingSpotTypeEntity.GRAVEL_PLACE
                TYPE_MARKED_SPOTS -> ParkingSpotTypeEntity.MARKED_SPOTS
                else -> ParkingSpotTypeEntity.UNKNOWN
            },
            stationName = properties.stationName,
            price = ParkAndRideServicePointPriceEntity(
                value = when (properties.price) {
                    FREE_PRICE_ID -> 0.0
                    else -> format.parse(properties.price).toDouble()
                },
                currency = apiCurrency.currencyCode
            ),
            latitude = point.coordinates.latitude,
            longitude = point.coordinates.longitude,
            streetName = properties.streetName,
            zipCode = properties.zipCode,
            city = properties.city,
        )
    }
}
