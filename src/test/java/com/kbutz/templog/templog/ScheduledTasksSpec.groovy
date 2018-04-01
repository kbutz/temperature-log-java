package com.kbutz.templog.templog

import com.fasterxml.jackson.databind.ObjectMapper
import com.kbutz.templog.templog.data.RawTempDto
import com.kbutz.templog.templog.repository.TemperatureRepository
import com.kbutz.templog.templog.tasks.ScheduledTasks
import net.aksingh.owmjapis.core.OWM
import org.springframework.web.client.RestTemplate
import spock.lang.Ignore
import spock.lang.Specification

class ScheduledTasksSpec extends Specification {
    def objectMapper = Mock(ObjectMapper)
    def restTemplate = Mock(RestTemplate)
    def temperatureRepository = Mock(TemperatureRepository)

    ScheduledTasks scheduledTasks = new ScheduledTasks(
            objectMapper: objectMapper,
            restTemplate: restTemplate,
            temperatureRepository: temperatureRepository
    )

    def "getInsideTempFromPi"() {
        when:
        String insideTemp = scheduledTasks.getInsideTempFromPi()

        then:
        1 * restTemplate.getForObject(_ as String, String.class) >> "{\"someKey\":\"someValue\"}"
        1 * objectMapper.readValue(_ as String, RawTempDto.class) >> new RawTempDto(rawTemp: "70.0")
        0 * _

        and:
        insideTemp == "70.0"
    }

    @Ignore("Ignore until OWM is mocked to appropriately test expected behavior")
    def "getOutsideTemp from OpenWeatherMap API"() {
        when:
        String outsideTemp = scheduledTasks.getOutsideTempFromOwmApi()

        then:
        0 * _
    }
}
