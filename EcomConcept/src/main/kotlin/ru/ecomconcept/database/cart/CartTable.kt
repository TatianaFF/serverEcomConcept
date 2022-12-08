package ru.ecomconcept.database.cart

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import ru.ecomconcept.database.phones.PhonesTable

object CartTable: Table("cart") {
    private val id = CartTable.varchar("id", 100)
    private val idUser = CartTable.varchar("id_user", 100)
    private val idPhone = CartTable.varchar("id_phone", 100)
    private val count = CartTable.integer("count")

    fun insert(cartDTO: CartDTO) {
        transaction {
            CartTable.insert {
                it[id] = cartDTO.id
                it[idUser] = cartDTO.idUser
                it[idPhone] = cartDTO.idPhone
                it[count] = cartDTO.count
            }
        }
    }

    fun fetchCart(): List<CartDTO> {
        return try {
            transaction {
                CartTable.selectAll().toList()
                    .map {
                        CartDTO(
                            id = it[CartTable.id],
                            idUser = it[idUser],
                            idPhone = it[idPhone],
                            count = it[count]
                        )
                    }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun deleteCart(id: String) {
        transaction {
            CartTable.deleteWhere { CartTable.id eq id }
        }
    }

    fun fetchCartByIdUser(id: String): List<CartDTO> {
        return try {
            transaction {
                CartTable.select { idUser eq id }.toList()
                    .map {
                        CartDTO(
                            id = it[CartTable.id],
                            idUser = it[idUser],
                            idPhone = it[idPhone],
                            count = it[count]
                        )
                    }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}