package br.com.planoleitura.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Chapter: RealmObject() {

    @PrimaryKey
    var id: Long = 0
    lateinit var book: Book
    var chapter: Long = 0
    var read: Boolean = false

}