package com.android.goally.data.model.api.response.copilot

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverters
import com.android.goally.data.db.DailyRepeatValuesConverter
import com.android.goally.data.db.WeeklyRepeatedValuesConverter
import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale


data class ScheduleV2(
    @SerializedName("timeType") var timeType: String? = null,
    @SerializedName("type") var type: String = "",

    /**
     * For Yearly Repeat
     */

    @SerializedName("yearlyRepeatDateValue") var yearlyRepeatDateValue: String = "",
    @SerializedName("timeValue") var timeValue: String? = null,

    /**
     * For Weekly Repeat
     */

    @SerializedName("weeklyRepeatStartedAt") var weeklyRepeatStartedAt: String? = null,
    @SerializedName("weeklyRepeatInterval") var weeklyRepeatInterval: String? = null,
    @TypeConverters(WeeklyRepeatedValuesConverter::class)
    @SerializedName("weeklyRepeatValues") var weeklyRepeatValues: WeeklyRepeatedValues? = null,

    /**
     * For Daily Repeat
     */

    @TypeConverters(DailyRepeatValuesConverter::class)
    @SerializedName("dailyRepeatValues") var dailyRepeatValues: DailyRepeatValues? = DailyRepeatValues()
)

@RequiresApi(Build.VERSION_CODES.O)
fun ScheduleV2.getScheduleType(): String {
    return when (type) {
        ScheduleType.REPEATING_YEARLY.toString() -> {
            yearlyRepeatDateValue.formatDate()
        }

        ScheduleType.REPEATING_WEEKLY.toString() -> {
            weeklyRepeatValues?.getRepeatType() ?: "N/A"
        }

        ScheduleType.REPEATING_DAILY.toString() -> {
            dailyRepeatValues?.getActiveDays() ?: "No active days"
        }

        else -> "Unknown Schedule Type"
    }
}

enum class ScheduleType(val value: String) {
    REPEATING_YEARLY("REPEATING_YEARLY"),
    REPEATING_WEEKLY("REPEATING_WEEKLY"),
    REPEATING_DAILY("REPEATING_DAILY")
}

@RequiresApi(Build.VERSION_CODES.O)
fun String.formatDate(): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val date = LocalDate.parse(this, inputFormatter)
    val outputFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.getDefault())
    return date.format(outputFormatter)
}