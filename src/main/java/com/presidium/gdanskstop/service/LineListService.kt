package com.presidium.gdanskstop.service

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class LineListService {

    fun queryLineEndpoint(context: Context) {
        val queue = Volley.newRequestQueue(context)
        val url =
            "https://ckan.multimediagdansk.pl/dataset/c24aa637-3619-4dc2-a171-a23eec8f2172/resource/22313c56-5acf-41c7-a5fd-dc5dc72b3851/download/routes.json"
        val stringRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val mappedResults = mapJsonObjectToLinesList(response)
                //  textView.text = "Response is: ${response.substring(0, 500)}"
                println(mappedResults)
            },
            { /*textView.text = "That didn't work!"*/ })
        queue.add(stringRequest)

    }
}