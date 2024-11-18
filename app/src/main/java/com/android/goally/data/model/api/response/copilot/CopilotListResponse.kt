package com.android.goally.data.model.api.response.copilot

import com.google.gson.annotations.SerializedName


data class CopilotListResponse(

    @SerializedName("routines") var routines: ArrayList<Routines>? = null,
    @SerializedName("checklists") var checklists: ArrayList<Checklists> = arrayListOf()

)