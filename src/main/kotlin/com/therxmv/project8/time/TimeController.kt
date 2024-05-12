package com.therxmv.project8.time

import com.therxmv.project8.utils.CircuitBreaker.TIME_SERVICE
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("time")
class TimeController {

    private val service = TimeService()

    @GetMapping
    @CircuitBreaker(name = TIME_SERVICE, fallbackMethod = "defaultTime")
    fun getTime() = service.getTime()

    fun defaultTime(throwable: Throwable) = "Check the time on your watch ;)"
}