package com.android.goally.data.network.rest.api

import com.android.goally.constants.WebServiceConstant
import com.android.goally.data.model.api.ErrorResponse
import com.android.goally.data.model.api.response.copilot.CopilotListResponse
import com.android.goally.data.model.api.response.health.ServerHealthApiResponse
import com.haroldadmin.cnradapter.NetworkResponse
import com.android.goally.data.model.api.response.token.TokenResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GeneralApi {
    @GET(value = WebServiceConstant.CHECK_HEALTH)
    suspend fun checkHealth(): NetworkResponse<ServerHealthApiResponse, ErrorResponse>

    @GET(value = WebServiceConstant.GET_TOKEN)
    suspend fun getToken(@Query("name") name: String): NetworkResponse<TokenResponse, ErrorResponse>

    @GET(value = WebServiceConstant.COPILOT_LIST)
    suspend fun getCopilotList(): NetworkResponse<CopilotListResponse, ErrorResponse>
}

