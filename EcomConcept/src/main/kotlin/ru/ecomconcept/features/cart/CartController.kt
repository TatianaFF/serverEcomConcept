package ru.ecomconcept.features.cart

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.ecomconcept.database.cart.CartTable
import ru.ecomconcept.database.cart.mapToCartDTO
import ru.ecomconcept.database.cart.mapToCartResponse
import ru.ecomconcept.database.hotsales.mapToHotSalesDTO
import ru.ecomconcept.database.hotsales.mapToHotSalesResponse
import ru.ecomconcept.database.phones.PhonesTable
import ru.ecomconcept.database.phones.mapToPhoneDTO
import ru.ecomconcept.database.phones.mapToPhoneResponse
import ru.ecomconcept.features.phones.models.CartRequest
import ru.ecomconcept.features.phones.models.HotSalesRequest

class CartController(private val call: ApplicationCall) {
    suspend fun fetchCart() {
        call.respond(CartTable.fetchCart())
    }

    suspend fun createCart() {
        val request = call.receive<CartRequest>()
        val cart = request.mapToCartDTO()
        CartTable.insert(cart)
        call.respond(cart.mapToCartResponse())
    }

    suspend fun deleteCart(id: String) {
        CartTable.deleteCart(id)
        fetchCart()
    }

    suspend fun fetchCartByIdUser(id: String) {
        call.respond(CartTable.fetchCartByIdUser(id))
    }
}