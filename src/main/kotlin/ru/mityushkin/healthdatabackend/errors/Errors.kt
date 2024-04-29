package ru.mityushkin.healthdatabackend.errors

open class BadRequestExceptions(override val message: String?) : RuntimeException(message)

class MeasurementTypeNotFound(val code: String) : BadRequestExceptions("MeasurementType with code $code doesn't exist")

class HealthRecordNotFoundById(val id: Long): BadRequestExceptions("HealthRecord with userId=$id is not found")

