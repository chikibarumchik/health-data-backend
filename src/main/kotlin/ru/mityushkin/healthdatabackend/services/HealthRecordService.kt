package ru.mityushkin.healthdatabackend.services

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.mityushkin.healthdatabackend.controllers.dto.HealthRecordRequest
import ru.mityushkin.healthdatabackend.errors.HealthRecordNotFoundById
import ru.mityushkin.healthdatabackend.errors.MeasurementTypeNotFound
import ru.mityushkin.healthdatabackend.persistence.entity.HealthRecord
import ru.mityushkin.healthdatabackend.persistence.repositories.HealthRecordRepository
import ru.mityushkin.healthdatabackend.persistence.repositories.MeasurementTypeRepository

@Service
class HealthRecordService(
    private val healthRecordRepository: HealthRecordRepository,
    private val measurementTypeRepository: MeasurementTypeRepository
) {

    @Transactional
    fun addHealthRecord(rq: HealthRecordRequest): HealthRecord {
        val measurementType =
            measurementTypeRepository.getByCode(rq.measurementType) ?: throw MeasurementTypeNotFound(rq.measurementType)
        return HealthRecord(
            userId = rq.userId,
            measurementType = measurementType,
            measurementValue = rq.measurementValue,
            notes = rq.notes
        )
            .also { healthRecordRepository.save(it) }
    }

    @Transactional
    fun getRecordsByUserId(userId: Long): List<HealthRecord> =
        healthRecordRepository.findAllByUserId(userId)

    @Transactional
    fun updateRecord(id: Long, rq: HealthRecordRequest): HealthRecord {
        val measurementType =
            measurementTypeRepository.getByCode(rq.measurementType) ?: throw MeasurementTypeNotFound(rq.measurementType)
        return getById(id).apply {
            this.userId = rq.userId
            this.measurementType = measurementType
            this.measurementValue = rq.measurementValue
            this.notes = rq.notes
        }.also { healthRecordRepository.save(it) }
    }

    @Transactional
    fun getById(id: Long): HealthRecord = healthRecordRepository.findById(id).orElseThrow { throw HealthRecordNotFoundById(id)}

    @Transactional
    fun deleteRecord(id: Long) = getById(id).let { healthRecordRepository.delete(it) }


}