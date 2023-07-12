package com.example.core.domain.models

import com.google.gson.annotations.SerializedName

data class Configurations (@SerializedName("images") val images: Images)

data class Images(@SerializedName("base_url") val basUrl: String,
                  @SerializedName("secure_base_url") val secureBaseUrl: String,
                  @SerializedName("poster_sizes") val posterSizes: ArrayList<String>)