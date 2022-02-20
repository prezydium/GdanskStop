package com.presidium.gdanskstop.mapper

import org.json.JSONObject
import org.junit.Assert.*
import org.junit.Test
import java.time.LocalDate

class StopListMapperKtTest {

    @Test
    fun shouldMapProperly() {
        //given
        val currentFakeDate = LocalDate.of(2022, 2, 20)
        val input = JSONObject(StopListTestData().properResponse)

        //when
        val mappingResult = mapJsonObjectToStopsList(input, currentFakeDate)

        //then
        assertTrue(mappingResult.size > 1)
    }

    @Test
    fun shouldMapProperlyOnFirstDateIfDateNotPresentInResponse() {
        //given
        val currentFakeDate = LocalDate.of(2021, 1, 1)
        val input = JSONObject(StopListTestData().properResponse)

        //when
        val mappingResult = mapJsonObjectToStopsList(input, currentFakeDate)

        //then
        assertTrue(mappingResult.size > 1)
    }
}