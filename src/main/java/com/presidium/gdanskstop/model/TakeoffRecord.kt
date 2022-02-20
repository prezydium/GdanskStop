package com.presidium.gdanskstop.model

import java.time.LocalDateTime
import java.time.ZonedDateTime

data class TakeoffRecord (val estimatedTime: ZonedDateTime, val headsign: String, val routeId: Int) {
    override fun toString(): String {
        val toLocalTime = LocalDateTime.from(estimatedTime).toLocalTime()
        estimatedTime.withFixedOffsetZone()
        return "$toLocalTime $headsign $routeId"
    }
}