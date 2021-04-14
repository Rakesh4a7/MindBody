package com.rakesh.mindbody.api

import com.rakesh.mindbody.BuildConfig
import com.rakesh.mindbody.model.Province
import com.rakesh.mindbody.model.User
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {

    private val api = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(getOkHttpClient())
        .build()
        .create(ApiRequestData::class.java)

    fun getCountries(): Single<List<User?>> {
        return api.getAllCountries()
    }

    fun getProvinces(
        id: String
    ): Single<List<Province?>> {
        return api.getAllProvinces(id)
    }

    private fun getOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
        return client.build()
    }

}