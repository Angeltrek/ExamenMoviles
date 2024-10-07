package com.feature.examenmoviles.data.network.model

import com.google.gson.annotations.SerializedName

data class LinksBase (
    @SerializedName("first") val first: String,
    @SerializedName("previous") val previous: String?,
    @SerializedName("next") val next: String?,
    @SerializedName("last") val last: String
)