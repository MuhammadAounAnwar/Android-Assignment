package com.android.goally.data.model.api.response.copilot

import com.google.gson.annotations.SerializedName


data class AudioEvents(
    @SerializedName("event") var event: String? = null,
    @SerializedName("audioList") var audioList: ArrayList<AudioList> = arrayListOf()
)