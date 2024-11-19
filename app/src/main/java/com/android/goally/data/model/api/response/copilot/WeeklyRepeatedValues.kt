package com.android.goally.data.model.api.response.copilot

import androidx.room.Ignore
import com.google.gson.annotations.SerializedName


data class WeeklyRepeatedValues(
    @SerializedName("Sun") var Sun: String? = null,
    @SerializedName("Mon") var Mon: String? = null,
    @SerializedName("Tue") var Tue: String? = null,
    @SerializedName("Wed") var Wed: String? = null,
    @SerializedName("Thu") var Thu: String? = null,
    @SerializedName("Fri") var Fri: String? = null,
    @SerializedName("Sat") var Sat: String? = null
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
        val weekdays = setOf(Mon, Tue, Wed, Thu, Fri)
        val weekend = setOf(Sun, Sat)

        val hasWeekend = weekend.any { weekend.contains(it) }
        val hasWeekdays = weekdays.any { weekdays.contains(it) }

        return when {
            hasWeekend && !hasWeekdays -> "Weekend"
            !hasWeekend && hasWeekdays -> "Weekdays"
            else -> {
                val availableDays = mutableListOf<String>()
                if (Sun != null) availableDays.add("Sun")
                if (Mon != null) availableDays.add("Mon")
                if (Tue != null) availableDays.add("Tue")
                if (Wed != null) availableDays.add("Wed")
                if (Thu != null) availableDays.add("Thu")
                if (Fri != null) availableDays.add("Fri")
                if (Sat != null) availableDays.add("Sat")
                availableDays.joinToString(", ")
            }
        }
    }


    fun getRepeatValue(): List<String> {
        val availableDays = mutableListOf<String>()
        if (Sun != null) availableDays.add("Sunday ${Sun ?: ""}")
        if (Mon != null) availableDays.add("Monday ${Mon ?: ""}")
        if (Tue != null) availableDays.add("Tuesday ${Tue ?: ""}")
        if (Wed != null) availableDays.add("Wednesday ${Wed ?: ""}")
        if (Thu != null) availableDays.add("Thursday ${Thu ?: ""}")
        if (Fri != null) availableDays.add("Friday ${Fri ?: ""}")
        if (Sat != null) availableDays.add("Saturday ${Sat ?: ""}")
        return availableDays
    }
}


