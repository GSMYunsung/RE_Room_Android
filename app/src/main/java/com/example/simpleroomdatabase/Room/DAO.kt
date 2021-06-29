package com.example.simpleroomdatabase.Room

import androidx.room.*

@Dao
interface DAO {
    // Entity 를 선택해 모든 값을 가져온다.
    @Query("SELECT * FROM entity")
    fun getall() : List<Entity>

    // Entity 를 선택해 title 인것을 찾아라
    @Query("SELECT * FROM entity WHERE title LIKE :title")
    fun findByTitle (title : String) : Entity

    @Insert
    fun insert(todo : Entity)

    @Delete
    fun delete( todo: Entity)

    @Query("DELETE FROM Entity")
    fun deleteAll()

    @Update
    fun update (todo: Entity)

}