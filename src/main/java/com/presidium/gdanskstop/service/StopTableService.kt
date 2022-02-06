package com.presidium.gdanskstop.service

import android.content.Context
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.presidium.gdanskstop.mapper.mapJsonObjectToTakeoffRecord
import com.presidium.gdanskstop.model.ApplicationData
import com.presidium.gdanskstop.model.TakeoffRecord

class StopTableService {

    fun queryStopTable(
        context: Context,
        stopTableResults: MutableList<TakeoffRecord>,
        stopId: Int,
        adapterToBeUpdated: ArrayAdapter<*>
    ) {
        val queue = Volley.newRequestQueue(context)
        val url =
            "https://ckan2.multimediagdansk.pl/departures?stopId="

        val urlWithQueryParam= url + stopId

        val stopsRequest = JsonObjectRequest(
            Request.Method.GET, urlWithQueryParam, null,
            { response ->
                val mappedResults = mapJsonObjectToTakeoffRecord(response)
                stopTableResults.clear()

                if (mappedResults.isNotEmpty()) {
                    stopTableResults.addAll(mappedResults)
                } else {
                    Log.w("StopListService", "Empty response from list of stops endpoint")
                }
                adapterToBeUpdated.notifyDataSetChanged()
            },
            { /*TODO error handle*/ })

        queue.add(stopsRequest)
    }

}