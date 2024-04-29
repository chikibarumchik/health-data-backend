package ru.mityushkin.healthdatabackend.controllers.dto

data class HealthRecordRequest(
    val userId: Long,
    val measurementType: String,
    val measurementValue: String,
    val notes: String? = null
)

data class HealthRecordResponse(
    val userId: Long,
    val measurementTypeName: String,
    val measurementTypeCode: String,
    val measurementValue: String,
    val notes: String? = null
)