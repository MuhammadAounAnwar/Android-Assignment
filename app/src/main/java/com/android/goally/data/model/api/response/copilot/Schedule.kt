package com.android.goally.data.model.api.response.copilot

import com.google.gson.annotations.SerializedName


data class Schedule(

    @SerializedName("Sun") var Sun: String? = null,
    @SerializedName("Mon") var Mon: String? = null,
    @SerializedName("Tue") var Tue: String? = null,
    @SerializedName("Wed") var Wed: String? = null,
    @SerializedName("Thu") var Thu: String? = null,
    @SerializedName("Fri") var Fri: String? = null,
    @SerializedName("Sat") var Sat: String? = null

)

fun Schedule.getScheduledDaysValues(): String {
    val days = listOf(Sun, Mon, Tue, Wed, Thu, Fri, Sat).filterNotNull()
    return days.joinToString(", ")
}

fun Schedule.getScheduledDays(): String {
    val dayNames = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
    val scheduledDays = listOf(Sun, Mon, Tue, Wed, Thu, Fri, Sat)

    return dayNames.filterIndexed { index, _ -> scheduledDays[index] != null }
        .joinToString(", ")
}