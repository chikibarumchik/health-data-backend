package ru.mityushkin.healthdatabackend.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.*
import ru.mityushkin.healthdatabackend.controllers.dto.HealthRecordRequest
import ru.mityushkin.healthdatabackend.persistence.entity.HealthRecord
import ru.mityushkin.healthdatabackend.persistence.entity.dictionary.MeasurementType
import ru.mityushkin.healthdatabackend.persistence.repositories.HealthRecordRepository
import ru.mityushkin.healthdatabackend.persistence.repositories.MeasurementTypeRepository

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ActiveProfiles("test", "security_disabled")
class HealthRecordControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var healthRecordRepository: HealthRecordRepository

    @Autowired
    private lateinit var measurementTypeRepository: MeasurementTypeRepository

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @AfterEach
    fun clear() {
        healthRecordRepository.deleteAll()
        measurementTypeRepository.deleteAll()
    }

    @Test
    fun `should add health record`() {
        val measurementType = MeasurementType(name = "Blood Pressure", code = "BLOOD_PRESSURE")
            .also { measurementTypeRepository.save(it) }
        val rq = HealthRecordRequest(1L, measurementType.code!!, "120/80", "Normal")

        mockMvc.post("/api/v1/health") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(rq)
        }.andDo {
            print()
        }.andExpect {
            status { isCreated() }
            content { contentType(MediaType.APPLICATION_JSON) }
            jsonPath("$.userId", equalTo(rq.userId.toInt()))
            jsonPath("$.measurementValue", equalTo(rq.measurementValue))
        }
    }

    @Test
    fun `should get health records by user id`() {
        val measurementType = MeasurementType("Blood Pressure", "TEST")
            .also { measurementTypeRepository.save(it) }
        val userId = 1L
        val record = HealthRecord(userId, measurementType, "120/80", "Normal")
            .also { healthRecordRepository.save(it) }

        mockMvc.get("/api/v1/health/records/$userId")
            .andDo {
                print()
            }.andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$[0].userId", equalTo(userId.toInt()))
                jsonPath("$[0].measurementValue", equalTo(record.measurementValue))
            }
    }

    @Test
    fun `should get health record by id`() {
        val measurementType = MeasurementType("Blood Pressure", "BLOOD_PRESSURE")
            .also { measurementTypeRepository.save(it) }
        val record = HealthRecord(1L, measurementType, "120/80", "Normal")
            .also { healthRecordRepository.save(it) }

        mockMvc.get("/api/v1/health/${record.id}")
            .andDo {
                print()
            }.andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.userId", equalTo(record.userId.toInt()))
                jsonPath("$.measurementValue", equalTo(record.measurementValue))
            }
    }

    @Test
    fun `should update health record`() {
        val measurementType = MeasurementType("Blood Pressure", "TEST")
            .also { measurementTypeRepository.save(it) }
        val record = HealthRecord(1L, measurementType, "120/80", "Normal")
            .also { healthRecordRepository.save(it) }
        val updatedRq = HealthRecordRequest(1L, measurementType.code!!, "130/85", "Slightly high")

        mockMvc.put("/api/v1/health/${record.id}") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(updatedRq)
        }.andDo {
            print()
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            jsonPath("$.measurementValue", equalTo(updatedRq.measurementValue))
        }
    }

    @Test
    fun `should delete health record`() {
        val measurementType = MeasurementType("Blood Pressure", "BLOOD_PRESSURE")
            .also { measurementTypeRepository.save(it) }
        val record = HealthRecord(1L, measurementType, "120/80", "Normal")
            .also { healthRecordRepository.save(it) }

        mockMvc.delete("/api/v1/health/${record.id}")
            .andDo {
                print()
            }.andExpect {
                status { isNoContent() }
            }
        assertThat(healthRecordRepository.findById(record.id!!).isEmpty).isTrue
    }
}
