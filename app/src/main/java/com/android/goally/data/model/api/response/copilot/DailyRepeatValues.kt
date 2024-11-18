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
        val activeDays = dayMapping
            .filter { it.second.isNotEmpty() }
            .map { it.first.take(3) }

        return if (activeDays.isEmpty()) "No active days" else activeDays.joinToString(", ")
    }

    fun getRepeatValue(): List<String> {
        return dayMapping
            .filter { it.second.isNotEmpty() }
            .map { "${it.first} ${it.second.firstOrNull() ?: ""}".trim() }
    }
}

