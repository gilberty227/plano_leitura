package br.com.planoleitura.data

import io.realm.RealmList
import io.realm.annotations.PrimaryKey

class Plan {
    @PrimaryKey
    var id: Long = 0
    var name: String = ""
    lateinit var listBooks: RealmList<Book>
}