package ru.ecomconcept.features.hotsales

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.ecomconcept.database.hotsales.HotSalesTable
import ru.ecomconcept.database.hotsales.mapToHotSalesDTO
import ru.ecomconcept.database.hotsales.mapToHotSalesResponse
import ru.ecomconcept.database.phones.PhonesTable
import ru.ecomconcept.database.phones.mapToPhoneDTO
import ru.ecomconcept.database.phones.mapToPhoneResponse
import ru.ecomconcept.features.phones.models.HotSalesRequest

class HotSalesController(private val call: ApplicationCall) {
    suspend fun fetchHotSales() {
        call.respond(HotSalesTable.fetchHotSales())
    }

    suspend fun createHotSales() {
        val request = call.receive<HotSalesRequest>()
        val hotsales = request.mapToHotSalesDTO()
        HotSalesTable.insert(hotsales)
        call.respond(hotsales.mapToHotSalesResponse())
    }

    suspend fun deleteHotSales(id: String) {
        HotSalesTable.deleteHotSales(id)
        fetchHotSales()
    }
}