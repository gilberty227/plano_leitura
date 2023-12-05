package br.com.planoleitura.domian.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey val id: Int,
    val name: String,
    val email: String,
    val birthday: Int,
    val gender: Int,
    val bible: Int
) {
    companion object {
        val versionBible = mutableListOf("NVI", "ARA", "RA")
    }
}
