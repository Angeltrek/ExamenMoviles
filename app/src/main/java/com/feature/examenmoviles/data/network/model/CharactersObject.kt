package com.feature.examenmoviles.data.network.model
import com.google.gson.annotations.SerializedName

data class CharactersObject(
    @SerializedName("items") val items: MutableList<CharacterBase>,
    @SerializedName("meta") val meta: MetadataBase,
    @SerializedName("links") val links: LinksBase
)