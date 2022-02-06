package com.presidium.gdanskstop

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.presidium.gdanskstop.model.ApplicationData
import com.presidium.gdanskstop.model.Stop
import com.presidium.gdanskstop.service.LineListService
import com.presidium.gdanskstop.service.StopListService


class MainActivity : AppCompatActivity() {

    private val stopListService: StopListService = StopListService()
    private val lineListService: LineListService = LineListService()

    private val applicationData: ApplicationData = ApplicationData(ArrayList(), ArrayList())

    private lateinit var listOfStopsView: ListView
    private lateinit var listOfStopsAdapter: ArrayAdapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listOfStopsView = findViewById(R.id.list_of_stops)
        loadApplicationData()

        val arrayAdapter: ArrayAdapter<*> = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            applicationData.listOfStops
        )
        listOfStopsAdapter = arrayAdapter
        listOfStopsView.adapter = arrayAdapter
    }

    fun showStops(view: View){
        listOfStopsAdapter.notifyDataSetChanged()
        listOfStopsView.visibility = View.VISIBLE
    }

    private fun loadApplicationData() {
        stopListService.queryStopList(this, applicationData)
        lineListService.queryLineEndpoint(this)
    }


}