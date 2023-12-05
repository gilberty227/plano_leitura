package br.com.planoleitura.domian.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "order",
    foreignKeys = [ForeignKey(entity = Book::class, parentColumns = ["id"],
        childColumns = ["idBook"], onDelete = CASCADE)])
data class Order(
    @PrimaryKey val id: Int,
    val idBook: Int
)
