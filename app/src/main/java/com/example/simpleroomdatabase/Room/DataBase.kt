package com.example.simpleroomdatabase.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Entity::class], version = 3)
abstract class DataBase : RoomDatabase() {

    abstract fun todoDao() : DAO?

    companion object{
        private val isLock = Any()
        private var instanceDataBase : DataBase? = null

        fun getInstanceDataBase(context : Context) : DataBase?{
            synchronized(isLock){
                if (instanceDataBase == null){
                    instanceDataBase = Room.databaseBuilder(
                        context,DataBase::class.java,"todo-db")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instanceDataBase
        }
    }

}