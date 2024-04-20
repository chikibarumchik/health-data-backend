package ru.mityushkin.healthdatabackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HealthDataBackendApplication

fun main(args: Array<String>) {
    runApplication<HealthDataBackendApplication>(*args)
}
