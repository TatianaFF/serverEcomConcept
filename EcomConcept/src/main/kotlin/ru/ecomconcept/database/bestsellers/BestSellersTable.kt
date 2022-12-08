package ru.ecomconcept.database.bestsellers

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import ru.ecomconcept.database.phones.PhonesTable
import ru.ecomconcept.database.phones.PhonesTable.nullable

object BestSellersTable: Table("bestsellers") {
    private val id = BestSellersTable.varchar("id", 100)
    private val idPhone = BestSellersTable.varchar("id_phone", 100)
    private val discountPrice = BestSellersTable.double("discount_price")

    fun insert(bestSellerDTO: BestSellersDTO) {
        transaction {
            BestSellersTable.insert {
                it[id] = bestSellerDTO.id
                it[idPhone] = bestSellerDTO.idPhone
                it[discountPrice] = bestSellerDTO.discountPrice
            }
        }
    }

    fun fetchBestSellers(): List<BestSellersDTO> {
        return try {
            transaction {
                BestSellersTable.selectAll().toList()
                    .map {
                        BestSellersDTO(
                            id = it[BestSellersTable.id],
                            idPhone = it[idPhone],
                            discountPrice = it[discountPrice]
                        )
                    }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun deleteBestSeller(id: String) {
        transaction {
            BestSellersTable.deleteWhere { BestSellersTable.id eq id }
        }
    }

    fun fetchBestSellersByIdPhone(id: String): BestSellersDTO {
        return transaction {
            BestSellersTable.select { idPhone eq id }.limit(1).single().let {
                BestSellersDTO(
                    id = it[BestSellersTable.id],
                    idPhone = it[idPhone],
                    discountPrice = it[discountPrice]
                )
            }
        }
    }
}