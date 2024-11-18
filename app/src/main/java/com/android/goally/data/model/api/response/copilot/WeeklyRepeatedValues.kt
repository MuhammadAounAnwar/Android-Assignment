package com.android.goally.data.model.api.response.copilot

import androidx.room.Ignore
import com.google.gson.annotations.SerializedName


data class WeeklyRepeatedValues(
    @SerializedName("Sun") var Sun: String = "",
    @SerializedName("Mon") var Mon: String = "",
    @SerializedName("Tue") var Tue: String = "",
    @SerializedName("Wed") var Wed: String = "",
    @SerializedName("Thu") var Thu: String = "",
    @SerializedName("Fri") var Fri: String = "",
    @SerializedName("Sat") var Sat: String = ""
) {
    companion object {
        private val WEEKEND_DAYS = listOf("Sunday", "Saturday")
        private val WEEKDAY_DAYS = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")
    }

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

    fun getRepeatType(): String {
        val availableDays = dayMapping.filter { it.second.isNotEmpty() }.map { it.first }
        val hasWeekend = availableDays.any { WEEKEND_DAYS.contains(it) }
        val hasWeekdays = availableDays.any { WEEKDAY_DAYS.contains(it) }

        return when {
            hasWeekend && !hasWeekdays -> "Weekend"
            !hasWeekend && hasWeekdays -> "Weekdays"
            else -> availableDays.joinToString(", ")
        }
    }

    fun getRepeatValue(): List<String> {
        return dayMapping
            .filter { it.second.isNotEmpty() }
            .map { "${it.first} ${it.second}" }
    }
}

