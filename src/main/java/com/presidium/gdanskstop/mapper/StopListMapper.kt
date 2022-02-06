package com.presidium.gdanskstop.mapper

import com.presidium.gdanskstop.model.Stop
import org.json.JSONObject
import java.time.LocalDate
import java.util.*

fun mapJsonObjectToStopsList(queryResponse: JSONObject): List<Stop>{
    var nowDate = LocalDate.now().toString()
    var actualData = queryResponse.getJSONObject(nowDate)
    val jsonArray = actualData.getJSONArray("stops")
    val listOfStops = LinkedList<Stop>()
    (0 until jsonArray.length()).forEach {
        val rawStop = jsonArray.getJSONObject(it)
        listOfStops.add(Stop(
            rawStop.getInt("stopId"),
            rawStop.getString("stopDesc"),
        ))
    }
    return listOfStops
}