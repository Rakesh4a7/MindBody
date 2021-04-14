package com.rakesh.mindbody.api

import com.rakesh.mindbody.model.Province
import com.rakesh.mindbody.model.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiRequestData {
    @GET("country/")
    fun getAllCountries(): Single<List<User?>>

    @GET("country/{id}/province")
    fun getAllProvinces(@Path("id") id: String?): Single<List<Province?>>
}