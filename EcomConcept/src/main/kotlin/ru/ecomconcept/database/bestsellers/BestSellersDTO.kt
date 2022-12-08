package ru.ecomconcept.database.bestsellers

import kotlinx.serialization.Serializable
import ru.ecomconcept.database.hotsales.HotSalesDTO
import ru.ecomconcept.features.phones.models.BestSellersRequest
import ru.ecomconcept.features.phones.models.BestSellersResponse
import ru.ecomconcept.features.phones.models.HotSalesRequest
import ru.ecomconcept.features.phones.models.HotSalesResponse
import java.util.*

@Serializable
data class BestSellersDTO(
    var id: String,
    val idPhone: String,
    val discountPrice: Double
)

fun BestSellersRequest.mapToBestSellersDTO(): BestSellersDTO =
    BestSellersDTO(
        id = UUID.randomUUID().toString(),
        idPhone = idPhone,
        discountPrice = discountPrice
    )

fun BestSellersDTO.mapToBestSellersResponse(): BestSellersResponse =
    BestSellersResponse(
        id = id,
        idPhone = idPhone,
        discountPrice = discountPrice
    )