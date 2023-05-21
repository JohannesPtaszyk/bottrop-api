package dev.pott.bottropapi.servicepoint.parkandride.importer

import dev.pott.bottropapi.servicepoint.parkandride.ParkAndRideRepository
import dev.pott.bottropapi.servicepoint.parkandride.entity.ParkAndRideServicePointEntity
import dev.pott.bottropapi.servicepoint.parkandride.entity.ParkAndRideServicePointPriceEntity
import dev.pott.bottropapi.servicepoint.parkandride.entity.ParkingSpotTypeEntity
import kotlinx.serialization.json.Json
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.KotlinSerializationJsonHttpMessageConverter
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.text.DecimalFormat
import java.util.*

import java.util.concurrent.TimeUnit

private const val jsonUrl = "https://www.bottrop.de/downloads/daten-karten/opendata/park-and-ride/Park_Ride_Bot.json"

@Component
class ParkAndRideServicePointsImporter(@Autowired private val repository: ParkAndRideRepository) {

    private val log: Logger = LoggerFactory.getLogger(ParkAndRideServicePointsImporter::class.java)

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.DAYS, initialDelay = 0)
    fun importData() {
        val api = RestTemplate().apply {
            messageConverters = listOf(
                KotlinSerializationJsonHttpMessageConverter(
                    Json {
                        coerceInputValues = true
                        ignoreUnknownKeys = true
                    }
                ) as HttpMessageConverter<*>
            )
        }.getForObject(jsonUrl, ParkAndRideOpenDataResponseDto::class.java) ?: return
        val apiCurrency = Currency.getInstance(Locale.GERMANY)
        val format = DecimalFormat.getCurrencyInstance().apply {
            currency = apiCurrency
        }
        val entites = api.features.map { feature ->
            feature.properties.let {
                ParkAndRideServicePointEntity(
                    it.fid.toString(),
                    it.name,
                    it.capacity,
                    when (it.type) {
                        TYPE_PARKING_GARAGE -> ParkingSpotTypeEntity.PARKING_GARAGE
                        TYPE_CAR_PARK -> ParkingSpotTypeEntity.CAR_PARK
                        TYPE_GRAVEL_PLACE -> ParkingSpotTypeEntity.GRAVEL_PLACE
                        TYPE_MARKED_SPOTS -> ParkingSpotTypeEntity.MARKED_SPOTS
                        else -> ParkingSpotTypeEntity.UNKNOWN
                    },
                    it.stationName,
                    price = ParkAndRideServicePointPriceEntity(
                        value = when (it.price) {
                            "frei" -> 0.0 //TODO
                            else -> format.parse(it.price).toDouble()
                        },
                        currency = apiCurrency.currencyCode
                    ),
                    latitude = it.latitude,
                    longitude = it.longitude,
                    streetName = it.streetName,
                    zipCode = it.zipCode,
                    city = it.city,
                )
            }
        }
        repository.saveAll(entites)
    }
}