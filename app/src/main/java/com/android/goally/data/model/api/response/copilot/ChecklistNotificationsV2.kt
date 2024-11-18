package com.android.goally.data.model.api.response.copilot

import com.google.gson.annotations.SerializedName


data class ChecklistNotificationsV2 (

  @SerializedName("notificationType" ) var notificationType : String? = null,
  @SerializedName("minutesBefore"    ) var minutesBefore    : Int?    = null,
  @SerializedName("_id"              ) var Id               : String? = null

)