package com.android.goally.data.model.api.response.copilot

import com.google.gson.annotations.SerializedName


data class Activities(
    @SerializedName("name") var name: String = "",
    @SerializedName("minCompletionTime") var minCompletionTime: Double? = null,
    @SerializedName("maxCompletionTime") var maxCompletionTime: Double? = null,
    @SerializedName("audioUrl") var audioUrl: String? = null,
    @SerializedName("ordering") var ordering: Int? = null,
    @SerializedName("imgURL") var imgURL: String? = null,
    @SerializedName("audioType") var audioType: String? = null,
    @SerializedName("createdBy") var createdBy: String? = null,
    @SerializedName("parentActivityId") var parentActivityId: String? = null,
    @SerializedName("allowCancelActivity") var allowCancelActivity: Boolean? = null,
    @SerializedName("allowPauseActivity") var allowPauseActivity: Boolean? = null,
    @SerializedName("allowPush") var allowPush: Boolean? = null,
    @SerializedName("allowBack") var allowBack: Boolean? = null,
    @SerializedName("showTimer") var showTimer: Boolean? = null,
    @SerializedName("libraryType") var libraryType: String? = null,
    @SerializedName("migrated") var migrated: Boolean? = null,
    @SerializedName("autoComplete") var autoComplete: Boolean? = null,
    @SerializedName("numOfAudioRepeats") var numOfAudioRepeats: Int? = null,
    @SerializedName("allowRestart") var allowRestart: Boolean? = null,
    @SerializedName("_id") var Id: String = "",
    @SerializedName("createdAt") var createdAt: String? = null,
    @SerializedName("updatedAt") var updatedAt: String? = null
)