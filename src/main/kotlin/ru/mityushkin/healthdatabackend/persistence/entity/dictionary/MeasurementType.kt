package ru.mityushkin.healthdatabackend.persistence.entity.dictionary

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import ru.mityushkin.healthdatabackend.persistence.entity.dictionary.DictionaryEntity.DictionaryType.Values.MEASUREMENT_TYPE


@Entity
@DiscriminatorValue(MEASUREMENT_TYPE)
class MeasurementType(
    name: String,
    code: String,
) : DictionaryEntity(
    DictionaryType.measurement_type,
    name,
    unit = null,
    code = code,
)