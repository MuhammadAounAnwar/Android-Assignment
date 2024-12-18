package com.android.goally.data.model.api.response.token

import com.google.gson.annotations.SerializedName


data class TokenResponse(
    @SerializedName("token") var token: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("message") var message: String? = null
)