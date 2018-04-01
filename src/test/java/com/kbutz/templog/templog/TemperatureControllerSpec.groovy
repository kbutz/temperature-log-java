package com.kbutz.templog.templog

import com.kbutz.templog.templog.controller.TemperatureController
import com.kbutz.templog.templog.repository.TemperatureRepository
import spock.lang.Specification

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
        1 * temperatureRepository.findAll()
        0 * _
    }
}
