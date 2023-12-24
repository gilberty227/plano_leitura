package br.com.planoleitura.domian.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "calendar")
data class Calendar(
    @PrimaryKey val day: Int,
    val month: Int,
    val year: Int
)
