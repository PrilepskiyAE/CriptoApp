package com.ambrella.criptoapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ambrella.criptoapp.pojo.CoinPriceInfo


@Database(entities = [CoinPriceInfo::class],version = 1,exportSchema = false)
abstract class Appdatabase: RoomDatabase() {
    companion object
    {
        private var db:Appdatabase?=null
        private const val DB_NAME="mail.db"
        private val LOCK=Any()

        fun getInstance(context: Context):  Appdatabase
        {
            synchronized(LOCK){
                db?.let { return it }
                val instance:Appdatabase= Room.databaseBuilder(
                    context,
                    Appdatabase::class.java,
                    DB_NAME
                ).build()
                db=instance
                return instance
            }
        }
    }
    abstract fun coinPriceInfoDao():CoinPriceInfoDAO
}