package com.rakesh.mindbody.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("ID")
    var id: Int = 0,

    @SerializedName("Name")
    var name: String? = null,

    @SerializedName("Code")
    var code: String? = null,

    @SerializedName("PhoneCode")
    var phoneCode: String? = null
)