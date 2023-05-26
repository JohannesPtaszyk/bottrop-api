package dev.pott.bottropapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.filter.ShallowEtagHeaderFilter


@SpringBootApplication
@EnableScheduling
class BottropApiApplication

fun main(args: Array<String>) {
	runApplication<BottropApiApplication>(*args)
}

@Configuration
class AppConfig {
	@Bean
	fun shallowEtagHeaderFilter(): ShallowEtagHeaderFilter {
		return ShallowEtagHeaderFilter()
	}
}
