package com.android.goally.data.model.api.response.copilot

import com.google.gson.annotations.SerializedName


data class RoutineNotifications (

  @SerializedName("name"       ) var name       : String?  = null,
  @SerializedName("isActive"   ) var isActive   : Boolean? = null,
  @SerializedName("audioUrl"   ) var audioUrl   : String?  = null,
  @SerializedName("isReadText" ) var isReadText : Boolean? = null,
  @SerializedName("timeBefore" ) var timeBefore : Int?     = null,
  @SerializedName("_id"        ) var Id         : String?  = null

)