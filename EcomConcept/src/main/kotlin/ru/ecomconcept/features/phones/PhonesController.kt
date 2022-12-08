package ru.ecomconcept.features.phones

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.ecomconcept.database.phones.PhonesTable
import ru.ecomconcept.database.phones.mapToPhoneDTO
import ru.ecomconcept.database.phones.mapToPhoneResponse
import ru.ecomconcept.features.phones.models.HotSalesRequest
import ru.ecomconcept.features.phones.models.PhoneRequest

class PhonesController(private val call: ApplicationCall) {
    suspend fun fetchPhones() {
        call.respond(PhonesTable.fetchPhones())
    }

    suspend fun createPhone() {
        val request = call.receive<PhoneRequest>()
        val phone = request.mapToPhoneDTO()
        PhonesTable.insert(phone)
        call.respond(phone.mapToPhoneResponse())
    }

    suspend fun deletePhone(id: String) {
        PhonesTable.deletePhone(id)
        fetchPhones()
    }

    suspend fun fetchPhoneById(id: String) {
        call.respond(PhonesTable.fetchPhoneById(id))
    }
}