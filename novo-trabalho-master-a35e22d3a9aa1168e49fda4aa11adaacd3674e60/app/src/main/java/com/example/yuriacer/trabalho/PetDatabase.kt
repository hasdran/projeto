package com.example.yuriacer.trabalho

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import java.util.*

@Database(entities = arrayOf(Pet::class), version = 1)
abstract class PetDatabase :RoomDatabase() {
    abstract fun petDao(): PetDao

    companion object {
        const val DATABASE_NAME = "tb_pet"
        private var INSTANCE: PetDatabase? = null

        fun getInstance(context: Context):PetDatabase?{
            if(INSTANCE == null) {
                synchronized(PetDatabase::class) {
                  /*  Room.databaseBuilder(context, PetDatabase::class.java!!, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build()*/
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            PetDatabase::class.java, DATABASE_NAME)
                            .build()
                }
            }
            return INSTANCE
        }
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}