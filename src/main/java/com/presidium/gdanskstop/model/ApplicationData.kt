package com.presidium.gdanskstop.model

class ApplicationData(var MutableList: List<Line>, var listOfStops: MutableList<Stop>)

data class Line (val routeId: Int, val routeShortName: String, val routeLongName: String,val routeType: String)

data class Stop(
    val displayCode: Int,
    val name: String,
    val idStop1: Int,
    val idStop2: Int,
    val idStop3: Int,
    val idStop4: Int,
)