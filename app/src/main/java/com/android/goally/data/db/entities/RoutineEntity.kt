package com.android.goally.data.db.entities

import androidx.room.*
import com.android.goally.data.db.ActivitiesConverter
import com.android.goally.data.db.RoutineAudioEventsConverter
import com.android.goally.data.db.RoutineNotificationsConverter
import com.android.goally.data.db.RoutineNotificationsV2Converter
import com.android.goally.data.db.ScheduleV2Converter
import com.android.goally.data.db.StringListConverter
import com.android.goally.data.model.api.response.copilot.Activities
import com.android.goally.data.model.api.response.copilot.AudioEvents
import com.android.goally.data.model.api.response.copilot.RoutineNotifications
import com.android.goally.data.model.api.response.copilot.RoutineNotificationsV2
import com.android.goally.data.model.api.response.copilot.Routines
import com.android.goally.data.model.api.response.copilot.Schedule
import com.android.goally.data.model.api.response.copilot.ScheduleV2
import com.google.gson.annotations.SerializedName

@Entity(tableName = "routines")
data class RoutineEntity(

    @PrimaryKey
    @SerializedName("_id") var Id: String,
    @SerializedName("enableVacationMode") var enableVacationMode: Boolean? = null,
    @SerializedName("name") var name: String = "",
    @SerializedName("rewardType") var rewardType: String? = null,
    @SerializedName("numberOfPointsOnTime") var numberOfPointsOnTime: Int? = null,
    @SerializedName("numberOfPointsLate") var numberOfPointsLate: Int? = null,
    @SerializedName("numberOfPuzzlesOnTime") var numberOfPuzzlesOnTime: Int? = null,
    @SerializedName("numberOfPuzzlesLate") var numberOfPuzzlesLate: Int? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("imgURL") var imgURL: String? = null,
    @SerializedName("ordering") var ordering: Int? = null,
    @SerializedName("parentRoutineId") var parentRoutineId: String? = null,
    @SerializedName("createdBy") var createdBy: String? = null,
    @SerializedName("clientId") var clientId: String? = null,
    @SerializedName("libraryType") var libraryType: String? = null,
    @SerializedName("migrated") var migrated: Boolean? = null,
    @SerializedName("showTimer") var showTimer: Boolean? = null,
    @SerializedName("allowClientToCancel") var allowClientToCancel: Boolean? = null,
    @SerializedName("allowToOverride") var allowToOverride: Boolean? = null,
    @SerializedName("showOnLearnerApp") var showOnLearnerApp: Boolean? = null,
    @SerializedName("notifsV2SoundName") var notifsV2SoundName: String? = null,
    @SerializedName("notifsV2SoundUrl") var notifsV2SoundUrl: String? = null,
    @SerializedName("category") var category: String = "",
    @SerializedName("enableEmotionalFeedback") var enableEmotionalFeedback: Boolean? = null,
    @SerializedName("entityType") var entityType: String? = null,
    @SerializedName("ctaOrdering") var ctaOrdering: Int? = null,
    @SerializedName("allowRecap") var allowRecap: Boolean? = null,
    @SerializedName("allowSnooze") var allowSnooze: Boolean? = null,
    @SerializedName("numberOfSnoozeAllowed") var numberOfSnoozeAllowed: Int? = null,
    @SerializedName("limitEarlyStart") var limitEarlyStart: Boolean? = null,
    @SerializedName("earlyStartMinutes") var earlyStartMinutes: Int? = null,
    @SerializedName("allowUpdateNightLightAndNoiseMachine") var allowUpdateNightLightAndNoiseMachine: Boolean? = null,
    @SerializedName("enableChillZoneWatch") var enableChillZoneWatch: Boolean? = null,
    @SerializedName("enableWeatherWatch") var enableWeatherWatch: Boolean? = null,
    @SerializedName("isScheduledByV2") var isScheduledByV2: Boolean = false,
    @SerializedName("limitRunPerDay") var limitRunPerDay: Boolean? = null,
    @SerializedName("numberOfRunPerDay") var numberOfRunPerDay: Int? = null,
    @SerializedName("limitRunPerHour") var limitRunPerHour: Boolean? = null,
    @SerializedName("numberOfRunPerHour") var numberOfRunPerHour: Int? = null,
    @SerializedName("isCreatedByDevice") var isCreatedByDevice: Boolean? = null,
    @SerializedName("enableSpeedMonitoring") var enableSpeedMonitoring: Boolean? = null,
    @SerializedName("requiredRewardApproval") var requiredRewardApproval: Boolean? = null,
    @SerializedName("narration") var narration: Boolean? = null,
    @SerializedName("__v") var _v: Int? = null,
    @SerializedName("createdAt") var createdAt: String? = null,
    @SerializedName("updatedAt") var updatedAt: String? = null,
    @SerializedName("folder") var folder: String? = null,
    @SerializedName("folderId") var folderId: String? = null,


    @Embedded(prefix = "lastSchedule")
    @SerializedName("lastSchedule") var lastSchedule: Schedule? = Schedule(),
    @Embedded(prefix = "schedule")
    @SerializedName("schedule") var schedule: Schedule? = Schedule(),


    @TypeConverters(StringListConverter::class)
    @SerializedName("templateDisabledClientIds") var templateDisabledClientIds: ArrayList<String> = arrayListOf(),
    @TypeConverters(StringListConverter::class)
    @SerializedName("appRewards") var appRewards: ArrayList<String> = arrayListOf(),
    @TypeConverters(StringListConverter::class)
    @SerializedName("devices") var devices: ArrayList<String> = arrayListOf(),
    @TypeConverters(StringListConverter::class)
    @SerializedName("tags") var tags: ArrayList<String> = arrayListOf(),


    @TypeConverters(RoutineNotificationsConverter::class)
    @SerializedName("routineNotifications") var routineNotifications: ArrayList<RoutineNotifications> = arrayListOf(),
    @TypeConverters(RoutineNotificationsV2Converter::class)
    @SerializedName("routineNotificationsV2") var routineNotificationsV2: ArrayList<RoutineNotificationsV2> = arrayListOf(),
    @TypeConverters(ActivitiesConverter::class)
    @SerializedName("activities") var activities: ArrayList<Activities> = arrayListOf(),
    @TypeConverters(RoutineAudioEventsConverter::class)
    @SerializedName("audioEvents") var audioEvents: ArrayList<AudioEvents> = arrayListOf(),
    @TypeConverters(ScheduleV2Converter::class)
    @SerializedName("scheduleV2") var scheduleV2: ScheduleV2? = ScheduleV2(),

    )


fun Routines.toEntity(): RoutineEntity {
    return RoutineEntity(
        Id = this.Id ?: throw IllegalArgumentException("ID cannot be null"),
        enableVacationMode = this.enableVacationMode,
        name = this.name,
        rewardType = this.rewardType,
        appRewards = this.appRewards,
        numberOfPointsOnTime = this.numberOfPointsOnTime,
        numberOfPointsLate = this.numberOfPointsLate,
        numberOfPuzzlesOnTime = this.numberOfPuzzlesOnTime,
        numberOfPuzzlesLate = this.numberOfPuzzlesLate,
        type = this.type,
        imgURL = this.imgURL,
        ordering = this.ordering,
        parentRoutineId = this.parentRoutineId,
        createdBy = this.createdBy,
        clientId = this.clientId,
        schedule = this.schedule,
        libraryType = this.libraryType,
        activities = this.activities,
        migrated = this.migrated,
        showTimer = this.showTimer,
        allowClientToCancel = this.allowClientToCancel,
        allowToOverride = this.allowToOverride,
        showOnLearnerApp = this.showOnLearnerApp,
        devices = this.devices,
        routineNotifications = this.routineNotifications,
        routineNotificationsV2 = this.routineNotificationsV2,
        notifsV2SoundName = this.notifsV2SoundName,
        notifsV2SoundUrl = this.notifsV2SoundUrl,
        category = this.category,
        enableEmotionalFeedback = this.enableEmotionalFeedback,
        entityType = this.entityType,
        ctaOrdering = this.ctaOrdering,
        lastSchedule = this.lastSchedule,
        tags = this.tags,
        allowRecap = this.allowRecap,
        allowSnooze = this.allowSnooze,
        numberOfSnoozeAllowed = this.numberOfSnoozeAllowed,
        limitEarlyStart = this.limitEarlyStart,
        earlyStartMinutes = this.earlyStartMinutes,
        allowUpdateNightLightAndNoiseMachine = this.allowUpdateNightLightAndNoiseMachine,
        enableChillZoneWatch = this.enableChillZoneWatch,
        enableWeatherWatch = this.enableWeatherWatch,
        templateDisabledClientIds = this.templateDisabledClientIds,
        isScheduledByV2 = this.isScheduledByV2,
        scheduleV2 = this.scheduleV2,
        limitRunPerDay = this.limitRunPerDay,
        numberOfRunPerDay = this.numberOfRunPerDay,
        limitRunPerHour = this.limitRunPerHour,
        numberOfRunPerHour = this.numberOfRunPerHour,
        isCreatedByDevice = this.isCreatedByDevice,
        audioEvents = this.audioEvents,
        enableSpeedMonitoring = this.enableSpeedMonitoring,
        requiredRewardApproval = this.requiredRewardApproval,
        narration = this.narration,
        _v = this._v,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        folder = this.folder,
        folderId = this.folderId
    )
}

fun List<Routines>.toEntityList(): List<RoutineEntity> {
    return this.map { it.toEntity() }
}

fun List<RoutineEntity>.toDomainList(): List<Routines> {
    return this.map { it.toDomain() }
}

fun RoutineEntity.toDomain(): Routines {
    return Routines(
        enableVacationMode = this.enableVacationMode,
        Id = this.Id,
        name = this.name,
        rewardType = this.rewardType,
        appRewards = this.appRewards,
        numberOfPointsOnTime = this.numberOfPointsOnTime,
        numberOfPointsLate = this.numberOfPointsLate,
        numberOfPuzzlesOnTime = this.numberOfPuzzlesOnTime,
        numberOfPuzzlesLate = this.numberOfPuzzlesLate,
        type = this.type,
        imgURL = this.imgURL,
        ordering = this.ordering,
        parentRoutineId = this.parentRoutineId,
        createdBy = this.createdBy,
        clientId = this.clientId,
        schedule = this.schedule,
        libraryType = this.libraryType,
        activities = this.activities,
        migrated = this.migrated,
        showTimer = this.showTimer,
        allowClientToCancel = this.allowClientToCancel,
        allowToOverride = this.allowToOverride,
        showOnLearnerApp = this.showOnLearnerApp,
        devices = this.devices,
        routineNotifications = this.routineNotifications,
        routineNotificationsV2 = this.routineNotificationsV2,
        notifsV2SoundName = this.notifsV2SoundName,
        notifsV2SoundUrl = this.notifsV2SoundUrl,
        category = this.category,
        enableEmotionalFeedback = this.enableEmotionalFeedback,
        entityType = this.entityType,
        ctaOrdering = this.ctaOrdering,
        lastSchedule = this.lastSchedule,
        tags = this.tags,
        allowRecap = this.allowRecap,
        allowSnooze = this.allowSnooze,
        numberOfSnoozeAllowed = this.numberOfSnoozeAllowed,
        limitEarlyStart = this.limitEarlyStart,
        earlyStartMinutes = this.earlyStartMinutes,
        allowUpdateNightLightAndNoiseMachine = this.allowUpdateNightLightAndNoiseMachine,
        enableChillZoneWatch = this.enableChillZoneWatch,
        enableWeatherWatch = this.enableWeatherWatch,
        templateDisabledClientIds = this.templateDisabledClientIds,
        isScheduledByV2 = this.isScheduledByV2,
        scheduleV2 = this.scheduleV2,
        limitRunPerDay = this.limitRunPerDay,
        numberOfRunPerDay = this.numberOfRunPerDay,
        limitRunPerHour = this.limitRunPerHour,
        numberOfRunPerHour = this.numberOfRunPerHour,
        isCreatedByDevice = this.isCreatedByDevice,
        audioEvents = this.audioEvents,
        enableSpeedMonitoring = this.enableSpeedMonitoring,
        requiredRewardApproval = this.requiredRewardApproval,
        narration = this.narration,
        _v = this._v,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        folder = this.folder,
        folderId = this.folderId
    )
}


