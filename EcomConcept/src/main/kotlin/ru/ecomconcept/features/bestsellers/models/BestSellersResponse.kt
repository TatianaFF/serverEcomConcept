package ru.ecomconcept.features.phones.models

import kotlinx.serialization.Serializable

@Serializable
data class BestSellersResponse(
    var id: String,
    val idPhone: String,
    val discountPrice: Double
)