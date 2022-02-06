package com.presidium.gdanskstop.model

class ApplicationData(var MutableList: List<Line>, var listOfStops: MutableList<Stop>)

data class Line (val routeId: Int, val routeShortName: String, val routeLongName: String,val routeType: String)

data class Stop(
    val stopId: Int,
    val name: String

){
    override fun toString(): String {
        return name
    }
}