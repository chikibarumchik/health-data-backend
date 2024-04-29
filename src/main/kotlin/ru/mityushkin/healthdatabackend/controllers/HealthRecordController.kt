package ru.mityushkin.healthdatabackend.controllers

import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import ru.mityushkin.healthdatabackend.controllers.dto.HealthRecordRequest
import ru.mityushkin.healthdatabackend.controllers.dto.HealthRecordResponse
import ru.mityushkin.healthdatabackend.services.HealthRecordService

@RestController
@RequestMapping("/api/v1/health")
class HealthRecordController(
    private val healthRecordService: HealthRecordService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Добавление записи")
    fun addHealthRecord(@RequestBody rq: HealthRecordRequest): HealthRecordResponse {
        return healthRecordService.addHealthRecord(rq).toResponse()
    }

    @GetMapping("/records/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Получение всех записей по userId")
    fun getHealthRecords(@PathVariable userId: Long): List<HealthRecordResponse> {
        return healthRecordService.getRecordsByUserId(userId)
            .map { it.toResponse() }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Получение записи по id")
    fun getHealthRecord(@PathVariable id: Long): HealthRecordResponse {
        return healthRecordService.getById(id).toResponse()
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Изменениe записи")
    fun updateHealthRecord(
        @PathVariable id: Long,
        @RequestBody record: HealthRecordRequest
    ): HealthRecordResponse {
        return healthRecordService.updateRecord(id, record).toResponse()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Удаление записи")
    fun deleteHealthRecord(@PathVariable id: Long): Unit = healthRecordService.deleteRecord(id)
}