package com.android.goally.data.model.api.response.copilot

import com.google.gson.annotations.SerializedName


data class AudioList (

  @SerializedName("name"       ) var name       : String?  = null,
  @SerializedName("url"        ) var url        : String?  = null,
  @SerializedName("paUrl"      ) var paUrl      : String?  = null,
  @SerializedName("isSelected" ) var isSelected : Boolean? = null,
  @SerializedName("ordering"   ) var ordering   : Int?     = null

)