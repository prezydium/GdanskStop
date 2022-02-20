package com.presidium.gdanskstop.mapper

import com.presidium.gdanskstop.model.Stop
import org.json.JSONException
import org.json.JSONObject
import java.time.LocalDate
import java.util.*

fun mapJsonObjectToStopsList(queryResponse: JSONObject, currentDate: LocalDate): List<Stop> {
    val actualData = getDataForCurrentDayOrFirst(queryResponse, currentDate)
    val jsonArray = actualData.getJSONArray("stops")
    val listOfStops = LinkedList<Stop>()
    (0 until jsonArray.length()).forEach {
        val rawStop = jsonArray.getJSONObject(it)
        listOfStops.add(
            Stop(
                rawStop.getInt("stopId"),
                rawStop.getString("stopDesc"),
            )
        )
    }
    return listOfStops
}

private fun getDataForCurrentDayOrFirst(
    queryResponse: JSONObject,
    currentDate: LocalDate
): JSONObject {
    return try {
        queryResponse.getJSONObject(currentDate.toString())
    } catch (excpt: JSONException) {
        val firstAvailableDate = queryResponse.keys().next()
        return queryResponse.getJSONObject(firstAvailableDate)
    }
}