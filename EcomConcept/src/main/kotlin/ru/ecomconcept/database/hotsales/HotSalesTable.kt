package ru.ecomconcept.database.hotsales

import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import ru.ecomconcept.database.phones.PhonesTable

object HotSalesTable: Table("hotsales") {
    private val id = HotSalesTable.varchar("id", 100)
    private val idPhone = HotSalesTable.varchar("id_phone", 100)
    private val isNew = HotSalesTable.bool("is_new")
    private val subTitle = HotSalesTable.varchar("subtitle", 300)
    private val picture = HotSalesTable.varchar("picture", 200)

    fun insert(hotSalesDTO: HotSalesDTO){
        transaction {
            HotSalesTable.insert {
                it[id] = hotSalesDTO.id
                it[idPhone] = hotSalesDTO.idPhone
                it[isNew] = hotSalesDTO.isNew
                it[subTitle] = hotSalesDTO.subTitle
                it[picture] = hotSalesDTO.picture
            }
        }
    }

    fun fetchHotSales(): List<HotSalesDTO> {
        return try {
            transaction {
                HotSalesTable.selectAll().toList()
                    .map {
                        HotSalesDTO(
                            id = it[HotSalesTable.id],
                            idPhone = it[idPhone],
                            isNew = it[isNew],
                            subTitle = it[subTitle],
                            picture = it[picture]
                        )
                    }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun deleteHotSales(id: String) {
        transaction {
            HotSalesTable.deleteWhere { HotSalesTable.id eq id }
        }
    }
}