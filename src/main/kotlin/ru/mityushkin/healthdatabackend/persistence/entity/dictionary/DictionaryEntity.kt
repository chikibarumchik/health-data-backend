package ru.mityushkin.healthdatabackend.persistence.entity.dictionary

import jakarta.persistence.*
import ru.mityushkin.usersbackend.persistence.entity.base.BaseAuditSoftDeleteEntity

@Entity
@Table(name = "dictionary")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name = "type",
    discriminatorType = DiscriminatorType.STRING
)
abstract class DictionaryEntity(
    @Column(name = "type", insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    val type: DictionaryType,

    var name: String,
    var unit: String?,
    val code: String? = null,
    ) : BaseAuditSoftDeleteEntity() {

    enum class DictionaryType {
        measurement_type;

        companion object Values {
            const val MEASUREMENT_TYPE = "measurement_type"

        }
    }

//    fun toDictionaryResponse() = DictionaryResponse(
//        id = id,
//        name = name,
//        isDeleted = isDeleted,
//        externalId = externalId,
//        position = position,
//        code = code,
//        createdAt = LocalDateTime.ofInstant(createdAt, ZoneOffset.UTC),
//        modifiedAt = LocalDateTime.ofInstant(modifiedAt, ZoneOffset.UTC),
//        employeeName = employeeName
//    )

    override fun toString(): String {
        return "DictionaryEntity(type=$type, name='$name', unit=$unit, code=$code)"
    }
}


