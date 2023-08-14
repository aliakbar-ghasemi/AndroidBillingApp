package com.aghasemi.billing

import android.app.Application

open class App:Application() {
    companion object{
        private var instance: App? = null
        fun getInstance(): App? {
            if (instance == null){
                instance = App()
            }
            return instance
        }
    }
}