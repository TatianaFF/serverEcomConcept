package ru.ecomconcept.database.hotsales

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.idParam
import ru.ecomconcept.database.phones.PhoneDTO
import ru.ecomconcept.features.phones.models.HotSalesRequest
import ru.ecomconcept.features.phones.models.HotSalesResponse
import ru.ecomconcept.features.phones.models.PhoneRequest
import ru.ecomconcept.features.phones.models.PhoneResponse
import java.util.*

@Serializable
data class HotSalesDTO(
    var id: String,
    val idPhone: String,
    val isNew: Boolean = false,
    val subTitle: String,
    val picture: String
)

fun HotSalesRequest.mapToHotSalesDTO(): HotSalesDTO =
    HotSalesDTO(
        id = UUID.randomUUID().toString(),
        idPhone = idPhone,
        isNew = isNew,
        subTitle = subTitle,
        picture = picture
    )

fun HotSalesDTO.mapToHotSalesResponse(): HotSalesResponse =
    HotSalesResponse(
        id = id,
        idPhone = idPhone,
        isNew = isNew,
        subTitle = subTitle,
        picture = picture
    )