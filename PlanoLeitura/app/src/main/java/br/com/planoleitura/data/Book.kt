package br.com.planoleitura.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open  class Book: RealmObject() {

    @PrimaryKey
    var id: Long = 0
    lateinit var book: String

}