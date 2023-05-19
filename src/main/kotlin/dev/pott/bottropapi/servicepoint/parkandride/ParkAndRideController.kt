package dev.pott.bottropapi.servicepoint.parkandride

import dev.pott.bottropapi.servicepoint.parkandride.dto.*
import org.springframework.data.annotation.Version
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ParkAndRideController {

    @GetMapping("/v1/service-points/park-and-ride")
    fun getAll(): ParkAndRideServicePointsResponseV1 {
        return ParkAndRideServicePointsResponseV1(
            listOf(
                ParkAndRideServicePointDtoV1(
                    "1",
                    "Bottrop Mitte",
                    100,
                    ParkingSpotTypeDtoV1.CAR_PARK,
                    "Bottrop HBF",
                    PriceDtoV1(0.0, "0.0 €"),
                    listOf("N11", "253"),
                    CoordinatesDtoV1(11000.0012, 1223123.0001),
                    AddressDtoV1(
                        "Vestische Straße",
                        "46240",
                        "Bottrop"
                    )
                ),
                ParkAndRideServicePointDtoV1(
                    "2",
                    "Bottrop Kirchellen",
                    53,
                    ParkingSpotTypeDtoV1.GRAVEL_PLACE,
                    "Kirchellen Mitte",
                    PriceDtoV1(2.99, "2.99 €"),
                    listOf("101", "112"),
                    CoordinatesDtoV1(34.0012, 99.0001),
                    AddressDtoV1(
                        "Gladbecker Str.",
                        "46246",
                        "Bottrop"
                    )
                )
            )
        )
    }

}