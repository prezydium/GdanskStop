package com.presidium.gdanskstop.model

import java.time.ZonedDateTime

data class TakeoffRecord (val estimatedTime: ZonedDateTime, val headsign: String, val routeId: Int)