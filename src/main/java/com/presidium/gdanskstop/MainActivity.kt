package com.presidium.gdanskstop

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.presidium.gdanskstop.model.ApplicationData
import com.presidium.gdanskstop.model.Stop
import com.presidium.gdanskstop.model.TakeoffRecord
import com.presidium.gdanskstop.service.LineListService
import com.presidium.gdanskstop.service.StopListService
import com.presidium.gdanskstop.service.StopTableService


class MainActivity : AppCompatActivity() {

    private val stopListService: StopListService = StopListService()
    private val lineListService: LineListService = LineListService()
    private val stopTableService: StopTableService = StopTableService()

    private val applicationData: ApplicationData = ApplicationData(ArrayList(), ArrayList())
    private var currentStopRecords: MutableList<TakeoffRecord> = ArrayList()

    private lateinit var listOfStopsView: ListView
    private lateinit var listOfStopsAdapter: ArrayAdapter<*>

    private lateinit var currentStopView: ListView
    private lateinit var arrayAdapterForCurrentStop: ArrayAdapter<*>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listOfStopsView = findViewById(R.id.list_of_stops)

        currentStopView = findViewById(R.id.current_stop)
        currentStopView.visibility = View.GONE
        val arrayAdapterForCurrentStop: ArrayAdapter<*> = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            currentStopRecords
        )
        currentStopView.adapter = arrayAdapterForCurrentStop
        this.arrayAdapterForCurrentStop = arrayAdapterForCurrentStop

        loadApplicationData()

        val arrayAdapterForStops: ArrayAdapter<*> = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            applicationData.listOfStops
        )
        listOfStopsAdapter = arrayAdapterForStops
        listOfStopsView.adapter = arrayAdapterForStops
        listOfStopsView.onItemClickListener = OnItemClickListener { _, _, position, _ ->
            val element: Stop =
                listOfStopsAdapter.getItem(position) as Stop
            Log.i("xxx", element.name)
            listOfStopsView.visibility = View.GONE
            stopTableService.queryStopTable(this, currentStopRecords, element.stopId, arrayAdapterForCurrentStop)
            currentStopView.visibility = View.VISIBLE
        }

    }

    fun showStops(view: View) {
        currentStopView.visibility = View.GONE
        listOfStopsAdapter.notifyDataSetChanged()
        listOfStopsView.visibility = View.VISIBLE
    }

    private fun loadApplicationData() {
        stopListService.queryStopList(this, applicationData)
        lineListService.queryLineEndpoint(this)
    }


}
