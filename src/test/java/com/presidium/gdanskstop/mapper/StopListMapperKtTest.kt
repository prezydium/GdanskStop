package com.presidium.gdanskstop.mapper

import org.json.JSONObject
import org.junit.Assert.*
import org.junit.Test
import java.time.LocalDate

class StopListMapperKtTest {

    @Test
    fun shouldMapProperly() {
        //given
        val currentFakeDate = LocalDate.of(2022, 2, 21)
        val input = JSONObject(StopListTestData().properResponse)

        //when
        val mappingResult = mapJsonObjectToStopsList(input, currentFakeDate)

        //then
        assertTrue(mappingResult.size == 2586)
    }

    @Test
    fun shouldMapProperlyOnFirstDateIfDateNotPresentInResponse() {
        //given
        val currentFakeDateNotPresentInResponse = LocalDate.of(2021, 1, 1)
        val input = JSONObject(StopListTestData().properResponse)

        //when
        val mappingResult = mapJsonObjectToStopsList(input, currentFakeDateNotPresentInResponse)

        //then
        assertTrue(mappingResult.size == 2338)
    }
}