package com.rakesh.mindbody.model

import com.google.gson.annotations.SerializedName

data class Province (
    @SerializedName("ID")
    var id: Int = 0,

    @SerializedName("Name")
    var name: String? = null,

    @SerializedName("Code")
    var code: String? = null,

    @SerializedName("CountryCode")
    var countryCode: String? = null
)