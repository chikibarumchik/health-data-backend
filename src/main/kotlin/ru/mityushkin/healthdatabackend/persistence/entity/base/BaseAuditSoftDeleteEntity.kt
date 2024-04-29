package ru.mityushkin.usersbackend.persistence.entity.base

import jakarta.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseAuditSoftDeleteEntity : BaseAuditEntity() {
    var isDeleted: Boolean = false
}