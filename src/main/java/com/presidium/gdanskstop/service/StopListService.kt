package com.presidium.gdanskstop.service

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.presidium.gdanskstop.mapper.mapJsonObjectToStopsList
import com.presidium.gdanskstop.model.ApplicationData
import org.json.JSONObject
import java.io.File
import java.lang.Exception
import java.time.LocalDate

class StopListService(context: Context) {

    var stopListFile = File(context.filesDir, "stoplist.json")


    fun initialLoad(context: Context, applicationData: ApplicationData) {
        if (stopListFile.exists()) {
            try {
                val stopListFileContent = stopListFile.readText()
                val loadedData =
                    mapJsonObjectToStopsList(JSONObject(stopListFileContent), LocalDate.now())
                applicationData.listOfStops.clear()
                applicationData.listOfStops.addAll(loadedData)
            } catch (e: Exception) {
                queryStopList(context, applicationData)
            }
        } else {
            Log.i("", "File with stop list not found, creating new one")
            //   stopListFile.createNewFile()
            queryStopList(context, applicationData)
        }
    }

    fun queryStopList(context: Context, applicationData: ApplicationData) {
        val queue = Volley.newRequestQueue(context)
        val url =
            "https://ckan.multimediagdansk.pl/dataset/c24aa637-3619-4dc2-a171-a23eec8f2172/resource/4c4025f0-01bf-41f7-a39f-d156d201b82b/download/stops.json"

        val stopsRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val mappedResults = mapJsonObjectToStopsList(response, LocalDate.now())
                if (mappedResults.isNotEmpty()) {
                    applicationData.listOfStops.clear()
                    applicationData.listOfStops.addAll(mappedResults)
                    cacheDataInFile(response)
                } else {
                    Log.w("StopListService", "Empty response from list of stops endpoint")
                }
            },
            { /*TODO error handling*/ })

        queue.add(stopsRequest)
    }

    private fun cacheDataInFile(response: JSONObject) {
        stopListFile.writeText(response.toString())
    }
}