package com.android.goally.data.model.api.response.copilot

import com.google.gson.annotations.SerializedName


data class Activities (

  @SerializedName("name"         ) var name         : String? = null,
  @SerializedName("ordering"     ) var ordering     : Int?    = null,
  @SerializedName("audioType"    ) var audioType    : String? = null,
  @SerializedName("visualAidUrl" ) var visualAidUrl : String? = null,
  @SerializedName("_id"          ) var Id           : String? = null

)