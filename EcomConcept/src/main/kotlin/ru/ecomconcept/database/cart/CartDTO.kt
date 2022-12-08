package ru.ecomconcept.database.cart

import kotlinx.serialization.Serializable
import ru.ecomconcept.database.bestsellers.BestSellersDTO
import ru.ecomconcept.features.phones.models.BestSellersRequest
import ru.ecomconcept.features.phones.models.BestSellersResponse
import ru.ecomconcept.features.phones.models.CartRequest
import ru.ecomconcept.features.phones.models.CartResponse
import java.util.*

@Serializable
data class CartDTO(
    var id: String,
    val idUser: String,
    val idPhone: String,
    val count: Int = 1
)

fun CartRequest.mapToCartDTO(): CartDTO =
    CartDTO(
        id = UUID.randomUUID().toString(),
        idUser = idUser,
        idPhone = idPhone,
        count = count
    )

fun CartDTO.mapToCartResponse(): CartResponse =
    CartResponse(
        id = id,
        idUser = idUser,
        idPhone = idPhone,
        count = count
    )