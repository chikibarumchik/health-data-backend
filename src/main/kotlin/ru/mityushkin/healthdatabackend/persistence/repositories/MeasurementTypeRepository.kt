package ru.mityushkin.healthdatabackend.persistence.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.mityushkin.healthdatabackend.persistence.entity.dictionary.MeasurementType

@Repository
interface MeasurementTypeRepository: JpaRepository<MeasurementType, Long> {
    fun getByCode(code: String): MeasurementType?
}