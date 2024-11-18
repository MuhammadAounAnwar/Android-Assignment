package com.android.goally.data.db

import androidx.room.TypeConverter
import com.android.goally.data.model.api.response.copilot.Activities
import com.android.goally.data.model.api.response.copilot.AudioEvents
import com.android.goally.data.model.api.response.copilot.DailyRepeatValues
import com.android.goally.data.model.api.response.copilot.RoutineNotifications
import com.android.goally.data.model.api.response.copilot.RoutineNotificationsV2
import com.android.goally.data.model.api.response.copilot.ScheduleV2
import com.android.goally.data.model.api.response.copilot.WeeklyRepeatedValues
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StringListConverter {

    @TypeConverter
    fun fromString(value: String?): ArrayList<String> {
        val listType = object : TypeToken<ArrayList<String>>() {}.type
        return Gson().fromJson(value, listType) ?: arrayListOf()
    }

    @TypeConverter
    fun fromArrayList(value: ArrayList<String>?): String {
        return Gson().toJson(value)
    }
}

class ActivitiesConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromActivitiesList(value: ArrayList<Activities>?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toActivitiesList(value: String?): ArrayList<Activities>? {
        val listType = object : TypeToken<ArrayList<Activities>>() {}.type
        return gson.fromJson(value, listType)
    }
}


class RoutineNotificationsConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromRoutineNotificationsList(value: ArrayList<RoutineNotifications>?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toRoutineNotificationsList(value: String?): ArrayList<RoutineNotifications>? {
        val listType = object : TypeToken<ArrayList<RoutineNotifications>>() {}.type
        return gson.fromJson(value, listType)
    }
}

class RoutineNotificationsV2Converter {

    private val gson = Gson()

    @TypeConverter
    fun fromRoutineNotificationsV2List(value: ArrayList<RoutineNotificationsV2>?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toRoutineNotificationsV2List(value: String?): ArrayList<RoutineNotificationsV2>? {
        val listType = object : TypeToken<ArrayList<RoutineNotificationsV2>>() {}.type
        return gson.fromJson(value, listType)
    }
}


class RoutineAudioEventsConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromRoutineAudioEventsList(value: ArrayList<AudioEvents>?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toRoutineAudioEventsList(value: String?): ArrayList<AudioEvents>? {
        val listType = object : TypeToken<ArrayList<AudioEvents>>() {}.type
        return gson.fromJson(value, listType)
    }
}

class ScheduleV2Converter {

    private val gson = Gson()

    @TypeConverter
    fun fromScheduleV2(value: ScheduleV2?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toScheduleV2(value: String?): ScheduleV2? {
        val type = object : TypeToken<ScheduleV2>() {}.type
        return gson.fromJson(value, type)
    }
}


class WeeklyRepeatedValuesConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromWeeklyRepeatedValues(value: WeeklyRepeatedValues?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toWeeklyRepeatedValues(value: String?): WeeklyRepeatedValues? {
        val type = object : TypeToken<WeeklyRepeatedValues>() {}.type
        return gson.fromJson(value, type)
    }
}

class DailyRepeatValuesConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromDailyRepeatValues(value: DailyRepeatValues?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toDailyRepeatValues(value: String?): DailyRepeatValues? {
        val type = object : TypeToken<DailyRepeatValues>() {}.type
        return gson.fromJson(value, type)
    }
}


class GenericListConverter {

    private val gson = Gson()

    @TypeConverter
    fun <T> fromList(value: ArrayList<T>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun <T> toList(value: String?): ArrayList<T>? {
        val listType = object : TypeToken<ArrayList<T>>() {}.type
        return gson.fromJson(value, listType)
    }
}