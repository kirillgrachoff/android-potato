package edu.phystech.kirillgrachoff.mipt.ui.viewmodels.places

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Place(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("deliveryTime")
    val deliveryTime: String,
    @SerialName("image")
    val image: String,
)

@Serializable
data class Commercial(
    @SerialName("picture")
    val picture: String,
    @SerialName("url")
    val redirectUrl: String,
)

@Serializable
data class CatalogResponse(
    @SerialName("nearest")
    val nearest: List<Place>,
    @SerialName("popular")
    val popular: List<Place>,
    @SerialName("commercial")
    val advertisement: Commercial,
)