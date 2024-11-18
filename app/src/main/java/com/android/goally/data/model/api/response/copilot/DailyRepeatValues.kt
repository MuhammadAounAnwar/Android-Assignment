package com.android.goally.data.model.api.response.copilot

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale


data class DailyRepeatValues(

    @SerializedName("Sun") var Sun: ArrayList<String> = arrayListOf(),
    @SerializedName("Mon") var Mon: ArrayList<String> = arrayListOf(),
    @SerializedName("Thu") var Thu: ArrayList<String> = arrayListOf(),
    @SerializedName("Wed") var Wed: ArrayList<String> = arrayListOf(),
    @SerializedName("Tue") var Tue: ArrayList<String> = arrayListOf(),
    @SerializedName("Fri") var Fri: ArrayList<String> = arrayListOf(),
    @SerializedName("Sat") var Sat: ArrayList<String> = arrayListOf()

)

fun DailyRepeatValues.getActiveDays(): String {
    val activeDays = mutableListOf<String>()

    if (Sun.isNotEmpty()) activeDays.add("Sun")
    if (Mon.isNotEmpty()) activeDays.add("Mon")
    if (Tue.isNotEmpty()) activeDays.add("Tue")
    if (Wed.isNotEmpty()) activeDays.add("Wed")
    if (Thu.isNotEmpty()) activeDays.add("Thu")
    if (Fri.isNotEmpty()) activeDays.add("Fri")
    if (Sat.isNotEmpty()) activeDays.add("Sat")

    return if (activeDays.isEmpty()) "No active days" else activeDays.joinToString(", ")
}