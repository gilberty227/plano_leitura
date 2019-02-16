package br.com.planoleitura.view


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.planoleitura.R
import br.com.planoleitura.data.Book
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults
import io.realm.exceptions.RealmException
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    lateinit var database: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onResume() {
        super.onResume()


        val config = RealmConfiguration.Builder().name("plano.realm").build()
        database = Realm.getInstance(config)
        /*database.beginTransaction()
        database.createObject((Book::class.java), 8).setBook("Gil")
        database.commitTransaction()*/

        var oi = database.where(Book::class.java).findAll()





        database.beginTransaction()
        try {

            //val nextId = database.where(Book::class.java).findAll()
            database.commitTransaction()
            val result: RealmResults<Book> = database.where(Book::class.java).findAll()
            result.forEach { book ->
                textTeste.text = book.getBook()
            }

        }catch (e: RealmException){
            var gil = e.message
            gil = gil

        }

    }
}
