package ru.mityushkin.healthdatabackend.persistence.entity

import jakarta.persistence.*
import ru.mityushkin.healthdatabackend.controllers.dto.HealthRecordResponse
import ru.mityushkin.healthdatabackend.persistence.entity.dictionary.MeasurementType
import ru.mityushkin.usersbackend.persistence.entity.base.BaseAuditSoftDeleteEntity

@Entity
@Table(name = "health_records")
class HealthRecord(
    var userId: Long,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "measurement_type")
    var measurementType: MeasurementType,

    var measurementValue: String,
    var notes: String? = null
): BaseAuditSoftDeleteEntity() {
    fun toResponse(): HealthRecordResponse = HealthRecordResponse(
        this.userId,
        this.measurementType.name,
        this.measurementType.name,
        this.measurementValue,
        this.notes
    )
}