package com.presidium.gdanskstop.mapper

import com.presidium.gdanskstop.model.TakeoffRecord
import org.json.JSONObject
import java.time.ZonedDateTime
import java.util.*

fun mapJsonObjectToTakeoffRecord(queryResponse: JSONObject): List<TakeoffRecord>{
    val jsonArray = queryResponse.getJSONArray("departures")
    val tableRecords = LinkedList<TakeoffRecord>()
    val zoneId = TimeZone.getDefault().toZoneId()
    (0 until jsonArray.length()).forEach {
        val rawStop = jsonArray.getJSONObject(it)
        tableRecords.add(TakeoffRecord(
            ZonedDateTime.parse(rawStop.getString("estimatedTime")).toLocalDateTime().atZone(zoneId),
            rawStop.getString("headsign"),
            rawStop.getInt("routeId")
        ))
    }
    return tableRecords
}