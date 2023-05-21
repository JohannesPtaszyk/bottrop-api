package dev.pott.bottropapi.servicepoint.parkandride

import dev.pott.bottropapi.servicepoint.parkandride.dto.*
import dev.pott.bottropapi.servicepoint.parkandride.entity.ParkAndRideServicePointEntity
import dev.pott.bottropapi.servicepoint.parkandride.model.ParkingSpotType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.annotation.Version
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ParkAndRideController(@Autowired private val service: ParkAndRideService) {

    @GetMapping("/v1/service-points/park-and-ride")
    fun getAll(): ParkAndRideServicePointsResponseV1 {
        val servicePoints = service.getAllServicePoints().map {
            ParkAndRideServicePointDtoV1(
                id = it.id,
                name = it.name,
                capacity = it.capacity,
                type = when (it.type) {
                    ParkingSpotType.MARKED_SPOTS -> ParkingSpotTypeDtoV1.MARKED_SPOTS
                    ParkingSpotType.CAR_PARK -> ParkingSpotTypeDtoV1.CAR_PARK
                    ParkingSpotType.PARKING_GARAGE -> ParkingSpotTypeDtoV1.PARKING_GARAGE
                    ParkingSpotType.GRAVEL_PLACE -> ParkingSpotTypeDtoV1.GRAVEL_PLACE
                    ParkingSpotType.UNKNOWN -> ParkingSpotTypeDtoV1.UNKNOWN
                },
                stationName = it.stationName,
                price = it.price.let {
                    PriceDtoV1(it.value, it.formatted)
                },
                publicTransportLines = it.publicTransportLines,
                coordinates = it.coordinates.let {
                    CoordinatesDtoV1(it.latitude, it.longitude)
                },
                address = it.address.let {
                    AddressDtoV1(
                        it.streetName,
                        it.zipCode,
                        it.city
                    )
                }
            )
        }
        return ParkAndRideServicePointsResponseV1(servicePoints)
    }

}