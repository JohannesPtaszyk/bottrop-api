package dev.pott.bottropapi.servicepoint.parkandride.dto

data class ParkAndRideServicePointDtoV1 (
    val id: String,
    val name: String,
    val capacity: Int,
    val type: ParkingSpotTypeDtoV1,
    val stationName: String,
    val price: PriceDtoV1,
    val publicTransportLines: List<String>,
    val coordinates: CoordinatesDtoV1,
    val addressDtoV1: AddressDtoV1,
)