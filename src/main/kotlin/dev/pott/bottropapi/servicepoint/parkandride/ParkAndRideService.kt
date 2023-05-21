package dev.pott.bottropapi.servicepoint.parkandride

import dev.pott.bottropapi.servicepoint.parkandride.dto.*
import dev.pott.bottropapi.servicepoint.parkandride.entity.ParkingSpotTypeEntity
import dev.pott.bottropapi.servicepoint.parkandride.model.*
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import java.text.DecimalFormat
import java.util.Currency

@Service
class ParkAndRideService(val parkAndRideRepository: ParkAndRideRepository) {

    fun getAllServicePoints(): List<ParkAndRideServicePoint> {
        //TODO add locale format from client request
        val currencyFormat = DecimalFormat.getCurrencyInstance()
        return parkAndRideRepository.findAll().map { entity ->
            ParkAndRideServicePoint(
                id = entity.id,
                name = entity.name,
                capacity = entity.capacity,
                type = when (entity.type) {
                    ParkingSpotTypeEntity.CAR_PARK -> ParkingSpotType.CAR_PARK
                    ParkingSpotTypeEntity.MARKED_SPOTS -> ParkingSpotType.MARKED_SPOTS
                    ParkingSpotTypeEntity.PARKING_GARAGE -> ParkingSpotType.PARKING_GARAGE
                    ParkingSpotTypeEntity.GRAVEL_PLACE -> ParkingSpotType.GRAVEL_PLACE
                    ParkingSpotTypeEntity.UNKNOWN -> ParkingSpotType.UNKNOWN
                },
                stationName = entity.stationName,
                price = entity.price.let {
                    val currency = Currency.getInstance(it.currency)
                    val formatted = currencyFormat.apply {
                        setCurrency(currency)
                    }.format(it.value)

                    Price(it.value, formatted, currency)
                },
                publicTransportLines = emptyList(), //TODO
                coordinates = Coordinates(
                    entity.latitude,
                    entity.longitude
                ),
                address = Address(
                    entity.streetName,
                    entity.zipCode,
                    entity.city
                )
            )
        }
    }
}