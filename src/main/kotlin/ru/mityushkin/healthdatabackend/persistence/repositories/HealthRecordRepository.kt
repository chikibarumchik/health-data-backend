package ru.mityushkin.healthdatabackend.persistence.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.mityushkin.healthdatabackend.persistence.entity.HealthRecord

@Repository
interface HealthRecordRepository: JpaRepository<HealthRecord, Long> {
    fun findAllByUserId(userId: Long): List<HealthRecord>
}