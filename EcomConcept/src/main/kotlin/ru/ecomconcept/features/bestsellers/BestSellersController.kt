package ru.ecomconcept.features.bestsellers

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.ecomconcept.database.bestsellers.BestSellersTable
import ru.ecomconcept.database.bestsellers.mapToBestSellersDTO
import ru.ecomconcept.database.bestsellers.mapToBestSellersResponse
import ru.ecomconcept.database.phones.PhonesTable
import ru.ecomconcept.database.phones.mapToPhoneDTO
import ru.ecomconcept.database.phones.mapToPhoneResponse
import ru.ecomconcept.features.phones.models.BestSellersRequest
import ru.ecomconcept.features.phones.models.HotSalesRequest

class BestSellersController(private val call: ApplicationCall) {
    suspend fun fetchBestSellers() {
        call.respond(BestSellersTable.fetchBestSellers())
    }

    suspend fun createBestSellers() {
        val request = call.receive<BestSellersRequest>()
        val bestseller = request.mapToBestSellersDTO()
        BestSellersTable.insert(bestseller)
        call.respond(bestseller.mapToBestSellersResponse())
    }

    suspend fun deleteBestSellers(id: String) {
        BestSellersTable.deleteBestSeller(id)
        fetchBestSellers()
    }

    suspend fun fetchBestSellersByIdPhone(id: String) {
        call.respond(BestSellersTable.fetchBestSellersByIdPhone(id))
    }
}