package com.feature.examenmoviles.data.network.model

import com.google.gson.annotations.SerializedName

data class MetadataBase (
    @SerializedName("totalItems") val totalItems: Int,
    @SerializedName("itemCount") val itemCount: Int,
    @SerializedName("itemsPerPage") val itemsPerPage: Int,
    @SerializedName("totalPages") val totalPages: Int,
    @SerializedName("currentPage") val currentPage: Int
)