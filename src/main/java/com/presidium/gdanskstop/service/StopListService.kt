package com.presidium.gdanskstop.service

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class StopListService {

    fun queryStopList(context: Context){

//        val textView = findViewById<TextView>(R.id.text)
// ...

// Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(context)
        val url = "https://ckan.multimediagdansk.pl/dataset/c24aa637-3619-4dc2-a171-a23eec8f2172/resource/ee910ad8-8ffa-4e24-8ef9-d5a335b07ccb/download/displays.json"

// Request a string response from the provided URL.
        val stringRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                var mappedResults = mapJsonObjectToStopsList(response)
                // Display the first 500 characters of the response string.
              //  textView.text = "Response is: ${response.substring(0, 500)}"
                println(mappedResults)
            },
            Response.ErrorListener { /*textView.text = "That didn't work!"*/ })

// Add the request to the RequestQueue.
        queue.add(stringRequest)
    }
}