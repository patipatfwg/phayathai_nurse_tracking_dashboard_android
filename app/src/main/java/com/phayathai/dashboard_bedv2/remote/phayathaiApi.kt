package com.phayathai.dashboard_bedv2.remote

import com.phayathai.dashboard_bedv2.model.view.response.ViewRes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface phayathaiApi {

    @Headers("Authorization: phayathai@freewill")
    @GET("v2view.php")
    fun getRoom() : Call<ViewRes>
}