package com.kbutz.templog.templog

import com.fasterxml.jackson.databind.ObjectMapper
import com.kbutz.templog.templog.constants.Secrets
import com.kbutz.templog.templog.data.RawTempDto
import com.kbutz.templog.templog.repository.TemperatureRepository
import com.kbutz.templog.templog.tasks.ScheduledTasks
import net.aksingh.owmjapis.core.OWM
import net.aksingh.owmjapis.model.CurrentWeather
import net.aksingh.owmjapis.model.param.Cloud
import net.aksingh.owmjapis.model.param.Coord
import net.aksingh.owmjapis.model.param.Main
import net.aksingh.owmjapis.model.param.Rain
import net.aksingh.owmjapis.model.param.Snow
import net.aksingh.owmjapis.model.param.System
import net.aksingh.owmjapis.model.param.Weather
import net.aksingh.owmjapis.model.param.Wind
import org.springframework.web.client.RestTemplate
import spock.lang.Ignore
import spock.lang.Specification

class ScheduledTasksSpec extends Specification {
    def objectMapper = Mock(ObjectMapper)
    OWM owm = GroovyMock()
    def restTemplate = Mock(RestTemplate)
    def temperatureRepository = Mock(TemperatureRepository)

    def setup() {
        owm.setApiKey("SOME_KEY")
        owm.setUnit(OWM.Unit.IMPERIAL)
    }

    ScheduledTasks scheduledTasks = new ScheduledTasks(
            objectMapper: objectMapper,
            owm: owm,
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

    // https://stackoverflow.com/questions/48391716/spock-with-mockito-testing-kotlin-classes/48402178#48402178
    @Ignore("Need to add another library to support stubbing Kotlin final classes")
    def "getOutsideTemp from OpenWeatherMap API"() {
        given:
        Main mainData = new Main(70.0, 10.0, 85.0, 35.0, 0.0, 0.0, 10.0, 235.0)
        List<Weather> weatherList = [new Weather()]
        CurrentWeather currentWeather = new CurrentWeather(
                1,
                new Rain(),
                new Snow(),
                new Coord(),
                weatherList,
                "Minneapolis",
                200,
                mainData,
                new Cloud(),
                100,
                new System() as System,
                "string baseStation",
                new Wind()
        )

        when:
        String outsideTemp = scheduledTasks.getOutsideTempFromOwmApi()

        then:
        1 * owm.currentWeatherByCityName(Secrets.STATE) >> currentWeather
        0 * _

        and:
        outsideTemp == "70.0"
    }
}
