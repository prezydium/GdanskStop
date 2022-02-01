package com.presidium.gdanskstop.service

import android.icu.text.SimpleDateFormat
import com.presidium.gdanskstop.model.Line
import org.json.JSONObject
import java.util.*

fun mapJsonObjectToLinesList(queryResponse: JSONObject): List<Line> {
    val todayDate = getCurrentDateAsString()
    val todayLineData = queryResponse.getJSONObject(todayDate)
    val routesList = todayLineData.getJSONArray("routes")
    val listOfLines = ArrayList<Line>()
    (0 until routesList.length()).forEach {
        val rawStop = routesList.getJSONObject(it)
        listOfLines.add(
            Line(
                rawStop.getInt("routeId"),
                rawStop.getString("routeShortName"),
                rawStop.getString("routeLongName"),
                rawStop.getString("routeType")
            )
        )
    }
    return listOfLines
}

private fun getCurrentDateAsString(): String {
    return SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(Date())
}