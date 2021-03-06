package com.presidium.gdanskstop

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.presidium.gdanskstop.model.ApplicationData
import com.presidium.gdanskstop.model.Stop
import com.presidium.gdanskstop.model.TakeoffRecord
import com.presidium.gdanskstop.service.LineListService
import com.presidium.gdanskstop.service.SearchStop
import com.presidium.gdanskstop.service.StopListService
import com.presidium.gdanskstop.service.StopTableService


class MainActivity : AppCompatActivity() {

    private val lineListService: LineListService = LineListService()
    private val stopTableService: StopTableService = StopTableService()
    private val applicationData: ApplicationData = ApplicationData(ArrayList(), ArrayList())

    private var currentStopRecords: MutableList<TakeoffRecord> = ArrayList()
    private lateinit var listOfStopsView: ListView

    private lateinit var listOfStopsAdapter: ArrayAdapter<*>
    private lateinit var searchView: SearchView;
    private lateinit var currentStopView: ListView

    private lateinit var arrayAdapterForCurrentStop: ArrayAdapter<*>

    private lateinit var stopListService: StopListService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        stopListService = StopListService(this)
        setViewReferences()

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
        searchView.setOnQueryTextListener(SearchStop(arrayAdapterForStops))
        listOfStopsAdapter = arrayAdapterForStops
        listOfStopsView.adapter = arrayAdapterForStops
        listOfStopsView.onItemClickListener = OnItemClickListener { _, _, position, _ ->
            val element: Stop =
                listOfStopsAdapter.getItem(position) as Stop
            Log.i("Getting stop table for: ", element.name)
            listOfStopsView.visibility = View.GONE
            searchView.visibility = View.GONE
            stopTableService.queryStopTable(this, currentStopRecords, element.stopId, arrayAdapterForCurrentStop)
            currentStopView.visibility = View.VISIBLE
        }

    }

    private fun setViewReferences() {
        listOfStopsView = findViewById(R.id.list_of_stops)
        searchView = findViewById(R.id.search_stop)
        currentStopView = findViewById(R.id.current_stop)
    }

    fun showStops(view: View) {
        currentStopView.visibility = View.GONE
        listOfStopsAdapter.notifyDataSetChanged()
        searchView.visibility = View.VISIBLE
        listOfStopsView.visibility = View.VISIBLE
    }

    private fun loadApplicationData() {
        stopListService.initialLoad(this, applicationData)
        lineListService.queryLineEndpoint(this)
    }

}
