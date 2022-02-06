package com.presidium.gdanskstop.service

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.presidium.gdanskstop.model.ApplicationData
import kotlin.math.log

class StopListService {

    fun queryStopList(context: Context, applicationData: ApplicationData){

        val queue = Volley.newRequestQueue(context)
        val url = "https://ckan.multimediagdansk.pl/dataset/c24aa637-3619-4dc2-a171-a23eec8f2172/resource/ee910ad8-8ffa-4e24-8ef9-d5a335b07ccb/download/displays.json"

// Request a string response from the provided URL.
        val stopsRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val mappedResults = mapJsonObjectToStopsList(response)
               if (mappedResults.isNotEmpty()){
                   applicationData.listOfStops.clear()
                   applicationData.listOfStops.addAll(mappedResults)
               } else {
                   Log.w("StopListService", "Empty response from list of stops endpoint")
               }
            },
            { /*textView.text = "That didn't work!"*/ })

        queue.add(stopsRequest)
    }
}