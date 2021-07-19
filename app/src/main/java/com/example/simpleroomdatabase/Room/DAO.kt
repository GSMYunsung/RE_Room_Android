package com.example.simpleroomdatabase.Room

import androidx.room.*

@Dao
interface DAO {
    // Entity 를 선택해 모든 값을 가져온다.
    @Query("SELECT * FROM entity")
    fun getall() : List<Entity>

    @Insert
    fun insert(todo : Entity)

    @Delete
    fun delete(todo: Entity)

    @Query("DELETE FROM Entity")
    fun deleteAll()

    @Update
    fun updater(todo: Entity)
}