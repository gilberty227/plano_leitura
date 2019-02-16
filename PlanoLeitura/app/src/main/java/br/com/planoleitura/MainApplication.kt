package br.com.planoleitura

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class MainApplication: Application(){

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}