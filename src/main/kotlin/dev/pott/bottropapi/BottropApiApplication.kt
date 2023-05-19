package dev.pott.bottropapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BottropApiApplication

fun main(args: Array<String>) {
	runApplication<BottropApiApplication>(*args)
}
