package dev.pott.bottropapi

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class BottropApiApplication

fun main(args: Array<String>) {
	runApplication<BottropApiApplication>(*args)
}
