package com.android.goally.data.model.api.response.copilot

import androidx.room.Ignore
import com.google.gson.annotations.SerializedName


data class DailyRepeatValues(
    @SerializedName("Sun") var Sun: List<String> = emptyList(),
    @SerializedName("Mon") var Mon: List<String> = emptyList(),
    @SerializedName("Tue") var Tue: List<String> = emptyList(),
    @SerializedName("Wed") var Wed: List<String> = emptyList(),
    @SerializedName("Thu") var Thu: List<String> = emptyList(),
    @SerializedName("Fri") var Fri: List<String> = emptyList(),
    @SerializedName("Sat") var Sat: List<String> = emptyList()
) {
    @Ignore
    private val dayMapping = listOf(
        "Sunday" to Sun,
        "Monday" to Mon,
        "Tuesday" to Tue,
        "Wednesday" to Wed,
        "Thursday" to Thu,
        "Friday" to Fri,
        "Saturday" to Sat
    )


    fun getActiveDays(): String {
        val scheduleType = mutableListOf<String>()
        if (Sun.isNotEmpty()) scheduleType.add("Sun")
        if (Mon.isNotEmpty()) scheduleType.add("Mon")
        if (Tue.isNotEmpty()) scheduleType.add("Tue")
        if (Wed.isNotEmpty()) scheduleType.add("Wed")
        if (Thu.isNotEmpty()) scheduleType.add("Thu")
        if (Fri.isNotEmpty()) scheduleType.add("Fri")
        if (Sat.isNotEmpty()) scheduleType.add("Sat")

        return if (scheduleType.isEmpty()) "N/A" else {
            if (scheduleType.size == 7) "Daily"
            else scheduleType.joinToString(", ")
        }
    }

    fun getRepeatValue(): List<String> {
        val scheduleType = mutableListOf<String>()
        if (Sun.isNotEmpty()) scheduleType.add("Sunday ${Sun[0]}")
        if (Mon.isNotEmpty()) scheduleType.add("Monday ${Mon[0]}")
        if (Tue.isNotEmpty()) scheduleType.add("Tuesday ${Tue[0]}")
        if (Wed.isNotEmpty()) scheduleType.add("Wednesday ${Wed[0]}")
        if (Thu.isNotEmpty()) scheduleType.add("Thursday ${Thu[0]}")
        if (Fri.isNotEmpty()) scheduleType.add("Friday ${Fri[0]}")
        if (Sat.isNotEmpty()) scheduleType.add("Saturday ${Sat[0]}")

        return scheduleType
    }
}

