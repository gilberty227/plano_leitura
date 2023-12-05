package br.com.planoleitura.domian.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE

@Entity(tableName = "calendar",
    foreignKeys = [ForeignKey(entity = Book::class, parentColumns = ["id"],
        childColumns = ["idBook"], onDelete = CASCADE)])
data class Calendar(
    val day: Int,
    val month: Int,
    val year: Int
)
