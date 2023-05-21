package dev.pott.bottropapi.servicepoint.parkandride

import dev.pott.bottropapi.servicepoint.parkandride.entity.ParkAndRideServicePointEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ParkAndRideRepository: JpaRepository<ParkAndRideServicePointEntity, String>