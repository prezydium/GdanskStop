package com.presidium.gdanskstop.service

import android.widget.ArrayAdapter
import android.widget.SearchView

class SearchStop(private val stopListAdapter: ArrayAdapter<*>) : SearchView.OnQueryTextListener {


    override fun onQueryTextSubmit(query: String?): Boolean {
        changeDisplayedList(query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        changeDisplayedList(newText)
        return true
    }

    private fun changeDisplayedList(query: String?) {
        stopListAdapter.filter.filter(query)
        stopListAdapter.notifyDataSetChanged()
    }
}