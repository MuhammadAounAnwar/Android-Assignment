package com.android.goally.data.model.api.response.copilot

import com.google.gson.annotations.SerializedName


data class WeeklyRepeatedValues(
    @SerializedName("Sun") var Sun: String = "",
    @SerializedName("Mon") var Mon: String = "",
    @SerializedName("Thu") var Thu: String = "",
    @SerializedName("Wed") var Wed: String = "",
    @SerializedName("Tue") var Tue: String = "",
    @SerializedName("Fri") var Fri: String = "",
    @SerializedName("Sat") var Sat: String = ""
)

fun WeeklyRepeatedValues.getRepeatType(): String {
    val weekendDays = setOf(Sun, Sat)
    val weekdays = setOf(Mon, Tue, Wed, Thu, Fri)

    val hasWeekend = weekendDays.any { it.isNotEmpty() }
    val hasWeekdays = weekdays.any { it.isNotEmpty() }

    return when {
        hasWeekend && !hasWeekdays -> "Weekend"
        !hasWeekend && hasWeekdays -> "Weekdays"
        else -> "Custom"
    }
}