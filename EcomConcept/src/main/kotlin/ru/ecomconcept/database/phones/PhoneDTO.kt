package ru.ecomconcept.database.phones

import kotlinx.serialization.Serializable
import ru.ecomconcept.features.phones.models.HotSalesRequest
import ru.ecomconcept.features.phones.models.HotSalesResponse
import ru.ecomconcept.features.phones.models.PhoneRequest
import ru.ecomconcept.features.phones.models.PhoneResponse
import java.util.*

@Serializable
data class PhoneDTO(
    var id: String,
    val CPU: String,
    val camera: String,
    val price: Double,
    val rating: Double? = null,
    val ssd: String,
    val title: String,
    val capacity: List<String>,
    val color: List<String>,
    val images: List<String>
)

fun PhoneRequest.mapToPhoneDTO(): PhoneDTO =
    PhoneDTO(
        id = UUID.randomUUID().toString(),
        CPU = CPU,
        camera = camera,
        price = price,
        rating = rating,
        ssd = ssd,
        title = title,
        capacity = capacity,
        color = color,
        images = images
    )

fun PhoneDTO.mapToPhoneResponse(): PhoneResponse =
    PhoneResponse(
        id = id,
        CPU = CPU,
        camera = camera,
        price = price,
        rating = rating,
        ssd = ssd,
        title = title,
        capacity =  capacity,
        color = color,
        images = images
    )



