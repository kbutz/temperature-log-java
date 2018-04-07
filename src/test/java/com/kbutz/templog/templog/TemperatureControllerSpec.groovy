package com.kbutz.templog.templog

import com.kbutz.templog.templog.controller.TemperatureController
import com.kbutz.templog.templog.repository.TemperatureRepository
import org.springframework.data.domain.Sort
import spock.lang.Specification

import java.time.LocalDateTime

class TemperatureControllerSpec extends Specification {
    def temperatureRepository = Mock(TemperatureRepository)

    TemperatureController temperatureController = new TemperatureController(
            temperatureRepository: temperatureRepository
    )

    def "get current temp endpoint"() {
        when:
        temperatureController.getCurrentTemperature()

        then:
        1 * temperatureRepository.findTopByOrderByTimeDesc()
        0 * _
    }

    def "list all temp readings"() {
        when:
        temperatureController.listAllTemperatures()

        then:
        1 * temperatureRepository.findAll(Sort.by(Sort.Direction.DESC, "time"))
        0 * _
    }

    def "find by range"() {
        given:
        def from = "2018-04-01T00:00:00"
        def to = "2018-04-15T00:00:00"

        when:
        temperatureController.findRange(from, to)

        then:
        1 * temperatureRepository.findAllByTimeBetween(LocalDateTime.parse(from), LocalDateTime.parse(to))
        0 * _
    }

    def "find by range throws exception with invalid date"() {
        given:
        def from = "2018-04-01"
        def to = "2018-04-1X"

        when:
        temperatureController.findRange(from, to)

        then:
        Exception e = thrown()
        e.getMessage().startsWith("Failed to find by range at:")
    }
}
