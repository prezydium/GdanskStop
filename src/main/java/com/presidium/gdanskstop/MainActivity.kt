package com.presidium.gdanskstop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.presidium.gdanskstop.service.LineListService
import com.presidium.gdanskstop.service.StopListService

class MainActivity : AppCompatActivity() {

    private val stopListService: StopListService = StopListService()
    private val lineListService: LineListService = LineListService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadApplicationData();
    }

    private fun loadApplicationData() {
        stopListService.queryStopList(this)
        lineListService.queryLineEndpoint(this)
    }


}