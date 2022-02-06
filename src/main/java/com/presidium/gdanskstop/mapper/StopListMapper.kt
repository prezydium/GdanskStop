package com.presidium.gdanskstop.service

import com.presidium.gdanskstop.model.Stop
import org.json.JSONObject
import java.util.*

fun mapJsonObjectToStopsList(queryResponse: JSONObject): List<Stop>{
    val jsonArray = queryResponse.getJSONArray("displays")
    val listOfStops = LinkedList<Stop>()
    (0 until jsonArray.length()).forEach {
        val rawStop = jsonArray.getJSONObject(it)
        listOfStops.add(Stop(
            rawStop.getInt("displayCode"),
            rawStop.getString("name"),
            rawStop.getInt("idStop1"),
            rawStop.getInt("idStop2"),
            rawStop.getInt("idStop3"),
            rawStop.getInt("idStop4"),
        ))
    }
    return listOfStops
}