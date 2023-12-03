package br.com.planoleitura.domian.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Plan(
    @PrimaryKey val id: Int,
    val title: String,
    val duration: Int
)
